package com.coded.spring.ordering

import RegistrationResponseDto
import com.coded.spring.ordering.authentication.AuthRequest
import com.coded.spring.ordering.authentication.AuthResponse
import com.coded.spring.ordering.authentication.jwt.JwtService
import com.coded.spring.ordering.items.CreateItemRequestDTO
import com.coded.spring.ordering.items.ItemDTO
import com.coded.spring.ordering.items.ItemsListDTO
import com.coded.spring.ordering.items.ItemsRepository
import com.coded.spring.ordering.orders.CreateOrderRequest
import com.coded.spring.ordering.orders.OrderDTO
import com.coded.spring.ordering.orders.OrderRepository
import com.coded.spring.ordering.orders.OrdersListDTO
import com.coded.spring.ordering.profiles.ProfileRequestDto
import com.coded.spring.ordering.profiles.ProfileResponseDto
import com.coded.spring.ordering.users.RegistrationRequest
import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UsersRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.context.annotation.Profile
import org.springframework.http.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.MultiValueMap
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
class ApplicationTests {

	companion object {
		val testUser = "Yousef91"
		val testPassword = "Test123456"

        @JvmStatic
        @BeforeAll
        fun setup(

			@Autowired usersRepository: UsersRepository,
			@Autowired passwordEncoder: PasswordEncoder
		) {

            val test = UserEntity(
                name = "Yousef Al Othman",
                age = 25,
                username = testUser,
                password = passwordEncoder.encode(testPassword)
            )
			usersRepository.save(test)
        }
    }

	@Autowired
	lateinit var restTemplate: TestRestTemplate
	@Autowired
	lateinit var  usersRepository: UsersRepository

    // a helper function to get jwt token
	fun getAuthToken(): String {
		// create a authentication request with test credentials
		val login = AuthRequest(
			username = testUser,
			password = testPassword
		)

		// send an http post request to the login endpoint
		val response: ResponseEntity<AuthResponse> = restTemplate.postForEntity(
			"/authentication/login",
			login,
			AuthResponse::class.java
		)

		Assertions.assertEquals(HttpStatus.OK, response.statusCode) //check status code
		assertNotNull(response.body) //check that response.body is not null

		return response.body?.token ?: throw AssertionError("Token is null")

	}

	// function to create the authentication request
	fun <T> authRequest(body: T, token: String): HttpEntity<T>{
		val headers = HttpHeaders()
		headers.setBearerAuth(token)
		return HttpEntity(body, headers)
	}


	// 1st test
	@Test
	fun `As a user, I can login and get jwt token`(){
		// create a authentication request with test credentials
		val login = AuthRequest(
			username = testUser,
			password = testPassword
		)

		// send a http post request to the login endpoint
		val response: ResponseEntity<AuthResponse> = restTemplate.postForEntity(
			"/authentication/login",
			login,
			AuthResponse::class.java
		)

		Assertions.assertEquals(HttpStatus.OK, response.statusCode) //check status code
		assertNotNull(response.body) //check that response.body is not null
	}

	//registration
	@Test
	fun `As a developer, I can test user regastration endpoint`(){
		// create a regastration request
		val regastration = RegistrationRequest(
			username = "userRegistration",
			password = testPassword,
			age = 30,
			name = "Nemo"
		)

		// send http post request to the regastration endpoint
		val response: ResponseEntity<RegistrationResponseDto> = restTemplate.postForEntity(
			"/register",
			regastration,
			RegistrationResponseDto::class.java
		)

		Assertions.assertEquals(HttpStatus.OK, response.statusCode) //check status code
		Assertions.assertEquals("userRegistration", response.body?.username) // check if username matches
		// Verify the user was saved in database
		val savedUser = usersRepository.findByUsername("userRegistration")
		assertNotNull(savedUser)

	}



	// 3rd test
	@Test
	fun `As a developer, I can test creating orders`(){
		//get authentication token
		val token = getAuthToken()

		//get user id
		val user = usersRepository.findByUsername(testUser)
		assertNotNull(user)

		// create order
		val order = CreateOrderRequest(
			userId = user?.id!!
		)
		//1st order
		val response1: ResponseEntity<Any> = restTemplate.postForEntity(
			"/orders/orders",
			authRequest(order, token),
			Any::class.java
		)
		assertEquals(HttpStatus.OK, response1.statusCode) // check status code

		//2nd order
		val response2: ResponseEntity<Any> = restTemplate.postForEntity(
			"/orders/orders",
			authRequest(order, token),
			Any::class.java
		)
		assertEquals(HttpStatus.OK, response2.statusCode) // check status code


	}// 4th test
	@Test
	fun `As a developer, I can test reading orders list`(){
		//get authentication token
		val token = getAuthToken()

		//get user id
		val user = usersRepository.findByUsername(testUser)
		assertNotNull(user)

		// create order
//		val order = CreateOrderRequest(
//			userId = user?.id!!
//		)
//		//1st order
//		val response1: ResponseEntity<Any> = restTemplate.postForEntity(
//			"/orders/orders",
//			authRequest(order, token),
//			Any::class.java
//		)
//		assertEquals(HttpStatus.OK, response1.statusCode) // check status code
//
//		//2nd order
//		val response2: ResponseEntity<Any> = restTemplate.postForEntity(
//			"/orders/orders",
//			authRequest(order, token),
//			Any::class.java
//		)
//		assertEquals(HttpStatus.OK, response2.statusCode) // check status code

		// create authenticated get request
		val headers = HttpHeaders()
		headers.setBearerAuth(token)
		val getRequest = HttpEntity<Any>(headers)

		// get the orders
		val response: ResponseEntity<OrdersListDTO> = restTemplate.exchange(
			"/orders/orders",
			HttpMethod.GET,
			getRequest,
			OrdersListDTO::class.java
		)

		assertEquals(HttpStatus.OK, response.statusCode) // check status code
		val orders = response.body
		assertNotNull(orders) // checking orders is not null
		assertEquals(2, response.body?.orders?.size)// making sure there's 2 orders in the list

		//verify all orders belong to the same user

	}

	//5th test
	@Test
	fun `as a developer, I can test a user can create profile`(){
		val token = getAuthToken()

		// create profile
		val createProfile = ProfileRequestDto(
			firstName = "Harvey",
			lastName = "Specter",
			phoneNumber = "333-111-3333"
		)
		//create response
		val response: ResponseEntity<ProfileResponseDto> = restTemplate.postForEntity(
			"/profile",
			authRequest(createProfile, token),
			ProfileResponseDto::class.java
		)
		//verify responses
		assertEquals(HttpStatus.OK, response.statusCode)
		assertEquals("Harvey", response.body?.firstName)
		assertEquals("Specter", response.body?.lastName)
		assertEquals("333-111-3333", response.body?.phoneNumber)
	}

	//6th test
	@Test
	fun `as a developer, I can test a user can update profile`(){
		val token = getAuthToken()

		val updateProfile = ProfileRequestDto(
			firstName = "Mike",
			lastName = "Ross",
			phoneNumber = "111-222-2222"
		)
		val response2: ResponseEntity<ProfileResponseDto> = restTemplate.postForEntity(
			"/profile",
			authRequest(updateProfile, token),
			ProfileResponseDto::class.java
		)

		assertEquals(HttpStatus.OK, response2.statusCode)
		assertEquals("Mike", response2.body?.firstName)
		assertEquals("Ross", response2.body?.lastName)
		assertEquals("111-222-2222", response2.body?.phoneNumber)
	}

	//7th test
	@Test
	fun `As a developer, I can add items to an order`(){
		val token = getAuthToken()

		//get user id
		val user = usersRepository.findByUsername(testUser)
		assertNotNull(user)

		// Add items to the order
		val item1 = CreateItemRequestDTO(
			name = "Machboos",
			quantity = 1,
			order_id = user?.id!!
		)

		val item2 = CreateItemRequestDTO(
			name = "Laban",
			quantity = 4,
			order_id = user.id!!
		)

		// Create items
		restTemplate.postForEntity(
			"/items/create",
			authRequest(item1, token),
			ItemDTO::class.java
		)

		restTemplate.postForEntity(
			"/items/create",
			authRequest(item2, token),
			ItemDTO::class.java
		)

		// Get the order id
		val headers = HttpHeaders()
		headers.setBearerAuth(token)
		val getRequest = HttpEntity<Any>(headers)

		// list items
		val response: ResponseEntity<ItemsListDTO> = restTemplate.exchange(
			"/orders/orders",
			HttpMethod.GET,
			getRequest,
			ItemsListDTO::class.java

		)
		assertEquals(HttpStatus.OK, response.statusCode)
		val items = response.body
		assertNotNull(items)
	}

	//8th test
	@Test
	fun `as a developer, I can test reading the menu`(){

		val response = restTemplate.getForEntity(
			"/menu",
			Object::class.java
		)
		assertEquals(HttpStatus.OK, response.statusCode)
		assertNotNull(response)

	}

}
package com.coded.spring.ordering

import com.coded.spring.ordering.authentication.jwt.JwtService
import com.coded.spring.ordering.items.CreateItemRequestDTO
import com.coded.spring.ordering.items.ItemsRepository
import com.coded.spring.ordering.orders.OrderRepository
import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UsersRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.MultiValueMap
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {
	@Autowired
	lateinit var restTemplate: TestRestTemplate

	companion object {
		@JvmStatic
		@BeforeAll
		fun setUp(
			@Autowired usersRepository: UsersRepository,
			@Autowired passwordEncoder: PasswordEncoder,
			@Autowired ordersRepository: OrderRepository,
			@Autowired itemsRepository: ItemsRepository
		) {

			val testUser = UserEntity(
				name = "test1",
				age = 29,
				username = "test2",
				password = passwordEncoder.encode("testpass"),
			)
			usersRepository.save(testUser)
		}
	}

	@Test
	fun testHelloWorld(@Autowired jwtService: JwtService){
		val token = jwtService.generateToken("test2")
		val headers = HttpHeaders(
			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
		)
		val requestEntity = HttpEntity<String>(headers)

		val result = restTemplate.exchange(
			"/hello",
			HttpMethod.GET,
			requestEntity,
			String::class.java
		)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals("Hello World!", result.body)
	}

}
//	@Autowired
//	lateinit var restTemplate: TestRestTemplate
//
//	@Test
//	fun helloWorld() {
//
//		val result = restTemplate.getForEntity(
//			"/hello",
//			String::class.java
//		)
//		assertEquals(expected = HttpStatus.OK, actual = result?.statusCode)
//		assertEquals(expected = "Hello World!", actual = result.body)
//	}
//
//	@Test
//	fun createItem()  {
//		val result = restTemplate.postForEntity(
//			"/items/create",
//			CreateItemRequestDTO(
//				name = "burger",
//				quantity = 2,
//				order_id = 3),
//				Any::class.java)
//
//		assertEquals(expected = HttpStatus.OK, actual = result?.statusCode)
//			}
//
//	}




package authentication

import RegistrationRequestDTO
import authentication.jwt.JwtService
import authentication.users.UsersService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.*
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Tag(name = "AuthenticationAPI")
@RestController
@RequestMapping("/authentication")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
    private val usersService: UsersService
) {

    // login page
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): AuthResponse {

        // create a spring security authentication using the username and password from the request
        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        // then the credentials will be authenticated by authentication manager
        val authentication = authenticationManager.authenticate(authToken)

        // check if authentication is successful
        if (authentication.isAuthenticated) {

            // load the user details from the userDetailsService
            val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
            // Uses a JWT (JSON Web Token) service to generate a token for the authenticated user.
            val token = jwtService.generateToken(userDetails.username)
            // Returns a response object containing the JWT token
            return AuthResponse(token)
        } else {
            throw UsernameNotFoundException("Invalid user request!")
        }
    }

    // register
    @PostMapping("/register")
    fun addUser(@RequestBody request: RegistrationRequestDTO) {
        usersService.registerUsers(request)
        ResponseEntity.ok()
    }


    // check the token
    @PostMapping("/check-token")
    fun checkToken(
        principal: Principal
    ): CheckTokenResponse {
        return CheckTokenResponse(
            userId = usersService.findByUsername(principal.name)
        )
    }

}

data class CheckTokenResponse(
    val userId: Long
)

data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val token: String
)
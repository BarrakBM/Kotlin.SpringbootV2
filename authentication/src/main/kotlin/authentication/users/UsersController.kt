package authentication.users

import RegistrationResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "UserAPI")
@RestController
class UsersController(
    private val usersService: UsersService,

){

    @GetMapping("/authentication/users/list")
    fun users() = usersService.listUsers()


}
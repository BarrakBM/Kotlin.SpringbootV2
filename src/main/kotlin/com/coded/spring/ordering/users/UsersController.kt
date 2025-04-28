package com.coded.spring.ordering.users

import RegistrationResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.coded.spring.ordering.users.RegistrationRequest
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "UserAPI")
@RestController
class UsersController(
    private val usersService: UsersService,

){

    @GetMapping("/users/list")
    fun users() = usersService.listUsers()

    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegistrationRequest): Any {
        try {
            val user = usersService.registerUsers(request)
            return RegistrationResponseDto(
                message = "User registered successfully",
                username = user.username
            )
        }catch (e: IllegalArgumentException){
            return ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

}
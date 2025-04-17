package com.coded.spring.ordering.users

import jakarta.inject.Named
import org.springframework.security.crypto.password.PasswordEncoder

@Named
class UsersService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder // Added missing dependency
) {
    fun listUsers(): List<User> = usersRepository.findAll().map {
        User(
            id = it.id,
            name = it.name,
            age = it.age,
        )
    }

    fun registerUsers(request: RegistrationRequest): UserEntity {

        //check if username exists already
        if(usersRepository.existsByUsername(request.username)){
            throw IllegalArgumentException("user name already exists")
        }

        // Validate the password
        val password = request.password

        // check pass length
        if (password.length < 6) {
            throw IllegalArgumentException("Password must be at least 6 characters long")
        }

        // check if there's any uppercase letter
        if (!password.any { it.isUpperCase() }) {
            throw IllegalArgumentException("Password must contain at least one capital letter")
        }

        // check if there's any digits
        if (!password.any { it.isDigit() }) {
            throw IllegalArgumentException("Password must contain at least one number")
        }

        // create user
        val user = UserEntity(
            name = request.name,
            age = request.age,
            username = request.username,
            password = passwordEncoder.encode(request.password)
        )

        // save the user
        return usersRepository.save(user)
    }
}

data class User(
    val id: Long?,
    val name: String,
    val age: Int
)

data class RegistrationRequest(
    val name: String,
    val age: Int,
    val username: String,
    val password: String
)


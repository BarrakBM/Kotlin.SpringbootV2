package com.coded.spring.ordering

import com.coded.spring.ordering.users.Roles
import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UsersRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class InitUserRunner {

    @Bean
    fun initUsers(usersRepository: UsersRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user = UserEntity(
            name = "HelloUser",
            username = "testuser",
            password = passwordEncoder.encode("password123"),
            age = 18,

        )
        if (usersRepository.findByUsername(user.username) == null) {
            println("Creating user ${user.username}")
            usersRepository.save(user)
        } else {
            println("User ${user.username} already exists")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<InitUserRunner>(*args).close()
}
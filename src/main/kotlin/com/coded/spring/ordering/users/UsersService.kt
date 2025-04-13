package com.coded.spring.ordering.users

import com.coded.spring.ordering.UsersRepository
import jakarta.inject.Named

@Named
class UsersService(
    private val usersRepository: UsersRepository,
) {

    fun listUsers(): List<User> = usersRepository.findAll().map {
        User(
            id = it.id,
            name = it.name,
            age = it.age
        )
    }
}

data class User(
    val id: Long?,
    val name: String,
    val age: Int
)
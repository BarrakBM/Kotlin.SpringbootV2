package com.coded.spring.ordering.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersService: UsersService
){

    @GetMapping("/users/list")
    fun users() = usersService.listUsers()
}
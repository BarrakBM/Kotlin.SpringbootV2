package com.coded.spring.ordering.authentication

import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UsersRepository
import jakarta.inject.Named
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsClass(
    private val usersRepository: UsersRepository
): UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails {
        val user: UserEntity = usersRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User Not Found")

        return User.builder()
            .username(user.username)
            .password(user.password)
    //            .roles(user.role.toString())
            .build()
    }
}
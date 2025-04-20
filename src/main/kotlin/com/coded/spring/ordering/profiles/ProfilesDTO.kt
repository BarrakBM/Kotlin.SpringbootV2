package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.users.UserEntity

data class ProfileRequestDto(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)

data class ProfileResponseDto(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)
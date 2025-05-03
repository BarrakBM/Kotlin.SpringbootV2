package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.users.UsersRepository
import jakarta.inject.Named
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
open class ProfilesService(
    private val profilesRepository: ProfilesRepository,
    private val usersRepository: UsersRepository
) {

    // create or update info for profiles
    fun createOrUpdateProf(request: ProfileRequestDto): Any{

        // get the username from the security contextholer
        val username = SecurityContextHolder.getContext().authentication.name

        // check if user exists
        val user = usersRepository.findByUsername(username)
            ?: throw IllegalArgumentException("user not found")

        // check if user have a profile
        val existingProfile = profilesRepository.findByUserId(user)

        val profile = if( existingProfile != null){
            // update existing profile
            val updateProfile = existingProfile.copy(
                firstName = request.firstName,
                lastName = request.lastName,
                phoneNumber = request.phoneNumber
            )
            profilesRepository.save(updateProfile)

        } else { // if user doesn't have a profile
            // create a new profile
            val createProfile = ProfileEntity(
                userId = user,
                firstName = request.firstName,
                lastName = request.lastName,
                phoneNumber = request.phoneNumber,
            )
            profilesRepository.save(createProfile)
        }

        return ProfileResponseDto(
            userId = user.id!!,
            firstName = profile.firstName,
            lastName = profile.lastName,
            phoneNumber = profile.phoneNumber
        )
    }

}
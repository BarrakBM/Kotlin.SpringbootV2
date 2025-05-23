package com.coded.spring.ordering.profiles

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "ProfileAPI")
@RestController
class ProfilesController(
    private val profilesRepository: ProfilesRepository,
    private val profilesService: ProfilesService
){
    // user creating profile
    @PostMapping("/profile")
    fun createOrUpdateProfile(@RequestBody request: ProfileRequestDto): Any {
        try{
            return profilesService.createOrUpdateProf(request)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }
}
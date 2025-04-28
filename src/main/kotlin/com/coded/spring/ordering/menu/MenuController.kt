package com.coded.spring.ordering.menu

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "MenuAPI")
@RestController
class MenuController(
    private val menuService: MenuService
){

    @GetMapping("menu")
    fun menu() = menuService.listMenu()
}
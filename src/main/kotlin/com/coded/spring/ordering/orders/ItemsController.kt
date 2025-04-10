package com.coded.spring.ordering.orders

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ItemsController(
    private val itemsService: ItemsService
) {

    @GetMapping("/items/list")
    fun items() = itemsService.listItems()
}
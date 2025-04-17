package com.coded.spring.ordering.items

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ItemsController(
    private val itemsService: ItemsService
) {

    @GetMapping("/items/list")
    fun items() = itemsService.listItems()

//    @PostMapping("/items/create")
//    fun addItems(request: CreateItemRequestDTO): ItemEntity{
//
//    }
}
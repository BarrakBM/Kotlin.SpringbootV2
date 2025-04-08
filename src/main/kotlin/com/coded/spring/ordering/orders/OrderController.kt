package com.coded.spring.ordering.orders

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderService: OrderService

) {

    @GetMapping("/orders/list")
    fun orders() = orderService.listOrders()
}
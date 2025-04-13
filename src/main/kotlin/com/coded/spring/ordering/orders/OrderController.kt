package com.coded.spring.ordering.orders

import com.coded.spring.ordering.users.UsersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderService: OrderService,
    private val userService: UsersService

) {

    @GetMapping("/orders/orders-list")
    fun orders() = orderService.listOrders()

    // create order
    @PostMapping("/orders/orders-list")
    fun createOrders(@RequestBody request: CreateOrderRequest){
        return orderService.createOrder( userId = request.userId )
    }

}

data class CreateOrderRequest(
    val userId: Long

)
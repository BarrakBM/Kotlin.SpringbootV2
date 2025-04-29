package com.coded.spring.ordering.orders

import com.coded.spring.ordering.users.UsersService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "OrderAPI")
@RestController
class OrderController(
    private val orderService: OrderService,
    private val userService: UsersService

) {

    @GetMapping("/orders/orders")
    fun orders(): OrdersListDTO {
        val ordersList = orderService.listOrders()
        return OrdersListDTO(orders = ordersList)
    }

    // create order
    @PostMapping("/orders/orders")
    fun createOrders(@RequestBody request: CreateOrderRequest){
        return orderService.createOrder( userId = request.userId )
    }

}

data class CreateOrderRequest(
    val userId: Long

)
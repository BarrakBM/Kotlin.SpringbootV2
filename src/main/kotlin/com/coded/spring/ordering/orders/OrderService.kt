package com.coded.spring.ordering.orders

import com.coded.spring.ordering.OrderRepository
import jakarta.inject.Named

@Named
class OrderService(
    private val orderRepository: OrderRepository,
) {

    fun listOrders(): List<Order> = orderRepository.findAll().map{
        Order(
            username = it.username,
            restaurant = it.restaurant,
            items = it.items


        )
    }
}

data class Order(
    val username: String,
    val restaurant: String,
    val items: String
)
package com.coded.spring.ordering.orders

import com.coded.spring.ordering.Order
import com.coded.spring.ordering.OrderRepository
import jakarta.inject.Named
import java.math.BigInteger

@Named
class OrderService(
    private val orderRepository: OrderRepository,
) {

    fun listOrders(): List<Order> = orderRepository.findAll().map{
        com.coded.spring.ordering.Order(
            id = it.id,
            user_id = it.user_id,



        )
    }
}


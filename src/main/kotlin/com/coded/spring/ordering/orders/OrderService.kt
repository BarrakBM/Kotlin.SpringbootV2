package com.coded.spring.ordering.orders

import com.coded.spring.ordering.OrderEntity
import com.coded.spring.ordering.OrderRepository
import com.coded.spring.ordering.UsersRepository
import jakarta.inject.Named
import java.math.BigInteger

@Named
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UsersRepository
) {

    // listing orders
    fun listOrders(): List<OrderEntity> = orderRepository.findAll().map{
        OrderEntity(id = it.id, user = it.user,items = it.items)
    }

    // creating new order
    fun createOrder(userId: Long){
        val user = userRepository.findById(userId).get()
        val newOrder = OrderEntity(user=user)
        orderRepository.save(newOrder)
    }


}


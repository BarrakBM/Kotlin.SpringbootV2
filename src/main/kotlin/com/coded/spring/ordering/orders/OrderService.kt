package com.coded.spring.ordering.orders

import com.coded.spring.ordering.users.UsersRepository
import jakarta.inject.Named

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
    fun createOrder(userId: Long) {
        val user = userRepository.findById(userId).get()
        val newOrder = OrderEntity(user=user)
        // save order
        orderRepository.save(newOrder)

//        val Items = request.items.map{
//            ItemEntity(name = it.)
//        }
//    }

}}


package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.ItemDTO
import com.coded.spring.ordering.users.UsersRepository
import jakarta.inject.Named

@Named
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UsersRepository
) {

    // listing orders
    fun listOrders(): List<OrderDTO> = orderRepository.findAll().map { order ->
        OrderDTO(
            id = order.id, // transfer the orderId to dto
            userId = order.user.id,
            items = order.items?.map { item ->
                // for each item in the order, create an ItemDTO
                ItemDTO(
                    id = item.id,
                    name = item.name,
                    quantity = item.quantity,
                    orderId = order.id
                )
            }
        )
    }

    // creating new order
    fun createOrder(userId: Long) {
        val user = userRepository.findById(userId).get()
        val newOrder = OrderEntity(user=user)
        // save order
        orderRepository.save(newOrder)
    }
}


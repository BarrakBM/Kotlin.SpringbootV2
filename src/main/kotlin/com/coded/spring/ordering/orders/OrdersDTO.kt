package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.ItemDTO

// return this when user asking for order list
data class OrderDTO(
    val id: Long?,
    val userId: Long?,
    val items: List<ItemDTO>?
)


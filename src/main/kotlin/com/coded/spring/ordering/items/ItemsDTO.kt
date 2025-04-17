package com.coded.spring.ordering.items

data class CreateItemRequestDTO(

    var name: String,
    var quantity: Int,
    var order_id: Int
)
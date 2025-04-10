package com.coded.spring.ordering.orders

import jakarta.inject.Named

@Named
class ItemsService(
    private val itemsRepository: ItemsRepository
) {

    fun listItems(): List<Items> = itemsRepository.findAll().map {
        Items(
            id = it.id,
            order_id = it.order_id,
            quantity = it.quantity,
            price = it.price,
            name = it.name
        )

    }
}
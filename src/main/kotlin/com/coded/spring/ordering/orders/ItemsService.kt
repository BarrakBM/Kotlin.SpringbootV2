package com.coded.spring.ordering.orders

import jakarta.inject.Named

@Named
class ItemsService(
    private val itemsRepository: ItemsRepository
) {

    fun listItems(): List<ItemEntity> = itemsRepository.findAll().map {
        ItemEntity(
            id = it.id,
            order_id = it.order_id,
            quantity = it.quantity,
            name = it.name
        )

    }
}
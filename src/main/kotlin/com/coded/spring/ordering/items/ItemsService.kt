package com.coded.spring.ordering.items

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

    fun createItem(request: CreateItemRequestDTO): Any{
        val item = ItemEntity(
            name = request.name,
            quantity = request.quantity,
            order_id = request.order_id
        )
        return itemsRepository.save(item)
    }


}
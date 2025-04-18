package com.coded.spring.ordering.menu

import jakarta.inject.Named

@Named
class MenuService(
    private val menuRepository: MenuRepository,
) {

    fun listMenu(): List<MenuEntity> = menuRepository.findAll().map {
        MenuEntity(
            id = it.id,
            name = it.name,
            price = it.price,
            description = it.description
        )
    }

}
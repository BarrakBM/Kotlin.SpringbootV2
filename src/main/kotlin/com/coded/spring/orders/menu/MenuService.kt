package com.coded.spring.ordering.menu

import jakarta.inject.Named
import org.springframework.beans.factory.annotation.Value
import java.math.BigDecimal
import java.math.RoundingMode

@Named
class MenuService(
    private val menuRepository: MenuRepository,

    @Value("\${feature.festive.enabled:false}")
    val festiveStatus: Boolean,
) {

    fun listMenu(): List<MenuEntity> = menuRepository.findAll().map {
        MenuEntity(
            id = it.id,
            name = it.name,
            price = if(festiveStatus) {
                // discount the price by 20%
                it.price.multiply(BigDecimal("0.8"))
            } else{
                it.price
            },
            description = it.description
        )
    }

}
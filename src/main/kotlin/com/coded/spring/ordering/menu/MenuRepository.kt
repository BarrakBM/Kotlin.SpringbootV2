package com.coded.spring.ordering.menu

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

@Named
interface MenuRepository : JpaRepository<MenuEntity, Long>{

}

@Entity
@Table(name = "menu")
data class MenuEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var price: BigDecimal,
    var description: String


){
    constructor() : this(null, "", BigDecimal.ZERO, "")
}

package com.coded.spring.ordering.items

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ItemsRepository: JpaRepository<ItemEntity, Long>

@Entity
@Table(name = "items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var quantity: Int,
    var order_id: Int,

//    @Column("'name'")


){
    constructor(): this(null, "", 0, 0)
}
package com.coded.spring.ordering.orders

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ItemsRepository: JpaRepository<Items, Long>

@Entity
@Table(name = "items")
data class Items(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var order_id: Int,
    var quantity: Int,
    var price: Double,
//    @Column("'name'")
    var name: String

){
    constructor(): this(null, 1, 1, 1.0, "")
}
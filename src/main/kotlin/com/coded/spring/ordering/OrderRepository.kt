package com.coded.spring.ordering


import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Named
interface OrderRepository : JpaRepository<Order, Long>

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    // @column since user is reserved word in sql ;/
    @Column(name = "'user'")
    var user: String,
    var restaurant: String,
    var items: MutableList<String> = mutableListOf()


){
    constructor(): this(null, LocalDateTime.now() ,"", "", mutableListOf())

}
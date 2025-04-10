package com.coded.spring.ordering


import com.coded.spring.ordering.orders.ItemEntity
import com.coded.spring.ordering.users.User
import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository : JpaRepository<OrderEntity, Long>

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val user: UserEntity,

    // join
    @OneToMany(mappedBy = "order_id")
    val items: List<ItemEntity>? = null


){
    constructor(): this(null, UserEntity(), listOf())

}
package com.coded.spring.ordering


import com.coded.spring.ordering.users.User
import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository : JpaRepository<Order, Long>

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
//    @OneToOne(targetEntity = User)
    var user_id: Long


){
    constructor(): this(null,1)

}
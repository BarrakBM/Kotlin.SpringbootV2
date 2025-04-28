package com.coded.spring.ordering


import com.coded.spring.ordering.orders.OrderEntity
import com.coded.spring.ordering.orders.OrderRepository
import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UsersRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
    val usersRepository: UsersRepository,
    val orderRepository: OrderRepository,
    @Value("\${server-welcome-message}")
    val welcomeMessage: String
){

    @GetMapping("/hello")
    fun helloWorld() =  "$welcomeMessage Barrak"


    @GetMapping("/cart")
    fun cart() =  "Your cart"

    @GetMapping("/restaurants")
    fun resturant() =  "List of resturants"

    @GetMapping("/Settings")
    fun settings() = "Settings!"

    // Post
//    @PostMapping("/my-name")
//    fun sayMyName( @RequestBody request: SayMyNameRequest) = usersRepository.save(UserEntity(name = request.name, age = request.age))

    @GetMapping("/my-name")
    fun getName(): MutableList<UserEntity> {
        return usersRepository.findAll()
    }

//    @PostMapping("/orders")
//    fun orders( @RequestBody request: OrderRequest): OrderEntity {
//        val order = (OrderEntity(null,user))
//        return orderRepository.save(order)
//    }

    @GetMapping("/orders")
    fun getOrders(): MutableList<OrderEntity>{
        return orderRepository.findAll()
    }





}

data class SayMyNameRequest(
    val name: String,
    val age: Int
)

data class OrderRequest(
    val user_id: Int,


)




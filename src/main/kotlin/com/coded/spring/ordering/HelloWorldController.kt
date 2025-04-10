package com.coded.spring.ordering


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
    val usersRepository: UsersRepository,
    val orderRepository: OrderRepository
){

    @GetMapping("/hello")
    fun helloWorld() =  "Hello World!"


    @GetMapping("/cart")
    fun cart() =  "Your cart"

    @GetMapping("/restaurants")
    fun resturant() =  "List of resturants"

    @GetMapping("/Settings")
    fun settings() = "Settings!"

    // Post
    @PostMapping("/my-name")
    fun sayMyName( @RequestBody request: SayMyNameRequest) = usersRepository.save(UserEntity(name = request.name, age = request.age))

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




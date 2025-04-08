package com.coded.spring.ordering


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

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
    fun sayMyName( @RequestBody request: SayMyNameRequest) = usersRepository.save(User(name = request.name))

    @GetMapping("/my-name")
    fun getName(): MutableList<User> {
        return usersRepository.findAll()
    }

    @PostMapping("/orders")
    fun orders( @RequestBody request: OrderRequest): Order{
        val order = (Order(null, LocalDateTime.now() ,request.user , request.restaurant, request.items.toMutableList()))
        return orderRepository.save(order)
    }

    @GetMapping("/orders")
    fun getOrders(): MutableList<Order>{
        return orderRepository.findAll()
    }



}

data class SayMyNameRequest(
    val name: String
)

data class OrderRequest(
    val user: String,
    val restaurant: String,
    val items: MutableList<String>

)




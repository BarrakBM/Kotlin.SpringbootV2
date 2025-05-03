package ordering.orders

import authentication.users.UsersService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import ordering.items.ItemDTO

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

//@Tag(name = "OrderAPI")
@RestController
class OrderController(
    private val orderService: OrderService,

) {

    @GetMapping("/orders/orders")
    fun orders(): OrdersListDTO {
        val ordersList = orderService.listOrders()
        return OrdersListDTO(orders = ordersList)
    }

    // create order
    @PostMapping("/orders/orders")
    fun createOrder(
        request: HttpServletRequest,
        @RequestBody body: CreateOrderRequest
    ): CreateOrderResponse {
        val userId = request.getAttribute("userId") as Long
        val order = orderService.createOrder(userId, body.items)
        return CreateOrderResponse(
            order.id,
            order.items
        )
    }

}

data class CreateOrderResponse(
    val id: Long?,
    val items: List<ItemDTO>?
)

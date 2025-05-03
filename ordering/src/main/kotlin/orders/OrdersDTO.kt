package ordering.orders

import ordering.items.ItemDTO

// return this when user asking for order list
data class OrderDTO(
    val id: Long?,
    val items: List<ItemDTO>?
)

data class OrdersListDTO(
    val orders: List<OrderDTO>
)

data class CreateOrderRequest(
    val items: List<ItemDTO>
)
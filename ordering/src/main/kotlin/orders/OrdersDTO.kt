package orders

import items.ItemDTO

// return this when user asking for order list
data class OrderDTO(
    val id: Long?,
    val userId: Long?,
    val items: List<ItemDTO>?
)

data class OrdersListDTO(
    val orders: List<OrderDTO>
)
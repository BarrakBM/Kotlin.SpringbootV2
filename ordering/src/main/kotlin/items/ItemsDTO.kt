package items

data class CreateItemRequestDTO(

    var name: String,
    var quantity: Int,
    var order_id: Long
)

data class ItemDTO(
    val id: Long?,
    val name: String,
    val quantity: Int,
    val orderId: Long?
)

data class ItemsListDTO(
    val items: List<ItemDTO>
)
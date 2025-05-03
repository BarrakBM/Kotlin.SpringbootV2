package items

data class CreateItemRequestDTO(

    var name: String,
    var quantity: Int,
    var order_id: Long
)

data class ItemDTO(
    val name: String,
    val quantity: Int,
)

data class ItemsListDTO(
    val items: List<ItemDTO>
)
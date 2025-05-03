//package items
//
//import orders.OrderRepository
//import jakarta.inject.Named
//
//@Named
//class ItemsService(
//    private val itemsRepository: ItemsRepository,
//    private val orderRepository: OrderRepository  // Added to fetch orders
//
//) {
//
//    fun listItems(): List<Unit> = itemsRepository.findAll().map {
//        ItemDTO(
//            id = it.id,
//            name = it.name,
//            quantity = it.quantity,
//            orderId = it.order.id
//        )
//
//    }
//
//    fun createItem(request: CreateItemRequestDTO): ItemDTO {
//        val order = orderRepository.findById(request.order_id)
//            .orElseThrow { IllegalArgumentException("Order not found with id: ${request.order_id}") }
//
//        val item = ItemEntity(
//            name = request.name,
//            quantity = request.quantity,
//            order = order
//        )
//
//        val savedItem = itemsRepository.save(item)
//
//        return ItemDTO(
//            id = savedItem.id,
//            name = savedItem.name,
//            quantity = savedItem.quantity,
//            orderId = savedItem.order.id
//        )
//    }
//
//
//}
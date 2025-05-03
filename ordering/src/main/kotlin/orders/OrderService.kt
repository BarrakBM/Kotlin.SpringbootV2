package orders

import authentication.users.UsersRepository
import items.ItemDTO
import items.ItemEntity
import items.ItemsRepository
import jakarta.inject.Named

@Named
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UsersRepository,
    private val itemsRepository: ItemsRepository
) {

    // listing orders
    fun listOrders(): List<OrderDTO> = orderRepository.findAll().map { order ->
        OrderDTO(
            id = order.id, //transfer the orderId to dto
            items = order.items?.map { item ->
                //for each item in the order, create an ItemDTO
                ItemDTO(
                    name = item.name,
                    quantity = item.quantity
                )
            }
        )
    }

    // creating new order
    fun createOrder(userId: Long, items: List<ItemDTO>): OrderDTO {
        val newOrder = OrderEntity(userId = userId)
        val savedOrder = orderRepository.save(newOrder)

        val newItems = items.map {
            ItemEntity(
                name = it.name,
                quantity = it.quantity,
                orderId = savedOrder.id!!
            )
        }
        itemsRepository.saveAll(newItems)

        return OrderDTO(
            id = savedOrder.id,
            items = newItems.map { ItemDTO(it.name, it.quantity) }
        )
    }
}


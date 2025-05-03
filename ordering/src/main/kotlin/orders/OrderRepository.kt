package ordering.orders


import jakarta.inject.Named
import jakarta.persistence.*
import ordering.items.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findByUserId(userId: Long): List<OrderEntity>
}

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    // join
    @OneToMany
    val items: List<ItemEntity>? = null


){
    constructor(): this(null, 0, listOf())

}
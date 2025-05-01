package orders


import authentication.users.UserEntity
import items.ItemEntity
import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrderRepository : JpaRepository<OrderEntity, Long>

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val user: UserEntity,

    // join
    @OneToMany(mappedBy = "order")
    val items: List<ItemEntity>? = null


){
    constructor(): this(null, UserEntity(), listOf())

}
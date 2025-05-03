package ordering.items

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ItemsRepository: JpaRepository<ItemEntity, Long>

@Entity
@Table(name = "items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var quantity: Int,

    @Column(name = "order_id")
    var orderId: Long,

//    @Column("'name'")


){
    constructor(): this(null, "", 0, 0)
}
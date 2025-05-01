package authentication.users

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface UsersRepository : JpaRepository<UserEntity, Long> {
    fun age(age: Int): MutableList<UserEntity>
    fun findByUsername(username: String): UserEntity?
    fun existsByUsername(username: String): Boolean
}

@Entity
@Table(name = "authentication/users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var age: Int,

    @Column(unique = true) // make sure it's unique
    var username: String,

    var password: String,

//    @Enumerated(EnumType.STRING)
//    val role: Roles = Roles.USER

){
    constructor() : this(null, "", 0,"username","password")
}

enum class Roles {
    USER, ADMIN
}



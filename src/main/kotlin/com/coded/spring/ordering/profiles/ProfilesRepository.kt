package com.coded.spring.ordering.profiles


import com.coded.spring.ordering.users.UserEntity
import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository



@Named
interface ProfilesRepository : JpaRepository<ProfileEntity, Long> {
    fun findByUserId(userId: UserEntity): ProfileEntity?
}

@Entity
@Table(name = "profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    //one user can have one profile
    @OneToOne
    @JoinColumn(name = "user_id")// foreign key for Users(Id)
    var userId: UserEntity,

    var firstName: String,
    var lastName: String,
    var phoneNumber: String,

){ constructor() : this(null, UserEntity(), "", "", "")
}
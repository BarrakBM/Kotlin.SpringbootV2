package com.coded.spring.ordering

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface UsersRepository : JpaRepository<User, Long>

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String
){
    constructor() : this(null, "")
}



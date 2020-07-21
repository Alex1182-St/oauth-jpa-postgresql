package com.example.oauthjpapostgresql.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "appUsers")
data class AppUserEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(updatable = false, nullable = false, unique = true)
        val id : UUID = UUID.randomUUID(),

        @Column(nullable = false, unique = true)
        val appUserLogin : String,

        @Column(nullable = false)
        val appUserPassword : String,

        @field:ManyToMany(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.PERSIST, CascadeType.MERGE]
        )
        @field:JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "appUser_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        val roles : Collection<RoleEntity> = setOf(),

        @field:Column(nullable = false)
        val isEnabled : Boolean = true,

        @field:Column(nullable = false)
        val isAccountNonLocked : Boolean = true,

        @field:Column(nullable = false)
        val isAccountNonExpired : Boolean = true,

        @field:Column(nullable = false)
        val isCredentialsNonExpired : Boolean = true,

        @Column(nullable = false, unique = true)
        val email : String,

        @Column(nullable = true)
        val appUserSurname : String? = null,

        @Column(nullable = true)
        val appUserName : String? = null,

        @Column(nullable = true, unique = true)
        val phone : String? = null,

        @Column(nullable = true, unique = true)
        val skype : String? = null,

        @Column(nullable = true)
        val userAge : Int? = null

        )
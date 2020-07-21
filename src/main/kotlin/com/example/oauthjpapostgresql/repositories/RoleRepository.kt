package com.example.oauthjpapostgresql.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.example.oauthjpapostgresql.entities.RoleEntity
import com.example.oauthjpapostgresql.entities.RoleEnum
import java.util.*

@Repository
interface RoleRepository : JpaRepository<RoleEntity, UUID> {

    fun findByName(name : RoleEnum) : RoleEntity

}
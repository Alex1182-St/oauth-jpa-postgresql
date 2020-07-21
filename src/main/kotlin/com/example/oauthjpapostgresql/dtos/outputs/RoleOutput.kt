package com.example.oauthjpapostgresql.dtos.outputs
import com.example.oauthjpapostgresql.entities.RoleEnum
import java.util.*

data class RoleOutput(

        val id : UUID,

        val name : RoleEnum,

        val description : String

)

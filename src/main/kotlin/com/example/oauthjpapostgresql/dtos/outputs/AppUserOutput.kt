package com.example.oauthjpapostgresql.dtos.outputs

import java.util.*

data class AppUserOutput(

        val id: UUID,

        val appUserLogin : String,

        val appUserPassword : String,

        val roles : Collection<UUID>,

        val email : String,

        val appUserSurname : String?,

        val appUserName : String?,

        val skype : String?,

        val userAge : Int?
)
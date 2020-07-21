package com.example.oauthjpapostgresql.dtos.inputs


data class AppUserCreateInput(

        val appUserLogin : String,

        val appUserPassword : String,

        val email : String,

        val appUserSurname : String?,

        val appUserName : String?,

        val phone : String?,

        val skype : String?,

        val userAge : Int?
)
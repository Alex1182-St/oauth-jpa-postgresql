package com.example.oauthjpapostgresql.entities

import org.springframework.security.core.GrantedAuthority

enum class RoleEnum : GrantedAuthority {

    USER,

    ADMIN;

    override fun getAuthority() : String = "ROLE_${name}"


}
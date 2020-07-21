package com.example.oauthjpapostgresql.dtos.convertes

import com.example.oauthjpapostgresql.dtos.userdetails.AppUserDetailsDTO
import com.example.oauthjpapostgresql.dtos.outputs.AppUserOutput
import com.example.oauthjpapostgresql.entities.AppUserEntity

fun AppUserEntity.toOutput() = AppUserOutput(
        id = id,
        appUserLogin = appUserLogin,
        appUserPassword = appUserPassword,
        roles = roles.map { it.id },
        email = email,
        appUserSurname = appUserSurname,
        appUserName = appUserName,
        skype = skype,
        userAge = userAge
)

fun AppUserEntity.toAppUserDetailsDTO() = AppUserDetailsDTO(
        id = id,
        username = appUserLogin,
        password = appUserPassword,
        authorities = roles.map { it.name },
        isEnabled               = isEnabled,
        isAccountNonLocked      = isAccountNonLocked,
        isAccountNonExpired     = isAccountNonExpired,
        isCredentialsNonExpired = isCredentialsNonExpired
)
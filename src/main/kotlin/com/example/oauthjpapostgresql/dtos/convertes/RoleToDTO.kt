package com.example.oauthjpapostgresql.dtos.convertes
import com.example.oauthjpapostgresql.dtos.outputs.RoleOutput
import com.example.oauthjpapostgresql.entities.RoleEntity

fun RoleEntity.toOutput() : RoleOutput = RoleOutput(
    id          = id,
    name        = name,
    description = description
)

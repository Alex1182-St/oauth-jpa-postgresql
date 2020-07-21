package com.example.oauthjpapostgresql.controllers

import com.example.oauthjpapostgresql.dtos.convertes.toOutput
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.oauthjpapostgresql.dtos.outputs.RoleOutput
import com.example.oauthjpapostgresql.repositories.RoleRepository
import javax.transaction.Transactional

@Transactional
@RestController
@RequestMapping("role")
class RoleController (val roleRepo : RoleRepository) {

    @GetMapping("all")
    fun findAll() : Collection<RoleOutput> =
            roleRepo.findAll().map { it.toOutput() }
}
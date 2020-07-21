package com.example.oauthjpapostgresql.controllers

import org.springframework.security.crypto.password.PasswordEncoder
import com.example.oauthjpapostgresql.dtos.convertes.toOutput
import com.example.oauthjpapostgresql.dtos.inputs.AppUserCreateInput
import com.example.oauthjpapostgresql.dtos.outputs.AppUserOutput
import com.example.oauthjpapostgresql.entities.AppUserEntity
import com.example.oauthjpapostgresql.repositories.AppUserRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.oauthjpapostgresql.entities.RoleEnum
import com.example.oauthjpapostgresql.repositories.RoleRepository
import javax.transaction.Transactional

@Transactional
@RestController
@RequestMapping("app")
class AppUserSignUpController (private val appUserRepo : AppUserRepository,
                               private val roleRepo : RoleRepository,
                               private val passwordEncoder : PasswordEncoder) {

@PostMapping("sign_up")
fun appUserCreate(@RequestBody newAppUser : AppUserCreateInput) : AppUserOutput {

    val role = roleRepo.findByName(RoleEnum.USER)

    val appUser : AppUserEntity = AppUserEntity(
            appUserLogin = newAppUser.appUserLogin,
            appUserPassword = passwordEncoder.encode(newAppUser.appUserPassword),
            roles = setOf(role),
            email = newAppUser.email,
            appUserSurname = newAppUser.appUserSurname,
            appUserName = newAppUser.appUserName,
            phone = newAppUser.phone,
            skype = newAppUser.skype,
            userAge = newAppUser.userAge
    )
    return appUserRepo.save(appUser).toOutput()

}
}
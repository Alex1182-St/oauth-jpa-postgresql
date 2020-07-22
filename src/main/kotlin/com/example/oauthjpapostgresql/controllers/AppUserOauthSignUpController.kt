package com.example.oauthjpapostgresql.controllers

import com.example.oauthjpapostgresql.dtos.convertes.toAppUserDetailsDTO
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
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import javax.transaction.Transactional


@Transactional
@RestController
@RequestMapping("app")
class AppUserOauthSignUpController (

        @Qualifier("accessTokenConverter")
        private val appUserRepo : AppUserRepository,
        private val roleRepo : RoleRepository,
        private val accessTokenConverter : JwtAccessTokenConverter,
        private val tokenServices : DefaultTokenServices
) {

@PostMapping("sign_up")
fun appUserSignUp(auth : OAuth2Authentication, @RequestBody newAppUser : AppUserCreateInput) : OAuth2AccessToken {

    val role = roleRepo.findByName(RoleEnum.USER)

    val appUser : AppUserEntity = AppUserEntity(
            appUserLogin = newAppUser.appUserLogin,
            appUserPassword = newAppUser.appUserPassword,
            appUserName = newAppUser.appUserName,
            appUserSurname = newAppUser.appUserSurname,
            roles = setOf(role),
            email = newAppUser.email
    )

    val appUserDetails = appUserRepo.save(appUser).toAppUserDetailsDTO()

    val authentication : OAuth2Authentication = OAuth2Authentication(
            auth.oAuth2Request,
            UsernamePasswordAuthenticationToken(
                    appUserDetails,
                    appUserDetails.password,
                    appUserDetails.authorities
            )
    )

    val accessToken = accessTokenConverter.enhance(
            tokenServices.createAccessToken(authentication),
            authentication
    )

    return  accessToken
}
}
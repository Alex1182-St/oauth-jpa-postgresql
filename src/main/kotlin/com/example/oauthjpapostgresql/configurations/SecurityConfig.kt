package com.example.oauthjpapostgresql.configurations

import com.example.oauthjpapostgresql.services.AppUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig (

        private val passwordEncoder: PasswordEncoder,

        private val appUserDetailsService : AppUserDetailsService

) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean() : AuthenticationManager =
            super.authenticationManagerBean()

    override fun configure(http: HttpSecurity) {

        http.csrf().disable()

        http
                .httpBasic().disable()
                .formLogin().disable()

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
                .anyRequest().permitAll()

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder)
    }
}



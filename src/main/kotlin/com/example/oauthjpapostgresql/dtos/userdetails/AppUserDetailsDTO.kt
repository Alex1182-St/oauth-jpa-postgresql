package com.example.oauthjpapostgresql.dtos.userdetails

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class AppUserDetailsDTO(

        private val id : UUID,

        private val username : String,

        private val password : String,

        private val authorities : Collection<GrantedAuthority>,

        private val isEnabled : Boolean,

        private val isAccountNonLocked : Boolean,

        private val isAccountNonExpired : Boolean,

        private val isCredentialsNonExpired : Boolean

) : UserDetails {

    fun getId() : UUID {
        return this.id
    }

    override fun getUsername() : String {
        return this.username
    }

    override fun getPassword() : String {
        return this.password
    }

    override fun getAuthorities() : Collection<GrantedAuthority> {
        return this.authorities
    }

    override fun isEnabled() : Boolean {
        return this.isEnabled
    }

    override fun isCredentialsNonExpired() : Boolean {
        return this.isCredentialsNonExpired
    }

    override fun isAccountNonExpired() : Boolean {
        return this.isAccountNonExpired
    }

    override fun isAccountNonLocked() : Boolean {
        return this.isAccountNonLocked
    }
}
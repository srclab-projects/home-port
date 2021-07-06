package xyz.srclab.home.port.server.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

open class HomePortUserDetailService : UserDetailsService{

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}
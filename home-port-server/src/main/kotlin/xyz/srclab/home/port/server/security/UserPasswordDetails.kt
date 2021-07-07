package xyz.srclab.home.port.server.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

open class UserPasswordDetails(
    private val userPassword: UserPasswordEntity,
    private val authorities: List<String>
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authorities.map {
            GrantedAuthority { it }
        }.toMutableList()

    override fun getPassword(): String = userPassword.passwordEncoded!!

    override fun getUsername(): String = userPassword.username!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
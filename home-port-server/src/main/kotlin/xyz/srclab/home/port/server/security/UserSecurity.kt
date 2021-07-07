package xyz.srclab.home.port.server.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import xyz.srclab.home.port.server.dao.user.UserPasswordEntity

open class UserSecurity(
    private val userPassword: UserPasswordEntity,
    authorities: List<String>
) : UserDetails {

    private var grantedAuthorities: MutableCollection<out GrantedAuthority> =
        authorities.map {
            SimpleGrantedAuthority(it)
        }.toMutableList()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return grantedAuthorities
    }

    override fun getPassword(): String = userPassword.passwordEncoded!!

    override fun getUsername(): String = userPassword.username!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    open fun setAuthorities(grantedAuthorities: MutableCollection<out GrantedAuthority>) {
        this.grantedAuthorities = grantedAuthorities
    }
}
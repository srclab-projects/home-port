package xyz.srclab.home.port.server.security

import org.springframework.data.domain.Example
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import xyz.srclab.home.port.server.dao.user.*
import java.util.*
import kotlin.collections.HashSet

@Component
open class HomePortUserDetailService : UserDetailsService {

    private lateinit var userPasswordDao: UserPasswordDao

    private lateinit var roleInfoDao: RoleInfoDao

    private lateinit var userAuthorityDao: UserAuthorityDao

    override fun loadUserByUsername(username: String): UserDetails {
        val userPasswordExample = UserPasswordEntity()
        userPasswordExample.username = username
        val userPassword = userPasswordDao.findOne(Example.of(userPasswordExample)).orElse(null)
        if (userPassword === null) {
            throw UsernameNotFoundException("User not found: $username")
        }
    }
}
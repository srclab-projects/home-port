package xyz.srclab.home.port.server.security

import org.springframework.data.domain.Example
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
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

        val userAuthorityExample = UserAuthorityEntity()
        userAuthorityExample.ownerId = userPassword.id
        userAuthorityExample.ownerType = UserAuthorityEntity.OwnerType.USER
        val authorities:MutableSet<String> = HashSet()
        val roles:MutableSet<String> = HashSet()

        fun findAllAuthorities(){
            for (userAuthorityEntity in userAuthorityDao.findAll(Example.of(userAuthorityExample))) {
                if (userAuthorityEntity.authorityType == UserAuthorityEntity.AuthorityType.AUTHORITY) {
                    authorities.add(userAuthorityEntity.authority!!)
                    continue
                }
                if (userAuthorityEntity.authorityType == UserAuthorityEntity.AuthorityType.ROLE) {
                    authorities.add(userAuthorityEntity.authority!!)
                    roles.add(userAuthorityEntity.authority!!)
                    continue
                }
            }
        }


    }
}
package xyz.srclab.home.port.server.service.user

import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import xyz.srclab.home.port.server.dao.user.*
import java.util.*
import javax.annotation.Resource

@Service
open class UserService {

    @Resource
    private lateinit var roleInfoDao: RoleInfoDao

    @Resource
    private lateinit var userAuthorityDao: UserAuthorityDao

    @Resource
    private lateinit var userRoleDao: UserRoleDao

    @Resource
    private lateinit var roleAuthorityDao: RoleAuthorityDao

    open fun findUserAuthorities(userId: String): List<String> {
        val authorities: MutableSet<String> = HashSet()
        val roles: MutableSet<String> = HashSet()

        val userAuthorityProbe = UserAuthorityEntity()
        userAuthorityProbe.userId = userId
        for (userAuthorityEntity in userAuthorityDao.findAll(Example.of(userAuthorityProbe))) {
            authorities.add(userAuthorityEntity.authority!!)
        }
        val userRoleProbe = UserRoleEntity()
        userRoleProbe.userId = userId
        for (userRoleEntity in userRoleDao.findAll(Example.of(userRoleProbe))) {
            roles.add(userRoleEntity.roleName!!)
        }

        fun tryEraseRoles(roleNames: Collection<String>): Collection<String> {
            val restRoles = LinkedList<String>()
            for (roleAuthorityEntity in roleAuthorityDao.findAllInRoleNames(roleNames)) {
                if (roleAuthorityEntity.authorityType == RoleAuthorityEntity.AuthorityType.AUTHORITY) {
                    authorities.add(roleAuthorityEntity.authority!!)
                    continue
                }
                if (roleAuthorityEntity.authorityType == RoleAuthorityEntity.AuthorityType.ROLE) {
                    if (roles.contains(roleAuthorityEntity.authority)) {
                        continue
                    }
                    roles.add(roleAuthorityEntity.authority!!)
                    restRoles.add(roleAuthorityEntity.authority!!)
                }
            }
            return restRoles
        }

        var restRoles: Collection<String> = roles
        while (restRoles.isNotEmpty()) {
            restRoles = tryEraseRoles(restRoles)
        }

        val result = LinkedList<String>()
        result.addAll(roles.map { "ROLE_$it" })
        result.addAll(authorities)
        return result
    }
}
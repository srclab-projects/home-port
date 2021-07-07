package xyz.srclab.home.port.server.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.RememberMeServices
import org.springframework.stereotype.Service
import xyz.srclab.common.lang.asAny
import xyz.srclab.home.port.server.dao.user.RoleAuthorityDao
import xyz.srclab.home.port.server.dao.user.RoleInfoDao
import xyz.srclab.home.port.server.dao.user.UserAuthorityDao
import xyz.srclab.home.port.server.dao.user.UserRoleDao
import xyz.srclab.home.port.server.service.user.UserService
import javax.annotation.Resource

@Service
open class SecurityService {

    @Resource
    private lateinit var roleInfoDao: RoleInfoDao

    @Resource
    private lateinit var userAuthorityDao: UserAuthorityDao

    @Resource
    private lateinit var userRoleDao: UserRoleDao

    @Resource
    private lateinit var roleAuthorityDao: RoleAuthorityDao

    @Resource
    private lateinit var userService: UserService

    private lateinit var rememberMeService: RememberMeServices

    open fun getAuthentication(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }

    open fun getCurrentUserSecurity(): UserSecurity {
        return getAuthentication().principal.asAny()
    }
}
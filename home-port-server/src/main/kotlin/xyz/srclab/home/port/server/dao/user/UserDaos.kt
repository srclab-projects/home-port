package xyz.srclab.home.port.server.dao.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserPasswordDao : JpaRepository<UserPasswordEntity, String>

interface RoleInfoDao : JpaRepository<RoleInfoEntity, String>

interface UserAuthorityDao : JpaRepository<UserAuthorityEntity, String>

interface UserRoleDao : JpaRepository<UserRoleEntity, String>

interface RoleAuthorityDao : JpaRepository<RoleAuthorityEntity, String> {

    @Query("select ra from RoleAuthorityEntity ra where ra.roleName in ?1")
    fun findAllInRoleNames(roleNames: Iterable<String>): List<RoleAuthorityEntity>
}
package xyz.srclab.home.port.server.security

import org.springframework.data.jpa.repository.JpaRepository

interface UserPasswordDao : JpaRepository<UserPasswordEntity, String> {
}

interface RoleInfoDao : JpaRepository<RoleInfoEntity, String> {
}

interface UserAuthorityDao : JpaRepository<UserAuthorityEntity, String> {
}
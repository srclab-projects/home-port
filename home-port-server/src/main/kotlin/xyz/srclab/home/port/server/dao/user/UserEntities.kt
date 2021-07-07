package xyz.srclab.home.port.server.dao.user

import javax.persistence.*

@Entity
@Table(name = "USER_PASSWORD")
open class UserPasswordEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 64, nullable = false, unique = true)
    open var username: String? = null

    @Column(length = 64)
    open var passwordPlain: String? = null

    @Column(length = 1024, nullable = false)
    open var passwordEncoded: String? = null
}

@Entity
@Table(name = "ROLE_INFO")
open class RoleInfoEntity {

    @Id
    @Column(length = 64)
    open var roleName: String? = null

    @Column(length = 1024)
    open var description: String? = null
}

@Entity
@Table(name = "USER_AUTHORITY")
open class UserAuthorityEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 32, nullable = false)
    open var userId: String? = null

    @Column(length = 64, nullable = false)
    open var authority: String? = null
}

@Entity
@Table(name = "USER_ROLE")
open class UserRoleEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 32, nullable = false)
    open var userId: String? = null

    @Column(length = 64, nullable = false)
    open var roleName: String? = null
}

@Entity
@Table(name = "ROLE_AUTHORITY")
open class RoleAuthorityEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 64, nullable = false)
    open var roleName: String? = null

    @Column(length = 64, nullable = false)
    open var authority: String? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 16, nullable = false)
    open var authorityType: AuthorityType? = null

    enum class AuthorityType {
        ROLE, AUTHORITY
    }
}
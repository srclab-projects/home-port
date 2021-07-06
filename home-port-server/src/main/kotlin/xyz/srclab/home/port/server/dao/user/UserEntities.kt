package xyz.srclab.home.port.server.dao.user

import javax.persistence.*

@Entity
@Table(name = "USER_PASSWORD")
open class UserPasswordEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 64)
    open var username: String? = null

    @Column(length = 64)
    open var passwordPlain: String? = null

    @Column(length = 1024)
    open var passwordEncoded: ByteArray? = null
}


@Entity
@Table(name = "ROLE_INFO")
open class RoleInfoEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 64)
    open var roleName: String? = null
}

@Entity
@Table(name = "USER_AUTHORITY")
open class UserAuthorityEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 32)
    open var ownerId: String? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    open var ownerType: OwnerType? = null

    @Column(length = 32)
    open var authority: String? = null

    enum class OwnerType {
        USER, ROLE
    }
}

@Entity
@Table(name = "USER_Role")
open class UserRoleEntity {

    @Id
    @Column(length = 32)
    open var id: String? = null

    @Column(length = 32)
    open var userId: String? = null

    @Column(length = 32)
    open var authority: String? = null
}
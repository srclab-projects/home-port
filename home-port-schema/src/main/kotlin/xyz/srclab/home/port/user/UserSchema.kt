package xyz.srclab.home.port.user

/**
 * User information.
 */
open class UserSchema(
    open val id: String,
    open var group: String,
    open var type: UserType,
    open var name: String = "u_$id",
    open var mail: String? = null,
    open var phone: String? = null,
)

enum class UserType {

}
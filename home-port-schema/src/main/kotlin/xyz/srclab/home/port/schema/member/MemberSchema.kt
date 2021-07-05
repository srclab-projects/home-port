package xyz.srclab.home.port.schema.member

/**
 * Home member information.
 */
open class MemberSchema {

    /**
     * Unique id.
     */
    open var id: String = ""

    open var name: String = "u_$id"

    /**
     * With pattern: yyyyMMddHHmmss
     *
     * Defaults.timestampPattern
     */
    open var birthday: String? = null

    open var mail: String? = null
    open var phone: String? = null


}


enum class UserType {

}

enum class UserCategory {

}
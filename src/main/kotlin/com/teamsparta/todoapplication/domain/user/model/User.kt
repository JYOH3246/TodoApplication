package com.teamsparta.todoapplication.domain.user.model

import com.teamsparta.todoapplication.domain.BaseTimeEntity
import com.teamsparta.todoapplication.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "nickname")
    val nickname: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,


    ) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    fun checkEmailAndNickname(checkEmail:Boolean, checkNickname: Boolean) {
        when {
            (checkEmail) -> {
                throw IllegalStateException("Email is already in use")
            }

            else -> when {
                (checkNickname)-> {
                    throw IllegalStateException("Nickname is already in use")
                }
            }
        }
    }


}






fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = nickname,
        email = email,
        role = role.name

    )
}
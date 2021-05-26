package net.franzka.courses.models

data class BodyForgottenPassword(
    val login: String
)

data class BodyToken(
    val token: String
)

data class BodyChangePassword(
    val token: String,
    val password: String
)

data class BodyHomeCreate(
    val token: String,
    val name: String
)

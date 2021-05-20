package net.franzka.courses.models

data class LoginFP(
    val login: String
)

data class Token(
    val token:String
)

data class ChangePassword(
    val token:String,
    val password: String
)
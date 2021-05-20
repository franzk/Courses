package net.franzka.courses.models

data class CoursesAPIResponse(
    val status: String,
    val message: String,
    val result: String
)

data class SignInAPIResponse(
    val status: String,
    val message: String,
    val result: Login
) {
    data class Login(
            val token: String,
            val username: String
    )
}

data class ConnectionCountAPIResponse(
        val status: String,
        val message: String,
        val result: ConnectionCount
) {
    data class ConnectionCount(
            val count: Int
    )
}

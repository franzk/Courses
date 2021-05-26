package net.franzka.courses

class AppToken private constructor() {
    var token: String? = null
    companion object {
        val instance = AppToken()
    }
}
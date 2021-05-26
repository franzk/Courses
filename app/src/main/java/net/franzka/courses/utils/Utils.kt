package net.franzka.courses.utils

import net.franzka.courses.AppToken

class Utils {

    companion object {
        fun setAppToken(token: String?) {
            val appToken: AppToken = AppToken.instance
            appToken.token = token
        }

        fun getAppToken(): String {
            val appToken: AppToken = AppToken.instance
            return appToken.token ?: ""
        }
    }

}
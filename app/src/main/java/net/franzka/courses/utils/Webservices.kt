package net.franzka.courses.utils

class Webservices {

    companion object {

        const val SIGN_UP = "user/signUp.php"
        const val SIGN_IN = "user/signIn.php"
        const val SIGN_IN_PARAM_LOGIN = "login"
        const val SIGN_IN_PARAM_PASSWORD = "password"
        const val FORGOTTEN_PASSWORD = "user/forgottenPassword.php"
        const val CHANGE_PASSWORD = "user/changePassword.php"
        const val SIGN_OUT = "user/signOut.php"
        const val SIGN_OUT_ALL = "user/signOutAll.php"
        const val CONNECTION_COUNT = "user/connectionCount.php"
        const val CONNECTION_COUNT_PARAM_TOKEN = "token"

        const val HOME_CREATE = "home/create"
        const val HOME_MY_HOMES = "home/myHomes"
        const val HOME_MY_HOMES_PARAM_TOKEN = "token"
        const val HOME_MY_HOMES_PARAM_ITEMS_NUMBER = "itemsNumber"
        const val HOME_MY_HOMES_PARAM_PAGE_NUMBER = "pageNumber"

    }

}
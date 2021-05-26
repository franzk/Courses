package net.franzka.courses.repository

import net.franzka.courses.api.RetrofitInstance
import net.franzka.courses.models.*
import net.franzka.courses.utils.APIConstants
import retrofit2.Response
import java.lang.Exception

class LoginRepository {

    suspend fun signUp(username: String, email: String, password: String): Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.signUp(User(username, email, password))
        }
        catch(e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun signIn(login: String, password: String): Response<SignInAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.signIn(login, password)
        } catch (e: Exception) {
            Response.success(
                    SignInAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE,
                            SignInAPIResponse.Login("", "")))
        }
    }

    suspend fun forgottenPassword(login: String) : Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.forgottenPassword(BodyForgottenPassword(login))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun changePassword(token: String, password: String) : Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.changePassword(BodyChangePassword(token, password))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun signOut(token: String): Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.signOut(BodyToken(token))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun signOutAll(token: String): Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.signOutAll(BodyToken(token))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun connectionCount(token: String): Response<ConnectionCountAPIResponse> {
        return try {
            RetrofitInstance.loginAPI.connectionCount(token)
        } catch (e: Exception) {
            Response.success(
                    ConnectionCountAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE,
                            ConnectionCountAPIResponse.ConnectionCount(0)))
        }
    }
}
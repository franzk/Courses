package net.franzka.courses.repository

import net.franzka.courses.api.RetrofitInstance
import net.franzka.courses.models.*
import net.franzka.courses.utils.APIConstants
import retrofit2.Response
import java.lang.Exception

class Repository {

    suspend fun signUp(username: String, email: String, password: String): Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.api.signUp(User(username, email, password))
        }
        catch(e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun signIn(login: String, password: String): Response<SignInAPIResponse> {
        return try {
            RetrofitInstance.api.signIn(login, password)
        } catch (e: Exception) {
            Response.success(
                    SignInAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE,
                            SignInAPIResponse.Login("", "")))
        }
    }

    suspend fun forgottenPassword(login: String) : Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.api.forgottenPassword(LoginFP(login))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun changePassword(token: String, password: String) : Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.api.changePassword(ChangePassword(token, password))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun signOut(token: String): Response<CoursesAPIResponse> {
        return try {
            RetrofitInstance.api.signOut(Token(token))
        } catch (e: Exception) {
            Response.success(CoursesAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, ""))
        }
    }

    suspend fun connectionCount(token: String): Response<ConnectionCountAPIResponse> {
        return try {
            RetrofitInstance.api.connectionCount(token)
        } catch (e: Exception) {
            Response.success(
                    ConnectionCountAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE,
                            ConnectionCountAPIResponse.ConnectionCount(0)))
        }
    }
}
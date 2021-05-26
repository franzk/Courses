package net.franzka.courses.api

import net.franzka.courses.models.*
import net.franzka.courses.utils.Webservices
import retrofit2.Response
import retrofit2.http.*


interface LoginAPI {

    @POST(Webservices.SIGN_UP)
    suspend fun signUp(
        @Body user: User
    ): Response<CoursesAPIResponse>

    @GET(Webservices.SIGN_IN)
    suspend fun signIn(
        @Query(Webservices.SIGN_IN_PARAM_LOGIN) login: String,
        @Query(Webservices.SIGN_IN_PARAM_PASSWORD) password: String
    ): Response<SignInAPIResponse>

    @PUT(Webservices.FORGOTTEN_PASSWORD)
    suspend fun forgottenPassword(
        @Body bodyForgottenPassword: BodyForgottenPassword
    ): Response<CoursesAPIResponse>

    @PUT(Webservices.CHANGE_PASSWORD)
    suspend fun changePassword(
        @Body bodyChangePassword: BodyChangePassword
    ): Response<CoursesAPIResponse>

    @HTTP(method = "DELETE", path = Webservices.SIGN_OUT, hasBody = true)
    suspend fun signOut(
            @Body token: BodyToken
    ): Response<CoursesAPIResponse>

    @HTTP(method = "DELETE", path = Webservices.SIGN_OUT_ALL, hasBody = true)
    suspend fun signOutAll(
        @Body token: BodyToken
    ): Response<CoursesAPIResponse>

    @GET(Webservices.CONNECTION_COUNT)
    suspend fun connectionCount(
            @Query(Webservices.CONNECTION_COUNT_PARAM_TOKEN) token: String
    ): Response<ConnectionCountAPIResponse>


}

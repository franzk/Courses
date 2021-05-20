package net.franzka.courses.api

import net.franzka.courses.models.*
import retrofit2.Response
import retrofit2.http.*


interface CoursesAPI {

    @POST("user/signUp.php")
    suspend fun signUp(
        @Body user: User
    ): Response<CoursesAPIResponse>

    @GET("user/signIn.php")
    suspend fun signIn(
        @Query("login") login: String,
        @Query("password") password: String
    ): Response<SignInAPIResponse>

    @PUT("user/forgottenPassword.php")
    suspend fun forgottenPassword(
        @Body login: LoginFP
    ): Response<CoursesAPIResponse>

    @PUT("user/changePassword.php")
    suspend fun changePassword(
        @Body changePassword: ChangePassword
    ): Response<CoursesAPIResponse>

    //@DELETE("user/signOut.php")
    @HTTP(method = "DELETE", path = "user/signOut.php", hasBody = true)
    suspend fun signOut(
            @Body token: Token
    ): Response<CoursesAPIResponse>


    @GET("user/connectionCount.php")
    suspend fun connectionCount(
            @Query("token") login: String
    ): Response<ConnectionCountAPIResponse>


}

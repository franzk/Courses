package net.franzka.courses.api

import net.franzka.courses.models.BodyHomeCreate
import net.franzka.courses.models.HomeCreateAPIResponse
import net.franzka.courses.models.HomeMyHomesAPIResponse
import net.franzka.courses.utils.Webservices
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeAPI {

    @POST(Webservices.HOME_CREATE)
    suspend fun create(
        @Body bodyHomeCreate: BodyHomeCreate
    ): Response<HomeCreateAPIResponse>

    @GET(Webservices.HOME_MY_HOMES)
    suspend fun myHomes(
        @Query(Webservices.HOME_MY_HOMES_PARAM_TOKEN) token: String
    ): Response<HomeMyHomesAPIResponse>

}
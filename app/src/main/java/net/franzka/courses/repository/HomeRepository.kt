package net.franzka.courses.repository

import net.franzka.courses.api.RetrofitInstance
import net.franzka.courses.models.*
import net.franzka.courses.utils.APIConstants
import retrofit2.Response

class HomeRepository {

    suspend fun create(name: String, token: String): Response<HomeCreateAPIResponse> {
        return try {
            RetrofitInstance.homeAPI.create(BodyHomeCreate(token, name))
        }
        catch(e: Exception) {
            Response.success(HomeCreateAPIResponse(APIConstants.FALSE, APIConstants.CONNECTION_ERROR_MESSAGE, HomeCreateAPIResponse.HomeId(-1)))
        }
    }

    suspend fun myHomes(token: String): Response<HomeMyHomesAPIResponse> {
        return try {
            RetrofitInstance.homeAPI.myHomes(token)
        } catch (e: java.lang.Exception) {
            Response.success(
                HomeMyHomesAPIResponse(
                    APIConstants.FALSE,
                    APIConstants.CONNECTION_ERROR_MESSAGE,
                    HomeMyHomesAPIResponse.MyHomes(0, 0, listOf())
            ))
        }
    }

}
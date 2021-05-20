package net.franzka.courses.api

import net.franzka.courses.utils.APIConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CoursesAPI by lazy {
        retrofit.create(CoursesAPI::class.java)
    }

}
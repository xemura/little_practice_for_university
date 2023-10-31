package com.xenia.practice.interfaceModel

import com.xenia.practice.model.ObjectData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitServicesInterface {
    @GET("api/users?page=1")
    @Headers("Content-Type: application/json")
    fun getUserList(): Call<ObjectData>
    companion object {
        private const val BASE_URL = "https://reqres.in/"

        fun create(): RetrofitServicesInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RetrofitServicesInterface::class.java)
        }
    }
}
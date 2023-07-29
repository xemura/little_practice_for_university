package com.xenia.practice.interfaceModel

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.xenia.practice.model.ObjectData
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitServicesInterface {
    @GET("api/users?page=1")
    @Headers("Content-Type: application/json")
    fun getUserList(): Call<ObjectData>

    companion object {
        private const val BASE_URL = "https://reqres.in/"

        private val contentType: MediaType
            get() = "application/json".toMediaType()
        @OptIn(ExperimentalSerializationApi::class)
        val converterFactory: Converter.Factory
            get() = Json.asConverterFactory(contentType)

        fun create(): RetrofitServicesInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RetrofitServicesInterface::class.java)
        }
    }
}
package com.scolley.logging.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://watch-master-staging.herokuapp.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

private val retrofit  = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface LoginApiApiService {

    @GET("login")
    suspend fun login(@Header("X-Parse-Application-Id")id: String,
                      @Query("username") userName: String,
                      @Query("password") password: String): User

    @PUT("users/{userId}")
    suspend fun updateUser(@Header("X-Parse-Application-Id")id: String,
                           @Header("X-Parse-Session-Token") token: String,
                           @Header("Content-Type") type: String,
                           @Body timezone: TimeZone,
                           @Path("userId") objectId: String): TimezoneResponse

}

object LoginApi {

    val retrofitService : LoginApiApiService by lazy { retrofit.create(LoginApiApiService::class.java) }

}

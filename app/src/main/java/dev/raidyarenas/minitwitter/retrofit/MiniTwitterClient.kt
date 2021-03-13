package dev.raidyarenas.minitwitter.retrofit

import dev.raidyarenas.minitwitter.common.Constants
import dev.raidyarenas.minitwitter.services.AuthService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MiniTwitterClient {
    private val clientHttp = OkHttpClient
            .Builder()
            .addInterceptor(AuthInterceptor)
            .build()
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_MINI_TWITTER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientHttp)
        .build()
    private var authService: AuthService = retrofit.create(AuthService::class.java)
    fun <T> buildService(service: Class<T>) : T {
        return retrofit.create(service)
    }
}
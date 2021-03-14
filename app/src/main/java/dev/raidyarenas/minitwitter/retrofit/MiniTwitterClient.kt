package dev.raidyarenas.minitwitter.retrofit

import android.util.Log
import dev.raidyarenas.minitwitter.common.Constants
import dev.raidyarenas.minitwitter.common.SharedPreferencesManager
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
    fun <T> buildService(service: Class<T>) : T {
        Log.i("PREFERENCES", SharedPreferencesManager.all.toString())
        return retrofit.create(service)
    }
}
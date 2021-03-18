package dev.raidyarenas.minitwitter.retrofit

import dev.raidyarenas.minitwitter.common.Constants
import dev.raidyarenas.minitwitter.common.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

object AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = SharedPreferencesManager.getString(Constants.PREFERENCE_TOKEN, null)
        var request: Request = chain.request()
        if (token != null)
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        return chain.proceed(request)
    }
}
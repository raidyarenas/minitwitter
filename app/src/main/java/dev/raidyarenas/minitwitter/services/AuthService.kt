package dev.raidyarenas.minitwitter.services

import dev.raidyarenas.minitwitter.requests.SignInRequest
import dev.raidyarenas.minitwitter.requests.SignUpRequest
import dev.raidyarenas.minitwitter.responses.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    fun doSignIn(@Body signInRequest: SignInRequest): Call<AuthResponse>

    @POST("auth/signup")
    fun doSignUp(@Body signUpRequest: SignUpRequest): Call<AuthResponse>

}
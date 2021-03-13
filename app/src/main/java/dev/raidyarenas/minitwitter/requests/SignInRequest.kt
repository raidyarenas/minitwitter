package dev.raidyarenas.minitwitter.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("email")
    @Expose
    private val email: String,

    @SerializedName("password")
    @Expose
    private val password: String
)
package dev.raidyarenas.minitwitter.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @SerializedName("username")
    @Expose
    private val username: String ,
    @SerializedName("email")
    @Expose
    private val email: String ,
    @SerializedName("password")
    @Expose
    private val password: String ,
    @SerializedName("code")
    @Expose
    private val code: String
)
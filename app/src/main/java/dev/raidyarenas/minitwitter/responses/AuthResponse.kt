package dev.raidyarenas.minitwitter.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    @Expose
    val token: String,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("role")
    @Expose
    val role: String,
    @SerializedName("photoUrl")
    @Expose
    val photoUrl: String,
    @SerializedName("created")
    @Expose
    val created: String,
    @SerializedName("active")
    @Expose
    val active: Boolean
)
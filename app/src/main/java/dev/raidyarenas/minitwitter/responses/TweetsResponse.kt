package dev.raidyarenas.minitwitter.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class User(
    @SerializedName("id")
    @Expose
    val id: Number,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("descripcion")
    @Expose
    val description: String,
    @SerializedName("website")
    @Expose
    val website: String,
    @SerializedName("photoUrl")
    @Expose
    val photoUrl: String,
    @SerializedName("created")
    @Expose
    val created: Date
)

data class TweetsResponse(
    @SerializedName("id")
    @Expose
    val id: Number,
    @SerializedName("mensaje")
    @Expose
    val message: String,
    @SerializedName("likes")
    @Expose
    val likes: ArrayList<User>,
    @SerializedName("user")
    @Expose
    val user: User
)
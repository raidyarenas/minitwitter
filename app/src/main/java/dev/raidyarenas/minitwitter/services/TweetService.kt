package dev.raidyarenas.minitwitter.services

import dev.raidyarenas.minitwitter.responses.TweetsResponse
import retrofit2.Call
import retrofit2.http.GET

interface TweetService {
    @GET("tweets/all")
    fun getAllTweets(): Call<List<TweetsResponse>>
}
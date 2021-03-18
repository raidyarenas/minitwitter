package dev.raidyarenas.minitwitter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.raidyarenas.minitwitter.R
import dev.raidyarenas.minitwitter.common.App
import dev.raidyarenas.minitwitter.common.Message
import dev.raidyarenas.minitwitter.responses.TweetsResponse
import dev.raidyarenas.minitwitter.retrofit.MiniTwitterClient
import dev.raidyarenas.minitwitter.services.TweetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetRepository {
    private val tweetService: TweetService =  MiniTwitterClient.buildService(TweetService::class.java)
    val allTweets: LiveData<List<TweetsResponse>>
        get() {
            val data: MutableLiveData<List<TweetsResponse>> = MutableLiveData()
            val call: Call<List<TweetsResponse>> = tweetService.getAllTweets()
            call.enqueue(object : Callback<List<TweetsResponse>> {
                override fun onResponse(
                        call: Call<List<TweetsResponse>>,
                        response: Response<List<TweetsResponse>>
                ) {
                    if(response.isSuccessful) {
                        data.value = response.body()!!
                    } else {
                       Message.long(App.instance.getString(R.string.something_bad))
                    }
                }

                override fun onFailure(call: Call<List<TweetsResponse>>, t: Throwable) {
                    Message.long(App.instance.getString(R.string.bad_connection))
                }
            })
            return data
        }

}
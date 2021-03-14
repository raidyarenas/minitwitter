package dev.raidyarenas.minitwitter.ui.fragments.tweetList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dev.raidyarenas.minitwitter.R
import dev.raidyarenas.minitwitter.responses.TweetsResponse
import dev.raidyarenas.minitwitter.retrofit.MiniTwitterClient
import dev.raidyarenas.minitwitter.services.AuthService
import dev.raidyarenas.minitwitter.services.TweetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TweetListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var tweetAdapter: TweetRecyclerViewAdapter
    private val tweetService: TweetService =  MiniTwitterClient.buildService(TweetService::class.java)
    private var tweets: List<TweetsResponse> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tweet_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                recyclerView = this
                tweetAdapter = TweetRecyclerViewAdapter(tweets)
                layoutManager =  LinearLayoutManager(context)
                adapter = tweetAdapter
            }
        }
        getAllTweets()
        return view
    }

    private fun getAllTweets() {
        val call: Call<List<TweetsResponse>> = tweetService.getAllTweets()
        call.enqueue(AllTweetsCallback())
    }

    inner class AllTweetsCallback : Callback<List<TweetsResponse>> {
        override fun onResponse(
            call: Call<List<TweetsResponse>>,
            response: Response<List<TweetsResponse>>
        ) {
            if(response.isSuccessful) {
                tweetAdapter.refreshData(response.body()!!)
            } else {
                Toast.makeText(
                    activity,
                    R.string.something_bad,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        override fun onFailure(call: Call<List<TweetsResponse>>, t: Throwable) {
            Toast.makeText(
                activity,
                R.string.bad_connection,
                Toast.LENGTH_LONG
            ).show()
        }

    }

}
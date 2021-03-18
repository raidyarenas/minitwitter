package dev.raidyarenas.minitwitter.ui.fragments.tweetList

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.raidyarenas.minitwitter.R
import dev.raidyarenas.minitwitter.common.App
import dev.raidyarenas.minitwitter.data.TweetViewModel
import dev.raidyarenas.minitwitter.responses.TweetsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TweetListFragment : Fragment() {
    private val tweetViewModel: TweetViewModel by viewModels()
    private var tweets: List<TweetsResponse> = listOf()
    lateinit var recyclerView: RecyclerView
    lateinit var tweetAdapter: TweetRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tweetViewModel.tweets.observe(this) {
            tweets = it
            tweetAdapter.refreshData(tweets)
        }
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
        return view
    }

}
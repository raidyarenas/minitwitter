package dev.raidyarenas.minitwitter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.raidyarenas.minitwitter.responses.TweetsResponse

class TweetViewModel: ViewModel() {
    private val tweetRepository: TweetRepository = TweetRepository()
    private val _tweets: LiveData<List<TweetsResponse>> = tweetRepository.allTweets

    val tweets: LiveData<List<TweetsResponse>> = _tweets

}
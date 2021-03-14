package dev.raidyarenas.minitwitter.ui.fragments.tweetList

import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import dev.raidyarenas.minitwitter.R
import dev.raidyarenas.minitwitter.common.Constants
import dev.raidyarenas.minitwitter.common.SharedPreferencesManager
import dev.raidyarenas.minitwitter.responses.TweetsResponse

class TweetRecyclerViewAdapter(
    private var tweets: List<TweetsResponse>
) : RecyclerView.Adapter<TweetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tweets[position]
        holder.username.text = item.user.username
        holder.message.text = item.message
        holder.totalLike.text = item.likes.size.toString()
        if (item.user.photoUrl != "") {
            Glide.with(holder.itemView)
                .load("${Constants.RESOURCE_IMAGE} ${item.user.photoUrl}")
                .into(holder.avatar)
        }

        val currentUser: String? = SharedPreferencesManager.getString(Constants.PREFERENCE_USERNAME, "")
        for (like in item.likes) {
            if (like.username == currentUser) {
                Glide.with(holder.itemView)
                    .load(R.drawable.ic_like_pink)
                    .into(holder.like)
                with(holder.totalLike) {
                    setTextColor(resources.getColor(R.color.pink))
                    setTypeface(null, Typeface.BOLD)
                }
                break
            }
        }
    }

    fun refreshData(tweets: List<TweetsResponse>) {
        this.tweets = tweets
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tweets.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val username: TextView = view.findViewById(R.id.username)
        val message: TextView = view.findViewById(R.id.message)
        val like: ImageView = view.findViewById(R.id.like)
        val totalLike: TextView = view.findViewById(R.id.totalLikes)
        override fun toString(): String {
            return super.toString()
        }
    }
}
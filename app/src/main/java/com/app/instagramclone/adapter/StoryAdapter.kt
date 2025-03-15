package com.app.instagramclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.instagramclone.R
import com.app.instagramclone.model.Story
import de.hdodenhof.circleimageview.CircleImageView

class StoryAdapter(private val context: Context) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    private var stories: List<Story> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setStories(stories: List<Story>) {
        this.stories = stories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        holder.bind(story)
    }

    override fun getItemCount(): Int = stories.size

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val storyRing: CircleImageView = itemView.findViewById(R.id.story_ring)
        private val profileImage: CircleImageView = itemView.findViewById(R.id.story_profile_image)
        private val username: TextView = itemView.findViewById(R.id.story_username)

        fun bind(story: Story) {
            val profileImageResourceId = context.resources.getIdentifier(
                story.profilePictureUrl, "drawable", context.packageName
            )
            profileImage.setImageResource(profileImageResourceId)
            username.text = story.username

            if (story.hasUnseenStory) {
                storyRing.visibility = View.VISIBLE
            } else {
                storyRing.visibility = View.INVISIBLE
            }
        }
    }
}


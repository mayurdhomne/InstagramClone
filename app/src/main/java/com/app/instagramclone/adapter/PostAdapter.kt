package com.app.instagramclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.app.instagramclone.R
import com.app.instagramclone.model.Post
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

class PostAdapter(private val context: Context) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts: List<Post> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(newPosts: List<Post>) {
        val diffCallback = PostDiffCallback(posts, newPosts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        posts = newPosts
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImage: CircleImageView = itemView.findViewById(R.id.profile_image)
        private val username: TextView = itemView.findViewById(R.id.username)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image)
        private val postVideo: VideoView = itemView.findViewById(R.id.post_video)
        private val carouselViewPager: ViewPager2 = itemView.findViewById(R.id.carousel_view_pager)
        private val carouselIndicator: LinearLayout = itemView.findViewById(R.id.carousel_indicator)
        private val likesCount: TextView = itemView.findViewById(R.id.likes_count)
        private val caption: TextView = itemView.findViewById(R.id.caption)
        private val likeButton: ImageView = itemView.findViewById(R.id.like_button)

        @SuppressLint("SetTextI18n")
        fun bind(post: Post) {
            val profileImageResourceId = context.resources.getIdentifier(
                post.profilePictureUrl.substringBefore("."), "drawable", context.packageName
            )
            if (profileImageResourceId != 0) {
                profileImage.setImageResource(profileImageResourceId)
            } else {
                profileImage.setImageResource(R.drawable.default_profile)
            }

            Glide.with(context)
                .load(
                    "android.resource://${context.packageName}/drawable/${
                        post.profilePictureUrl.substringBefore(
                            "."
                        )
                    }".toUri())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_profile)
                .into(profileImage)

            username.text = post.user
            caption.text = "${post.user} ${post.caption}"
            likesCount.text = "${post.likes} likes"

            when (post.type) {
                Post.TYPE_IMAGE -> {
                    postImage.visibility = View.VISIBLE
                    postVideo.visibility = View.GONE
                    carouselViewPager.visibility = View.GONE
                    carouselIndicator.visibility = View.GONE

                    val imageResourceId = context.resources.getIdentifier(
                        post.imageUrl?.substringBefore("."), "drawable", context.packageName
                    )
                    if (imageResourceId != 0) {
                        postImage.setImageResource(imageResourceId)
                    } else {
                        postImage.setImageResource(R.drawable.placeholder_image)
                    }

                    Glide.with(context)
                        .load(
                            "android.resource://${context.packageName}/drawable/${
                                post.imageUrl?.substringBefore(
                                    "."
                                )
                            }".toUri())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.placeholder_image)
                        .into(postImage)
                }

                Post.TYPE_VIDEO -> {
                    postImage.visibility = View.GONE
                    postVideo.visibility = View.VISIBLE
                    carouselViewPager.visibility = View.GONE
                    carouselIndicator.visibility = View.GONE

                    val videoResourceId = context.resources.getIdentifier(
                        post.videoUrl?.substringBefore("."), "raw", context.packageName
                    )
                    if (videoResourceId != 0) {
                        val videoUri =
                            "android.resource://${context.packageName}/raw/$videoResourceId".toUri()
                        postVideo.setVideoURI(videoUri)
                        postVideo.setOnPreparedListener { mp ->
                            mp.isLooping = true
                            mp.setVolume(0f, 0f)
                        }
                        postVideo.start()
                    }
                }
            }


            likeButton.setOnClickListener {
                if (likeButton.tag == null || likeButton.tag == "unliked") {
                    likeButton.setImageResource(R.drawable.ic_heart_filled)
                    likeButton.tag = "liked"
                    likesCount.text = "${post.likes + 1} likes"
                } else {
                    likeButton.setImageResource(R.drawable.ic_heart)
                    likeButton.tag = "unliked"
                    likesCount.text = "${post.likes} likes"
                }
            }
        }

        private fun setupCarouselIndicators(count: Int) {
            carouselIndicator.removeAllViews()

            val indicators = mutableListOf<ImageView>()

            for (i in 0 until count) {
                val indicator = ImageView(context).apply {
                    setImageResource(R.drawable.indicator_inactive)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(8, 0, 8, 0)
                    this.layoutParams = layoutParams
                }

                carouselIndicator.addView(indicator)
                indicators.add(indicator)
            }

            if (indicators.isNotEmpty()) {
                indicators[0].setImageResource(R.drawable.indicator_active)
            }
        }


        private fun updateCarouselIndicators(position: Int) {
            for (i in 0 until carouselIndicator.childCount) {
                val imageView = carouselIndicator.getChildAt(i) as ImageView
                if (i == position) {
                    imageView.setImageResource(R.drawable.indicator_active)
                } else {
                    imageView.setImageResource(R.drawable.indicator_inactive)
                }
            }
        }

    }
}

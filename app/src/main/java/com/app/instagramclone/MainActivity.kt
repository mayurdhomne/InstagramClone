package com.app.instagramclone

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.instagramclone.adapter.PostAdapter
import com.app.instagramclone.adapter.StoryAdapter
import com.app.instagramclone.viewmodel.FeedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FeedViewModel
    private lateinit var postAdapter: PostAdapter
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var feedRecyclerView: RecyclerView
    private lateinit var storiesRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var bottomNavigation: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedRecyclerView = findViewById(R.id.feed_recycler_view)
        storiesRecyclerView = findViewById(R.id.stories_recycler_view)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        progressBar = findViewById(R.id.progress_bar)


        postAdapter = PostAdapter(this)
        storyAdapter = StoryAdapter(this)

        feedRecyclerView.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                initialPrefetchItemCount = 5
            }
            adapter = postAdapter
        }

        storiesRecyclerView.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = storyAdapter
        }

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        viewModel.posts.observe(this) { posts ->
            postAdapter.setPosts(posts)
        }

        viewModel.stories.observe(this) { stories ->
            storyAdapter.setStories(stories)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Error: $it", Toast.LENGTH_LONG).show()
            }
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    true
                }
                else -> {
                    Toast.makeText(this, "This feature is not implemented in this demo", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }
}


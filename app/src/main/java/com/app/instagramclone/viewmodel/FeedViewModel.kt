package com.app.instagramclone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.instagramclone.model.Post
import com.app.instagramclone.model.Story
import com.app.instagramclone.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PostRepository(application)

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _stories = MutableLiveData<List<Story>>()
    val stories: LiveData<List<Story>> = _stories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadFeed()
    }

    fun loadFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                val postList = repository.getPosts()
                val storyList = repository.getStories()

                val repeatedPosts = mutableListOf<Post>()
                repeat(5) { repeatedPosts.addAll(postList) }

                _posts.postValue(repeatedPosts)
                _stories.postValue(storyList)
                _error.postValue(null)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

}


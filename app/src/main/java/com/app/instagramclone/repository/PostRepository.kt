package com.app.instagramclone.repository

import android.content.Context
import com.app.instagramclone.model.Post
import com.app.instagramclone.model.Story
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class PostRepository(private val context: Context) {

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("feed_data.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Post>>() {}.type
            Gson().fromJson(jsonString, type)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getStories(): List<Story> = withContext(Dispatchers.IO) {
        listOf(
            Story("Your Story", "profile_self", false),
            Story("traveler_jane", "profile_1", true),
            Story("world_explorer", "profile_2", true),
            Story("skater_boy", "profile_3", true),
            Story("food_lover", "profile_4", true),
            Story("art_enthusiast", "profile_5", false),
            Story("dog_lover", "profile_6", true)
        )
    }
}


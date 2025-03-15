package com.app.instagramclone

import com.app.instagramclone.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class PostModelTest {

    private val sampleJson = """
        [
          {
            "type": "image",
            "imageUrl": "post_image_1",
            "caption": "Beautiful sunset at the beach #sunset #beach #summer",
            "user": "traveler_jane",
            "profilePictureUrl": "profile_1",
            "likes": 1243
          },
          {
            "type": "carousel",
            "caption": "My trip to Paris was amazing! #travel #paris #eiffeltower",
            "user": "world_explorer",
            "profilePictureUrl": "profile_2",
            "likes": 856,
            "carouselImages": ["carousel_1_1", "carousel_1_2", "carousel_1_3"]
          }
        ]
    """.trimIndent()

    @Test
    fun testPostJsonParsing() {
        val type = object : TypeToken<List<Post>>() {}.type
        val posts: List<Post> = Gson().fromJson(sampleJson, type)

        // Verify we parsed 2 posts
        assertEquals(2, posts.size)

        // Verify first post (image type)
        val imagePost = posts[0]
        assertEquals(Post.TYPE_IMAGE, imagePost.type)
        assertEquals("post_image_1", imagePost.imageUrl)
        assertEquals("Beautiful sunset at the beach #sunset #beach #summer", imagePost.caption)
        assertEquals("traveler_jane", imagePost.user)
        assertEquals("profile_1", imagePost.profilePictureUrl)
        assertEquals(1243, imagePost.likes)

        // Verify second post (carousel type)
        val carouselPost = posts[1]
        assertEquals(Post.TYPE_CAROUSEL, carouselPost.type)
        assertEquals("My trip to Paris was amazing! #travel #paris #eiffeltower", carouselPost.caption)
        assertEquals("world_explorer", carouselPost.user)
        assertEquals("profile_2", carouselPost.profilePictureUrl)
        assertEquals(856, carouselPost.likes)
        assertNotNull(carouselPost.carouselImages)
        assertEquals(3, carouselPost.carouselImages?.size)
        assertEquals("carousel_1_1", carouselPost.carouselImages?.get(0))
        assertEquals("carousel_1_2", carouselPost.carouselImages?.get(1))
        assertEquals("carousel_1_3", carouselPost.carouselImages?.get(2))
    }
}


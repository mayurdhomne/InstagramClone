package com.app.instagramclone.model

data class Post(
    val id: String,
    val type: String,
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val caption: String,
    val user: String,
    val profilePictureUrl: String,
    val likes: Int,
    val carouselImages: List<String>? = null
) {
    companion object {
        const val TYPE_IMAGE = "image"
        const val TYPE_VIDEO = "video"
        const val TYPE_CAROUSEL = "carousel"
    }
}

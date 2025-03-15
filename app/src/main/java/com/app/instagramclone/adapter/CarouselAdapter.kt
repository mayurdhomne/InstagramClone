package com.app.instagramclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.instagramclone.R

class CarouselAdapter(
    private val context: Context,
    private val images: List<String>
) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_image, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int = images.size

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carouselImage: ImageView = itemView.findViewById(R.id.carousel_image)

        fun bind(imageUrl: String) {
            val imageResourceId = context.resources.getIdentifier(
                imageUrl, "drawable", context.packageName
            )
            carouselImage.setImageResource(imageResourceId)
        }
    }
}


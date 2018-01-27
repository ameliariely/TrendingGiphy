package com.ameliariely.trendinggiphy

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Custom adapter for loading an image from a url into an ImageView with Glide.
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val context = imageView.context
    Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions().circleCrop())
            .into(imageView)
}

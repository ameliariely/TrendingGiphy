package com.ameliariely.trendinggiphy.ui.gifList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.ameliariely.trendinggiphy.data.remote.model.Gif
import com.ameliariely.trendinggiphy.databinding.ItemGifViewBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gif_view.view.*

class GifsListAdapter(private val onLastItemBoundListener: (itemCount : Int) -> Unit) : RecyclerView.Adapter<GifViewHolder>() {

    private val items = ArrayList<Gif>()

    fun setItems(list: List<Gif>) {
        items.clear()
        addItems(list)
    }

    fun addItems(list: List<Gif>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(ItemGifViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.setGif(items[position])
        setImageUrl(holder.itemView.gif_image_view, items[position].imagesObject?.fixedWidth?.url ?: "")
        if (position == itemCount-1) {
            onLastItemBoundListener(itemCount)
        }
    }

    fun setImageUrl(imageView: ImageView, url: String) {
        val context = imageView.context
        Glide.with(context)
                .load(url)
                .into(imageView)

    }
}

/**
 * ViewHolder that takes a Gif for dataBinding.
 */
class GifViewHolder(private val binding: ItemGifViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setGif(gif: Gif) {
        binding.gif = gif
        binding.executePendingBindings()
    }
}
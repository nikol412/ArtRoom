package com.nikol412.artroom.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.artroom.data.GlideApp
import com.nikol412.artroom.data.response.ArtworkObject
import com.nikol412.artroom.databinding.ItemArtworksRowBinding

class ArtworksAdapter() : RecyclerView.Adapter<ArtworkViewHolder>() {

    private var artsList = mutableListOf<ArtworkObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        return ArtworkViewHolder(
            ItemArtworksRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        holder.onBind(artsList[position])
    }

    override fun getItemCount(): Int = artsList.size

    fun setItems(items: List<ArtworkObject>) {
        artsList = items.toMutableList()

        notifyDataSetChanged()
    }
}

class ArtworkViewHolder(private val binding: ItemArtworksRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ArtworkObject) {
        binding.textViewData.text = item.title

        val url = "${item.imageConfig}/${item.imageId}/full/600,/0/default.jpg"
        GlideApp.with(binding.root.context)
            .load(url)
            .into(binding.imageArtwork)
    }
}
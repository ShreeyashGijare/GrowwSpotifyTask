package com.example.growtask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growtask.R
import com.example.growtask.data.data_models.artistAlbums.Item
import com.example.growtask.databinding.ItemArtistAlbumsBinding
import kotlinx.coroutines.flow.combine

class ArtistAlbumsAdapter(
    val context: Context
) : RecyclerView.Adapter<ArtistAlbumsAdapter.MainViewHolder>() {

    private var albumList: List<Item> = ArrayList()

    class MainViewHolder constructor(val binding: ItemArtistAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistAlbumsBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(context).load(albumList[position].images[0].url)
            .error(R.drawable.spotify_icon).into(holder.binding.ivAlbum)
        holder.binding.tvAlbumName.text = albumList[position].name

    }

    override fun getItemCount(): Int {
        return if (albumList.isEmpty()) {
            0
        } else {
            5
        }
    }


    fun updateList(albumList: List<Item>) {
        this.albumList = albumList
        notifyDataSetChanged()
    }
}
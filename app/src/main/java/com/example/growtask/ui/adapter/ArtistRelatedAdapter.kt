package com.example.growtask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growtask.R
import com.example.growtask.data.data_models.artistRelated.Artist
import com.example.growtask.databinding.ItemArtistAlbumsBinding
import com.example.growtask.databinding.ItemArtistRelatedBinding

class ArtistRelatedAdapter(
    val context: Context
) : RecyclerView.Adapter<ArtistRelatedAdapter.MainViewHolder>() {

    private var artistRelatedList: List<Artist> = ArrayList()

    class MainViewHolder constructor(val binding: ItemArtistRelatedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistRelatedBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(context).load(artistRelatedList[position].images[0].url)
            .error(R.drawable.spotify_icon).into(holder.binding.ivAlbum)
        holder.binding.tvAlbumName.text = artistRelatedList[position].name

    }

    override fun getItemCount(): Int {
        return if (artistRelatedList.isEmpty()) {
            0
        } else {
            artistRelatedList.size
        }
    }

    fun updateList(albumList: List<Artist>) {
        this.artistRelatedList = albumList
        notifyDataSetChanged()
    }
}
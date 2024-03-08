package com.example.growtask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growtask.R
import com.example.growtask.data.data_models.artistTopTracks.Track
import com.example.growtask.databinding.ItemArtistTopTracksBinding

class ArtistTopTrackAdapter(
    val context: Context
) : RecyclerView.Adapter<ArtistTopTrackAdapter.MainViewHolder>() {

    private var trackList: List<Track> = ArrayList()

    class MainViewHolder constructor(val binding: ItemArtistTopTracksBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistTopTracksBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(context).load(trackList[position].album.images[0].url)
            .error(R.drawable.spotify_icon).into(holder.binding.ivTrack)
        holder.binding.tvTrackNumber.text = "${position + 1}"
        holder.binding.tvTrackTitle.text = trackList[position].name
        holder.binding.tvTrackViews.text = "Popularity ${trackList[position].popularity}"
    }

    override fun getItemCount(): Int {
        return if (trackList.isEmpty()) {
            0
        } else {
            5
        }
    }


    fun updateList(trackList: List<Track>) {
        this.trackList = trackList
        notifyDataSetChanged()
    }
}
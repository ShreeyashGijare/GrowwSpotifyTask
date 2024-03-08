package com.example.growtask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.growtask.R
import com.example.growtask.data.data_models.searchModel.ItemXXX
import com.example.growtask.databinding.ItemSongBinding
import com.example.growtask.ui.clickInterfaces.SearchClickInterface

class DashBoardAdapter(
    val context: Context,
    val searchClickInterface: SearchClickInterface
) :
    RecyclerView.Adapter<DashBoardAdapter.MainViewHolder>() {

    private var dataList: List<ItemXXX> = ArrayList()

    class MainViewHolder constructor(var binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.binding.textSongTitle.text = dataList[position].name
        holder.binding.textArtistAlbum.text = dataList[position].album?.name
        holder.binding.textDuration.text = dataList[position].artists!![0].name
        Glide.with(context)
            .load(dataList[position].album?.images!![0].url)
            .error(R.drawable.spotify_icon)
            .into(holder.binding.imageAlbumCover)

        holder.binding.imageMore.setOnClickListener {
            searchClickInterface.searchClickData(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        if (dataList.isNullOrEmpty()) {
            return 0
        }
        return dataList.size
    }

    fun setUpData(dataList: List<ItemXXX>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}
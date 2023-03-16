package com.crystallake.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crystallake.explore.databinding.ItemGalleryBinding

class GalleryAdapter(val dataList: MutableList<String>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {


    class GalleryViewHolder(val itemGalleryBinding: ItemGalleryBinding) : RecyclerView.ViewHolder(itemGalleryBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(ItemGalleryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Glide.with(holder.itemView).load(dataList[position])
            .timeout(5000)
            .into(holder.itemGalleryBinding.img)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
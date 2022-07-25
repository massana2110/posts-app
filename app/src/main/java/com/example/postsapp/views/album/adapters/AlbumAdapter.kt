package com.example.postsapp.views.album.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.R
import com.example.postsapp.databinding.ImageItemBinding
import com.example.postsapp.models.Image

class AlbumAdapter(
    private val images: List<Image>
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(
        val imageItemBinding: ImageItemBinding
    ) : RecyclerView.ViewHolder(imageItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.image_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.imageItemBinding.apply {
            image = images[position]
        }
    }

    override fun getItemCount(): Int = images.size

}
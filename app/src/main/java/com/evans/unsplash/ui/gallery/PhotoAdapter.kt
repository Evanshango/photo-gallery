package com.evans.unsplash.ui.gallery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.evans.unsplash.R
import com.evans.unsplash.data.Helpers.Companion.getFormattedDate
import com.evans.unsplash.data.Photo
import com.evans.unsplash.databinding.ItemPhotoBinding

class PhotoAdapter(
    private val listener: PhotoClickListener
) : PagingDataAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) holder.bind(currentItem)
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.photoClicked(it)
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(currentItem: Photo) {
            binding.apply {

                txtUserName.text = currentItem.user.username
                txtPhotoLikes.text = "${currentItem.likes} likes"
                txtDate.text = getFormattedDate(currentItem.createdAt)

                Glide.with(itemView)
                    .load(currentItem.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        }
    }

    interface PhotoClickListener {
        fun photoClicked(photo: Photo)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }
}
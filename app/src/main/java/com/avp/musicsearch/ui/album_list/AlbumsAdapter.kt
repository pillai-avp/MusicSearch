package com.avp.musicsearch.ui.album_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.databinding.AlbumItemLayoutBinding
import com.avp.musicsearch.dto.Album
import com.bumptech.glide.Glide


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */
class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private var albums: MutableList<Album> = mutableListOf()
    val itemClickLiveData = MutableLiveData<Event<Album?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemBinding =
            AlbumItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = AlbumsViewHolder(itemBinding)
        holder.itemView.setOnClickListener {
            itemClickLiveData.postValue(Event(itemBinding.album))
        }
        return holder
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])

    }

    fun setItem(albums: List<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    class AlbumsViewHolder(private val binding: AlbumItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            binding.album = item
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("remotesrc")
fun remoteSource(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

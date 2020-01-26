package com.avp.musicsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.databinding.ArtistItemLayoutBinding
import com.avp.musicsearch.dto.Artist
import com.bumptech.glide.Glide


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 22 January 2020
 */
class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {

    private var artists: MutableList<Artist> = mutableListOf()
    val itemClickLiveData = MutableLiveData<Event<Artist?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemBinding =
            ArtistItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ArtistViewHolder(itemBinding)
        holder.itemView.setOnClickListener {
            itemClickLiveData.postValue(Event(itemBinding.artist))
        }
        return holder
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artists[position])

    }

    fun setItem(artists: List<Artist>) {
        this.artists.clear()
        this.artists.addAll(artists)
        notifyDataSetChanged()
    }

    class ArtistViewHolder(private val binding: ArtistItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            binding.artist = item
            Glide.with(binding.artistImage.context)
                .load(item.picture_small)
                .into(binding.artistImage)

            binding.executePendingBindings()
        }
    }
}

package com.avp.musicsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avp.musicsearch.databinding.ArtistItemLayoutBinding
import com.avp.musicsearch.dto.Artist


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemBinding =
            ArtistItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(itemBinding)
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
            binding.executePendingBindings()
        }
    }
}
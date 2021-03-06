package com.avp.musicsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.databinding.ArtistItemLayoutBinding
import com.avp.musicsearch.dto.FormattedArtist
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */
class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {

    private var artists: MutableList<FormattedArtist> = mutableListOf()
    val itemClickLiveData = MutableLiveData<Event<FormattedArtist?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemBinding =
            ArtistItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ArtistViewHolder(itemBinding)
        holder.itemView.setOnClickListener {
            itemClickLiveData.postValue(Event(itemBinding.formattedArtist))
        }
        return holder
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artists[position])

    }

    fun setItem(artists: List<FormattedArtist>) {
        this.artists.clear()
        this.artists.addAll(artists)
        notifyDataSetChanged()
    }

    class ArtistViewHolder(private val binding: ArtistItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FormattedArtist) {
            binding.formattedArtist = item
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("remotesrc")
fun remoteSource(imageView: CircleImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

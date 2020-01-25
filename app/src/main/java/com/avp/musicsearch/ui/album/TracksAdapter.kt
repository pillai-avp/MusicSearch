package com.avp.musicsearch.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.databinding.TrackItemLayoutBinding
import com.avp.musicsearch.dto.TrackData


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 22 January 2020
 */
class TracksAdapter : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    private var tracks: MutableList<TrackData> = mutableListOf()
    val itemClickLiveData = MutableLiveData<Event<TrackData?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemBinding =
            TrackItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = TrackViewHolder(itemBinding)
        holder.itemView.setOnClickListener {
            itemClickLiveData.postValue(Event(itemBinding.track))
        }
        return holder
    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])

    }

    fun setItem(tracks: List<TrackData>) {
        this.tracks.clear()
        this.tracks.addAll(tracks)
        notifyDataSetChanged()
    }

    class TrackViewHolder(private val binding: TrackItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrackData) {
            binding.track = item
            binding.executePendingBindings()
        }
    }
}

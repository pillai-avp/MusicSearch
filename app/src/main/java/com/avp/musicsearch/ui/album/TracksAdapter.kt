package com.avp.musicsearch.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.avp.musicsearch.R
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.databinding.TrackItemLayoutBinding
import com.avp.musicsearch.databinding.TrackVolumeItemLayoutBinding
import com.avp.musicsearch.dto.TrackData


private val TYPE_VOLUME = 0

private val TYPE_TRACK = 1

/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */
class TracksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tracks: MutableList<Any> = mutableListOf()
    val itemClickLiveData = MutableLiveData<Event<Any?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_VOLUME) {
            val itemBinding =
                TrackVolumeItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            VolumeViewHolder(itemBinding)
        } else {
            val itemBinding =
                TrackItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = TrackViewHolder(itemBinding)
            holder.itemView.setOnClickListener {
                itemClickLiveData.postValue(Event(itemBinding.track))
            }
            holder
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            tracks[position] is Int -> TYPE_VOLUME
            else -> TYPE_TRACK
        }
    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_VOLUME) {
            (holder as VolumeViewHolder).bind(tracks[position] as Int)
        } else {
            (holder as TrackViewHolder).bind(tracks[position] as TrackData)
        }
    }

    fun setItem(tracks: List<Any>) {
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

    class VolumeViewHolder(private val binding: TrackVolumeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.volumeText = binding.root.context.getString(R.string.volume, item.toString())
            binding.executePendingBindings()
        }
    }
}

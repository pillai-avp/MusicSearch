package com.avp.musicsearch.ui.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.avp.musicsearch.R
import com.avp.musicsearch.common.EventObserver
import com.avp.musicsearch.databinding.ActivityTracksBinding
import com.avp.musicsearch.dto.Album
import com.avp.musicsearch.ui.album_list.TracksViewModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tracks.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val ARG_ALBUM = "ARG_ALBUM"
private const val ARG_ARTIST_NAME = "ARG_ARTIST_NAME"

class TracksActivity : AppCompatActivity() {

    private lateinit var album: Album
    private val gson: Gson by inject()
    private val tracksViewModel: TracksViewModel by viewModel()

    private val tracksAdapter: TracksAdapter by inject()

    private lateinit var binding: ActivityTracksBinding


    companion object{
        fun launch(
            context: Context,
            albumJson: String,
            artistName: String
        ) {
            Intent(context, TracksActivity::class.java).apply {
                putExtra(ARG_ALBUM, albumJson)
                putExtra(ARG_ARTIST_NAME, artistName)
            }.also {
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tracks)
        setToolBarAsActionBar(album_details_toolbar)
        album = getDataFromIntent()
        configureAlbumList()
        setValueToBinding()
        getTracksFromRemote()

    }

    private fun getTracksFromRemote() {
        tracksViewModel.getTrackList(album.tracklist).observe(this, EventObserver {
            it?.let {
                showTrackList(it)
            }?:run{
                showEmptyScreen()
            }
        })
    }

    private fun showEmptyScreen() {
        Timber.d("Tracks- Empty screen")
    }

    private fun showTrackList(tracks: List<Any>) {
        tracksAdapter.setItem(tracks)
    }

    private fun configureAlbumList() {
        binding.trackListView.apply {
            adapter = tracksAdapter
            layoutManager = LinearLayoutManager(context)
        }
        onAdapterItemClickListener()
    }

    private fun onAdapterItemClickListener() {
        tracksAdapter.itemClickLiveData.observe(this, EventObserver {
            it?.let {
                // TODO play sample mp3 file
            }
        })
    }


    private fun setValueToBinding() {
        binding.album = album
        binding.artistName = intent.getStringExtra(ARG_ARTIST_NAME)
        Glide.with(binding.albumCoverDetails.context)
            .load(album.cover_big)
            .into(binding.albumCoverDetails)

        binding.executePendingBindings()
    }

    private fun getDataFromIntent() : Album =  gson.fromJson(intent.getStringExtra(ARG_ALBUM), Album::class.java)


    private fun setToolBarAsActionBar(toolbar: Toolbar?) {
        toolbar?.let { toolBar ->
            setSupportActionBar(toolBar)
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}

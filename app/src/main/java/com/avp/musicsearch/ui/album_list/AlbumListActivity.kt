package com.avp.musicsearch.ui.album_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.avp.musicsearch.R
import com.avp.musicsearch.common.EventObserver
import com.avp.musicsearch.databinding.ActivityAlbumListBinding
import com.avp.musicsearch.dto.Album
import com.avp.musicsearch.dto.FromattedArtistModel
import com.avp.musicsearch.ui.album.TracksActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_album_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val ARG_ARTIST = "ARG_ARTIST"

class AlbumListActivity : AppCompatActivity() {

    companion object {
        fun launch(context: Context, artistJson: String) {
            Intent(context, AlbumListActivity::class.java).also {
                it.putExtra(ARG_ARTIST, artistJson)
                context.startActivity(it)
            }
        }
    }

    private lateinit var artist: FromattedArtistModel
    private val gson: Gson by inject()
    private val albumListViewModel: AlbumListViewModel by viewModel()

    private val albumsAdapter: AlbumsAdapter by inject()

    private lateinit var binding: ActivityAlbumListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        setToolBarAsActionBar(albums_toolbar)
        artist = getDataFromIntent()
        configureAlbumList()
        showListOfAlbums(groupAlbumsByID())

    }

    private fun groupAlbumsByID(): List<Album> {
        val albums = artist.listAlbums.groupBy { it.id }.values.mapNotNull {
            it.maxBy { groupedValues -> groupedValues.id }
        }
        return albums
    }

    private fun configureAlbumList() {
        binding.albumList.apply {
            adapter = albumsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        onAdapterItemClickListener()
    }

    private fun onAdapterItemClickListener() {
        albumsAdapter.itemClickLiveData.observe(this, EventObserver {
            it?.let {
                openAlbumDetailsActivity(it)
            }
        })
    }

    private fun openAlbumDetailsActivity(it: Album) {
        TracksActivity.launch(this, gson.toJson(it))
    }

    private fun showListOfAlbums(albums: List<Album>) {
        albumsAdapter.setItem(albums)
    }

    private fun getDataFromIntent(): FromattedArtistModel =
        gson.fromJson(intent.getStringExtra(ARG_ARTIST), FromattedArtistModel::class.java)


    private fun setToolBarAsActionBar(toolbar: Toolbar?) {
        toolbar?.let { toolBar ->
            setSupportActionBar(toolBar)
            supportActionBar?.title = getString(R.string.albums)
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

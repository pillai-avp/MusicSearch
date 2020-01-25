package com.avp.musicsearch.ui.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.avp.musicsearch.R
import com.avp.musicsearch.databinding.ActivityAlbumDetailsBinding
import com.avp.musicsearch.dto.Album
import com.avp.musicsearch.ui.album_list.AlbumDetailsViewModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_album_details.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_ALBUM = "ARG_ALBUM"

class AlbumDetailsActivity : AppCompatActivity() {

    private lateinit var album: Album
    private val gson: Gson by inject()
    private val albumDetailsViewModel: AlbumDetailsViewModel by viewModel()

    //private val albumsAdapter: AlbumsAdapter by currentScope.inject()

    private lateinit var binding: ActivityAlbumDetailsBinding


    companion object{
        fun launch(context: Context, albumJson: String){
            Intent(context, AlbumDetailsActivity::class.java).apply {
                putExtra(ARG_ALBUM, albumJson)
            }.also {
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_details)
        setToolBarAsActionBar(album_details_toolbar)
        album = getDataFromIntent()
        setValueToBinding()

    }

    private fun setValueToBinding() {
        binding.album = album
        Glide.with(binding.albumCoverDetails.context)
            .load(album.cover_big)
            .placeholder(R.drawable.ic_album_art_empty)
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

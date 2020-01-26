package com.avp.musicsearch.ui.search

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.avp.musicsearch.R
import com.avp.musicsearch.common.EventObserver
import com.avp.musicsearch.databinding.ActivitySearchBinding
import com.avp.musicsearch.dto.Artist
import com.avp.musicsearch.ui.album_list.AlbumListActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private val artistsAdapter: ArtistsAdapter by inject()
    private val gson: Gson by inject()

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        setToolBarAsActionBar(search_toolbar)
        configureSearch()
        configureArtistList()

    }

    private fun configureArtistList() {
        binding.artistListView.apply {
            adapter = artistsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        onAdapterItemClickListener()
    }

    private fun onAdapterItemClickListener() {
        artistsAdapter.itemClickLiveData.observe(this, EventObserver {
            it?.let {
                openAlbumsActivity(it)
            }
        })
    }

    private fun openAlbumsActivity(artist: Artist) {
        AlbumListActivity.launch(this, gson.toJson(artist))
    }

    private fun configureSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                doAnEmptyCheckThen(query, ::getArtistList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                doAnEmptyCheckThen(newText, ::getArtistList)
                return true
            }

            fun doAnEmptyCheckThen(newText: String?, searchFn: (SearchViewModel, String) -> Unit) {
                if (newText != null && newText.length >= 3) {
                    searchFn(searchViewModel, newText)
                } else if ((newText != null && newText.length < 3) || TextUtils.isEmpty(newText)) {
                    showEmptyScreen()
                }
            }

        })
    }

    private fun getArtistList(searchViewModel: SearchViewModel, query: String) {
        searchViewModel.doSearch(query).observe(this, EventObserver { list ->
            list?.let {
                showArtistGrid(it)
            } ?: run {
                showEmptyScreen()
            }
        })
    }

    private fun showArtistGrid(artists: List<Artist>) {
        artistsAdapter.setItem(artists)
    }

    private fun showEmptyScreen() {
        artistsAdapter.setItem(mutableListOf())
    }


    private fun setToolBarAsActionBar(searchToolbar: Toolbar?) {
        searchToolbar?.let { toolBar ->
            setSupportActionBar(toolBar)
            supportActionBar?.title = ""
        }

    }
}

package com.avp.musicsearch.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.avp.musicsearch.R
import com.avp.musicsearch.databinding.ActivitySearchBinding
import com.avp.musicsearch.dto.Artist
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private val artistsAdapter: ArtistsAdapter by currentScope.inject()

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
                }
            }

        })
    }

    private fun getArtistList(searchViewModel: SearchViewModel, query: String) {
        searchViewModel.doSearch(query).observe(this, Observer { list ->
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

    }


    private fun setToolBarAsActionBar(searchToolbar: Toolbar?) {
        searchToolbar?.let { toolBar ->
            setSupportActionBar(toolBar)
            supportActionBar?.title = ""
        }

    }
}

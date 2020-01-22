package com.avp.musicsearch.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.avp.musicsearch.R
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setToolBarAsActionBar(search_toolbar)
        configureSearch(searchViewModel)

    }

    private fun configureSearch(searchViewModel: SearchViewModel) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                doAnEmptyCheckThen(query, ::getArtistList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                doAnEmptyCheckThen(newText, ::getArtistList)
                return true
            }

            fun doAnEmptyCheckThen(newText: String?, fn: (SearchViewModel, String) -> Unit) {
                if (newText != null && newText.length >= 3) {
                    fn(searchViewModel, newText)
                }
            }

        })
    }

    private fun getArtistList(searchViewModel: SearchViewModel, query: String) {
        searchViewModel.doSearch(query).observe(this, Observer { list ->

        })
    }


    private fun setToolBarAsActionBar(searchToolbar: Toolbar?) {
        searchToolbar?.let { toolBar ->
            setSupportActionBar(toolBar)
            supportActionBar?.title = ""
        }

    }
}

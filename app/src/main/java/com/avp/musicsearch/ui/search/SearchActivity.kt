package com.avp.musicsearch.ui.search

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.avp.musicsearch.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setToolBarAsActionBar(search_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    private fun setToolBarAsActionBar(searchToolbar: Toolbar?) {
        searchToolbar?.let { toolBar ->
            setSupportActionBar(toolBar)
            supportActionBar?.title = ""
        }

    }
}

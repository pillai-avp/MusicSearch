package com.avp.musicsearch.repo


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 21 January 2020
 */

interface AlbumRepository {
    fun genericSearch(query: String): List<Any>
}

class AlbumRepositoryImpl() : AlbumRepository {


    override fun genericSearch(query: String): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
package com.project.segunfrancis.wiki.repositories

import com.google.gson.Gson
import com.project.segunfrancis.wiki.models.WikiPage
import com.project.segunfrancis.wiki.models.WikiThumbnail
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser

/**
 * Created by SegunFrancis
 */

class HistoryRepository(val databaseHelper: ArticleDatabaseOpenHelper) {
    private val TABLE_NAME: String = "History"

    fun addFavorite(page: WikiPage) {
        databaseHelper.use {
            insert(
                TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail)
            )
        }
    }

    fun removePageById(pageId: Int) {
        databaseHelper.use {
            delete(
                TABLE_NAME,
                "id = {pageId}",
                "pageId" to pageId
            )
        }
    }

    private fun getAllHistory(): ArrayList<WikiPage> {
        var pages = ArrayList<WikiPage>()

        val articleRowParser =
            rowParser { id: Int, title: String, url: String, thumbnailJson: String ->
                val page = WikiPage()
                page.title = title
                page.pageid = id
                page.fullurl = url
                page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

                pages.add(page)
            }
        return pages
    }
}
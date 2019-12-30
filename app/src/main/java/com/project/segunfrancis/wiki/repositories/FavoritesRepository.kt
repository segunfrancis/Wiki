package com.project.segunfrancis.wiki.repositories

import com.google.gson.Gson
import com.project.segunfrancis.wiki.models.WikiPage
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

/**
 * Created by SegunFrancis
 */
class FavoritesRepository(val databaseHelper: ArticleDatabaseOpenHelper) {
    private val TABLE_NAME: String = "Favorites"

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

    fun removeFavoriteById(pageId: Int) {
        databaseHelper.use {
            delete(
                TABLE_NAME,
                "id = {pageId}",
                "pageId" to pageId
            )
        }
    }

}
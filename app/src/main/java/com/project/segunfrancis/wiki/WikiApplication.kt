package com.project.segunfrancis.wiki

import android.app.Application
import com.project.segunfrancis.wiki.managers.WikiManager
import com.project.segunfrancis.wiki.providers.ArticleDataProvider
import com.project.segunfrancis.wiki.repositories.ArticleDatabaseOpenHelper
import com.project.segunfrancis.wiki.repositories.FavoritesRepository
import com.project.segunfrancis.wiki.repositories.HistoryRepository

/**
 * Created by SegunFrancis
 */

class WikiApplication : Application() {
    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set

    override fun onCreate() {
        super.onCreate()

        // THE ORDER OF INSTANTIATION MATTERS
        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}
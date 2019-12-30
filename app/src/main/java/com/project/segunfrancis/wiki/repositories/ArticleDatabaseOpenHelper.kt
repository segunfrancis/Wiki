package com.project.segunfrancis.wiki.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

/**
 * Created by SegunFrancis
 */
class ArticleDatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "ArticleDatabase.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            "Favorites",
            true,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT
        )

        db?.createTable(
            "History",
            true,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
package com.project.segunfrancis.wiki.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.WikiApplication
import com.project.segunfrancis.wiki.managers.WikiManager
import com.project.segunfrancis.wiki.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast

class ArticleDetailActivity : AppCompatActivity() {

    private var wikiManager: WikiManager? = null
    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // get the page from the extras
        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson(wikiPageJson, WikiPage::class.java)
        supportActionBar?.title = currentPage?.title

        // Ensures URL is properly loaded rather than calling over to the webView
        article_detail_webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?, request: WebResourceRequest?
            ): Boolean {
                return true
            }
        }
        article_detail_webview.loadUrl(currentPage!!.fullurl)

        wikiManager?.addHistory(currentPage!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.action_favorite) {
            try {
                // determine if the article is already favorite or not
                if (wikiManager!!.getIsFavorite(currentPage!!.pageid!!)) {
                    wikiManager?.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                } else {
                    wikiManager?.addFavorite(currentPage!!)
                    toast("Article added to favorites")
                }
            } catch (e: Exception) {
                toast("Unable to update this article")
                Log.d("ArticleDetailActivity", e.localizedMessage!!)
            }
        }
        return true
    }
}

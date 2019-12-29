package com.project.segunfrancis.wiki.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.activities.ArticleDetailActivity
import com.project.segunfrancis.wiki.models.WikiPage
import com.squareup.picasso.Picasso

/**
 * Created by SegunFrancis
 */
class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val titleTextView = itemView.findViewById<TextView>(R.id.result_text)
    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener {
            val detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            val pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage) {
        if (page.thumbnail != null)
            Picasso.get().load(page.thumbnail!!.source).into(articleImageView)
        titleTextView.text = page.title
        currentPage = page
    }
}
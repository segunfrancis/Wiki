package com.project.segunfrancis.wiki.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.models.WikiPage
import com.squareup.picasso.Picasso

/**
 * Created by SegunFrancis
 */
class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val articleImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView = itemView.findViewById<TextView>(R.id.article_title)

    private var currentPage: WikiPage? = null

    fun updateWithPage(page: WikiPage) {
        currentPage = page

        titleTextView.text = page.title
        if (page.thumbnail != null) {
            Picasso.get().load(page.thumbnail!!.source).into(articleImageView)
        }
    }
}
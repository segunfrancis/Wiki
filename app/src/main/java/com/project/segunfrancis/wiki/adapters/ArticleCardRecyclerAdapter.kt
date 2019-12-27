package com.project.segunfrancis.wiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.holders.CardHolder
import com.project.segunfrancis.wiki.models.WikiPage

/**
 * Created by SegunFrancis
 */
class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardHolder>() {
    val currentResults: ArrayList<WikiPage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val cardItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int = currentResults.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val page = currentResults[position]
        // Update view with holder
        holder.updateWithPage(page)
    }
}
package com.project.segunfrancis.wiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.holders.CardHolder

/**
 * Created by SegunFrancis
 */
class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int = 15

    override fun onBindViewHolder(holder: CardHolder, position: Int) {

    }
}
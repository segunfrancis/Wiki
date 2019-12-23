package com.project.segunfrancis.wiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.holders.ListItemHolder

/**
 * Created by SegunFrancis
 */
class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int = 15

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

    }
}
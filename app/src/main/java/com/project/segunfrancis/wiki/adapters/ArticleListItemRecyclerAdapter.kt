package com.project.segunfrancis.wiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.holders.ListItemHolder
import com.project.segunfrancis.wiki.models.WikiPage

/**
 * Created by SegunFrancis
 */
class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>(){
    private val currentResults : ArrayList<WikiPage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int = currentResults.size

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val page = currentResults[position]
        holder.updateWithPage(page)
    }
}
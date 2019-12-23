package com.project.segunfrancis.wiki.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.wiki.R

/**
 * Created by SegunFrancis
 */
class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val articleImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val titleTextView = itemView.findViewById<TextView>(R.id.result_text)
}
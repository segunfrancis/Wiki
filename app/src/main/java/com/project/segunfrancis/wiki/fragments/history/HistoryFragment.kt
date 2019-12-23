package com.project.segunfrancis.wiki.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.adapters.ArticleCardRecyclerAdapter
import com.project.segunfrancis.wiki.adapters.ArticleListItemRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        historyViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        history_article_recycler.layoutManager = LinearLayoutManager(requireContext())
        history_article_recycler.adapter = ArticleListItemRecyclerAdapter()
    }
}
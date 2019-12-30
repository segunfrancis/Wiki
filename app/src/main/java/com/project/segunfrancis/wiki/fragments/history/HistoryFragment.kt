package com.project.segunfrancis.wiki.fragments.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.WikiApplication
import com.project.segunfrancis.wiki.adapters.ArticleListItemRecyclerAdapter
import com.project.segunfrancis.wiki.managers.WikiManager
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    private lateinit var historyViewModel: HistoryViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        historyViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        history_article_recycler.layoutManager = LinearLayoutManager(requireContext())
        history_article_recycler.adapter = ArticleListItemRecyclerAdapter()
    }
}
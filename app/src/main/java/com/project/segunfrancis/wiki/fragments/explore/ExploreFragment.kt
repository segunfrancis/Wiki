package com.project.segunfrancis.wiki.fragments.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.activities.SearchActivity
import com.project.segunfrancis.wiki.adapters.ArticleCardRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        exploreViewModel.text.observe(this, Observer {
            textView.text = it
        })*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_card_view.setOnClickListener {
            val searchIntent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(searchIntent)
        }
        explore_article_recycler.layoutManager = LinearLayoutManager(requireContext())
        explore_article_recycler.adapter = ArticleCardRecyclerAdapter()
    }
}
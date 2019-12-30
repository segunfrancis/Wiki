package com.project.segunfrancis.wiki.fragments.explore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.WikiApplication
import com.project.segunfrancis.wiki.activities.SearchActivity
import com.project.segunfrancis.wiki.adapters.ArticleCardRecyclerAdapter
import com.project.segunfrancis.wiki.managers.WikiManager
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    private lateinit var exploreViewModel: ExploreViewModel
    private var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        exploreViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    private fun getRandomArticles() {
        try {
            wikiManager?.getRandom(15) { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher.isRefreshing = false
                }
            }
        } catch (e: Exception) {
            // show alert
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(e.message).setTitle("oops!")
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_card_view.setOnClickListener {
            val searchIntent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(searchIntent)
        }
        explore_article_recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        explore_article_recycler.adapter = adapter

        refresher.setOnRefreshListener {
            refresher.isRefreshing = true
        }
        getRandomArticles()
    }
}
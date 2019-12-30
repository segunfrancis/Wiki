package com.project.segunfrancis.wiki.fragments.history

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.segunfrancis.wiki.R
import com.project.segunfrancis.wiki.WikiApplication
import com.project.segunfrancis.wiki.adapters.ArticleListItemRecyclerAdapter
import com.project.segunfrancis.wiki.managers.WikiManager
import com.project.segunfrancis.wiki.models.WikiPage
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class HistoryFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    private lateinit var historyViewModel: HistoryViewModel
    private val adapter = ArticleListItemRecyclerAdapter()

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
        history_article_recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        doAsync {
            val history = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(history as ArrayList<WikiPage>)
            requireActivity().runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_clear_history){
            // show confirmation alert
            requireActivity().alert("Are you sure you want to clear your history?", "Confirm") {
                yesButton {
                    // clear history
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager?.clearHistory()
                    }
                    requireActivity().runOnUiThread { adapter.notifyDataSetChanged() }
                    it.dismiss()
                }
                noButton {
                    // do nothing
                    it.dismiss()
                }
            }.show()
        }
        return true
    }
}
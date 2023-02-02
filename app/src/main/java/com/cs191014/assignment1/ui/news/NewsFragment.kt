package com.cs191014.assignment1.ui.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs191014.assignment1.R
import com.cs191014.assignment1.ui.NewsDetailActivity
import kotlinx.coroutines.launch
import java.io.Serializable

class NewsFragment : Fragment() {
    var loading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Lookup the recyclerview in activity layout
        val view: View = inflater.inflate(R.layout.fragment_news, container, false)
        val rvNews = view.findViewById(R.id.rvNews) as RecyclerView
        // Initialize records
        val newsModel: NewsModel =
            ViewModelProvider(requireActivity())[NewsModel::class.java]

        // Create adapter passing in the sample user data
        val adapter = NewsAdapter(
            newsModel.newsList.value ?: ArrayList(0),
            ::onNewsClickHandler,
        )
        // Attach the adapter to the recyclerview to populate items
        rvNews.adapter = adapter
        // Set layout manager to position the items
        rvNews.layoutManager = LinearLayoutManager(view.context)
        if (newsModel.newsList.value == null) {
            loadData(newsModel, adapter)
        }
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (newsModel.canLoadMore && lastVisibleItemPosition == adapter.itemCount - 1) {
                    loadData(newsModel, adapter)
                }
            }
        }
        rvNews.addOnScrollListener(scrollListener)
        // That's all!
        return view
    }

    private fun loadData(newsModel: NewsModel, newsAdapter: NewsAdapter) {
        if (!loading) {
            loading = true
            lifecycleScope.launch {
                val newData = newsModel.loadRecords()
                newsAdapter.addData(newData)
                Log.d("huzaifa", newData.size.toString())
                loading = false
            }
        }
    }

    private fun onNewsClickHandler(news: News) {
        activity?.let {
            val intent = Intent(it, NewsDetailActivity::class.java)
            intent.putExtra(
                "news",
                news as Serializable
            )
            it.startActivityFromFragment(this, intent, 1)
        }
    }
}
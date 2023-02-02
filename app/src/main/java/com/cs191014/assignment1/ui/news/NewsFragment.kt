package com.cs191014.assignment1.ui.news

import android.content.Intent
import android.os.Bundle
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
import com.cs191014.assignment1.ui.RecordDetailActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable

class NewsFragment : Fragment() {

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
        lifecycleScope.launch {
            if (newsModel.newsList.value == null) {
                newsModel.loadRecords()
            }
            // Create adapter passing in the sample user data
            val adapter = NewsAdapter(
                newsModel.newsList.value!!,
                ::onNewsClickHandler,
            )
            // Attach the adapter to the recyclerview to populate items
            rvNews.adapter = adapter
            // Set layout manager to position the items
            rvNews.layoutManager = LinearLayoutManager(view.context)
        }

        // That's all!
        return view
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
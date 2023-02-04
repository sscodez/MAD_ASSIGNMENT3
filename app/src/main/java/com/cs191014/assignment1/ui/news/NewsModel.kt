package com.cs191014.assignment1.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NewsModel : ViewModel() {
    private val _newsList: MutableLiveData<ArrayList<News>> =
        MutableLiveData<ArrayList<News>>()

    val newsList: LiveData<ArrayList<News>>
        get() = _newsList
    private var currentPage = 1
    var canLoadMore = true
    companion object {
        const val pageSize = 20
    }

    @Synchronized
    suspend fun loadRecords() : List<News> {
        if (_newsList.value == null) {
            _newsList.value = ArrayList(0)
        }
        val newsApi = RetrofitHelper.getInstance().create(NewsApi::class.java)
        val result =
            newsApi.getNews("us", "1dbf08ceb411481a8f1b7800b3419523", pageSize, currentPage)
        if (result.body() != null) {
            val fetchedNews = result.body()!!.newsList
            _newsList.value!!.addAll(fetchedNews)
            currentPage += 1
            if(fetchedNews.size < pageSize) {
                canLoadMore = false
            }
            return fetchedNews
        }
        return ArrayList(0)
    }
}

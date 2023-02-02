package com.cs191014.assignment1.ui.news

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NewsModel : ViewModel() {
    private val _newsList: MutableLiveData<ArrayList<News>> =
        MutableLiveData<ArrayList<News>>()

    val newsList: LiveData<ArrayList<News>>
        get() = _newsList


    suspend fun loadRecords() {
        val newsApi = RetrofitHelper.getInstance().create(NewsApi::class.java)
        val result = newsApi.getNews("us", "business", "1dbf08ceb411481a8f1b7800b3419523")
        if (result.body() != null) {
            _newsList.value = ArrayList(result.body()?.newsList)
        } else {
            _newsList.value = ArrayList(0)
        }
    }
}

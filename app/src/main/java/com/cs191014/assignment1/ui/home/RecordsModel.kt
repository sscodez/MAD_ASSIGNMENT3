package com.cs191014.assignment1.ui.home

import android.util.Log
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.cs191014.assignment1.ui.home.Record


class RecordsModel : ViewModel() {
    private val recordsList: MutableLiveData<ArrayList<Record>> = MutableLiveData<ArrayList<Record>>()
    val records: LiveData<ArrayList<Record>>
        get() = recordsList

    fun initializeRecords() {
        recordsList.value = Record.createRecordList(10)
    }
    fun addRecord(newRecord: Record) {
        Log.i("e", "${newRecord.id}")
        var tempList = recordsList.value
        tempList?.add(newRecord)
        Log.i("f", tempList?.size.toString())
        recordsList.value = tempList ?: ArrayList()
    }
}
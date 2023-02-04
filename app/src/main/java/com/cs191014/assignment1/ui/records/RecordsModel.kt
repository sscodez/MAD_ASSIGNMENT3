package com.cs191014.assignment1.ui.records

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.util.logging.LogRecord
import java.util.logging.Logger


class RecordsModel : ViewModel() {
    private val recordsList: MutableLiveData<ArrayList<Record>> =
        MutableLiveData<ArrayList<Record>>()

    val records: LiveData<ArrayList<Record>>
        get() = recordsList

//    fun initializeRecords() {
//        recordsList.value = Record.createRecordList(10)
//    }

    suspend fun loadRecords(context: Context): Unit {
        val db = RecordDatabase.getInstance(context.applicationContext)
        val recordDao = db.recordDao()
        val records = recordDao.getAll()
        recordsList.value = ArrayList(records);
    }


    fun markFavorite(record: Record, context: Context) {
//        val updatedRecord = recordsList.value!![index]
        record.isFav = !record.isFav;
        Log.i("updated", record.isFav.toString())
        viewModelScope.launch {
            val db = RecordDatabase.getInstance(context.applicationContext)
            db.transactionExecutor.execute {
                db.recordDao().update(record)
            }
        }
    }

    fun addRecord(newRecord: Record, context: Context) {
        var tempList = recordsList.value
        tempList?.add(newRecord)
        recordsList.value = tempList ?: ArrayList()
        viewModelScope.launch {
            val db = RecordDatabase.getInstance(context.applicationContext)
            db.transactionExecutor.execute {
                db.recordDao().insertAll(newRecord)
            }
        }
    }

    fun deleteRecord(record: Record, context: Context) {
        var tempList = recordsList.value
        recordsList.value = tempList ?: ArrayList()
        viewModelScope.launch {
            val db = RecordDatabase.getInstance(context.applicationContext)
            db.transactionExecutor.execute {
                db.recordDao().delete(record)
            }
        }
    }
}

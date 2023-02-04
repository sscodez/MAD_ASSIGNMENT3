package com.cs191014.assignment1.ui.add_record

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.cs191014.assignment1.ui.records.Record
import com.cs191014.assignment1.ui.records.RecordsModel
import kotlin.random.Random

class AddRecordViewModel : ViewModel() {
    var name: String = ""
    var description: String = ""
    var image: String? = null

    fun addRecord(activity: FragmentActivity) : Boolean {
        if(name.isBlank()) {
            return false
        }
        val recordsModel = ViewModelProvider(activity)[RecordsModel::class.java]
        val newRecord = Record(
            Random.nextInt(0, 10000000),
            name,
            description,
            image,
            false,
        );
        recordsModel.addRecord(newRecord, activity.applicationContext)
        return true
    }

}
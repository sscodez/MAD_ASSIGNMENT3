package com.cs191014.assignment1.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs191014.assignment1.R
import com.cs191014.assignment1.ui.home.RecordsModel
import java.io.Serializable


class HomeFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val recordsModel: RecordsModel = ViewModelProvider(requireActivity())[RecordsModel::class.java]
        recordsModel.initializeRecords()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Lookup the recyclerview in activity layout
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val rvRecords = view.findViewById(R.id.rvRecords) as RecyclerView
        // Initialize records
        val recordsModel: RecordsModel = ViewModelProvider(requireActivity())[RecordsModel::class.java]
//      Create adapter passing in the sample user data
        val adapter = RecordAdapter(recordsModel.records.value!!, this::onRecordClickHandler)
//        val adapter = RecordAdapter(Record.createRecordList(200), this::onRecordClickHandler)
        // Attach the adapter to the recyclerview to populate items
        rvRecords.adapter = adapter
        // Set layout manager to position the items
        rvRecords.layoutManager = LinearLayoutManager(view.context)
        // That's all!
        return view
    }

    private fun onRecordClickHandler(position:Int) {
        activity?.let {
            val intent = Intent(it, RecordDetailActivity::class.java)
            intent.putExtra("record", ViewModelProvider(requireActivity())[RecordsModel::class.java].records.value!![position] as Serializable)
            it.startActivityFromFragment(this, intent, 1)
        }
    }
}
package com.cs191014.assignment1.ui.settings

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
import com.cs191014.assignment1.ui.RecordDetailActivity
import com.cs191014.assignment1.ui.records.Record
import com.cs191014.assignment1.ui.records.RecordAdapter
import com.cs191014.assignment1.ui.records.RecordsModel
import kotlinx.coroutines.launch
import java.io.Serializable

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Lookup the recyclerview in activity layout
        val view: View = inflater.inflate(R.layout.fragment_favorites, container, false)

        // That's all!
        return view
    }

    private fun onRecordClickHandler(record: Record) {
        activity?.let {
            val intent = Intent(it, RecordDetailActivity::class.java)
            intent.putExtra(
                "record",
                record as Serializable
            )
            it.startActivityFromFragment(this, intent, 1)
        }
    }

//    private fun onRecordDeleted(position: Int) {
//        ViewModelProvider(requireActivity())[RecordsModel::class.java].deleteRecord(
//            mRecords[position],
//            context!!
//        )
//    }
//
//    private fun onRecordUpdated(position: Int) {
//        ViewModelProvider(requireActivity())[RecordsModel::class.java].markFavorite(
//            position,
//            context!!
//        )
//    }
}
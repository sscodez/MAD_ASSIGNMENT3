package com.cs191014.assignment1.ui.add_record

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cs191014.assignment1.R
import com.cs191014.assignment1.databinding.FragmentAddRecordBinding
import com.cs191014.assignment1.ui.home.HomeFragment
import com.cs191014.assignment1.ui.home.Record
import com.cs191014.assignment1.ui.home.RecordsModel
import kotlin.random.Random

class AddRecordFragment : Fragment() {
    private var _binding: FragmentAddRecordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recordsModel = ViewModelProvider(requireActivity())[RecordsModel::class.java]

        _binding = FragmentAddRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val addRecordName: EditText = binding.addRecordName
        val addRecordDescription: EditText = binding.addRecordDescription
        val addRecordImage: EditText = binding.addRecordImage

        val addRecordButton: Button = binding.addRecordButton
        addRecordButton.setOnClickListener {
            if(addRecordName.text.isNotBlank()) {
                val newRecord = Record(
                    Random.nextInt(0, 10000000),
                    addRecordName.text.toString(),
                    addRecordDescription.text.toString(),
                    addRecordImage.text.toString()
                );
                recordsModel.addRecord(newRecord)
                addRecordName.setText("")
                addRecordDescription.setText("")
                addRecordImage.setText("")
            }
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.cs191014.assignment1.ui.add_record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cs191014.assignment1.databinding.FragmentAddRecordBinding
import com.cs191014.assignment1.ui.records.Record
import com.cs191014.assignment1.ui.records.RecordsModel
import kotlin.random.Random

class AddRecordFragment : Fragment() {
    private var _binding: FragmentAddRecordBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recordsModel = ViewModelProvider(requireActivity())[RecordsModel::class.java]

        _binding = FragmentAddRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addRecordImage: EditText = binding.addRecordImage
        addRecordImage.doAfterTextChanged {
            var newUrl = it?.toString()
            if (newUrl == "") {
                newUrl = null
            }
            addRecordViewModel.image = newUrl
        }
        val addRecordName: EditText = binding.addRecordName
        val addRecordDescription: EditText = binding.addRecordDescription
        val addRecordImage: EditText = binding.addRecordImage

        val addRecordButton: Button = binding.addRecordButton
        addRecordButton.setOnClickListener {
            val addImage = binding.addRecordImage
            val addName = binding.addRecordName
            val addDescription = binding.addRecordDescription
            if (addRecordViewModel.name.isNotBlank()) {
                val newRecord = Record(
                    Random.nextInt(0, 10000000),
                    addRecordViewModel.name,
                    addRecordViewModel.description,
                    addRecordViewModel.image,
                    false,
                );
                recordsModel.addRecord(newRecord, context!!)
                addImage.setText("")
                addName.setText("")
                addDescription.setText("")
            }
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
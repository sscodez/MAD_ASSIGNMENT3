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

class AddRecordFragment : Fragment() {
    private var _binding: FragmentAddRecordBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addRecordViewModel =
            ViewModelProvider(this)[AddRecordViewModel::class.java]

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
        addRecordName.doAfterTextChanged {
            addRecordViewModel.name = it?.toString() ?: ""
        }
        val addRecordDescription: EditText = binding.addRecordDescription
        addRecordDescription.doAfterTextChanged {
            addRecordViewModel.description = it?.toString() ?: ""
        }
        val addRecordButton: Button = binding.addRecordButton
        addRecordButton.setOnClickListener {
            if (addRecordViewModel.addRecord(requireActivity())) {
                binding.addRecordImage.setText("")
                binding.addRecordName.setText("")
                binding.addRecordDescription.setText("")
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
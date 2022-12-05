package com.cs191014.assignment1.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cs191014.assignment1.R
import com.cs191014.assignment1.databinding.ActivityMainBinding
import com.cs191014.assignment1.databinding.ActivityRecordDetailsBinding
import com.cs191014.assignment1.databinding.FragmentAddRecordBinding

class RecordDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val record = intent.getSerializableExtra("record") as? Record

        val recordDetailsName: TextView = binding.recordDetailName
        recordDetailsName.text = record?.name
        recordDetailsName.textSize = 28F

        val recordDetailsDescription: TextView = binding.recordDetailDescription
        recordDetailsDescription.text = record?.description

        val recordDetailsImage: ImageView = binding.recordDetailImage

        Glide.with(recordDetailsImage.context)
            .load(record?.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_home_black_24dp)
            .into(recordDetailsImage)
    }
}
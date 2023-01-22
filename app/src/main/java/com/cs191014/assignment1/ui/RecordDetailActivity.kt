package com.cs191014.assignment1.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cs191014.assignment1.R
import com.cs191014.assignment1.databinding.ActivityRecordDetailsBinding
import com.cs191014.assignment1.ui.records.Record

class RecordDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        mTitle.text = "Record Details"

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
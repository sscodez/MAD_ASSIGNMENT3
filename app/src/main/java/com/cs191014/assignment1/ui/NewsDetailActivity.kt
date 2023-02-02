package com.cs191014.assignment1.ui

import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cs191014.assignment1.R
import com.cs191014.assignment1.databinding.ActivityNewsDetailsBinding
import com.cs191014.assignment1.ui.news.News
import java.text.SimpleDateFormat

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news = intent.getSerializableExtra("news") as? News

        val newsDetailSource: TextView = binding.newsDetailSource
        val source = "Source: ${news?.source?.name}"
        newsDetailSource.text = source
        if(news?.source?.name == null) {
            newsDetailSource.visibility = View.INVISIBLE
        }

        val newsDetailPublisher: TextView = binding.newsDetailPublisher
        val publishingDetails = "${news?.author ?: "Anonymous"} at ${(SimpleDateFormat("dd MMM yyyy, HH:mm:ss ").format(news?.publishedAt))}"
        newsDetailPublisher.text = publishingDetails

        val newsDetailImage: ImageView = binding.newsDetailImage
        Glide.with(newsDetailImage.context)
            .load(news?.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(Uri.parse("file:///android_asset/breaking_news.jpg"))
            .into(newsDetailImage)

        val newsDetailTitle: TextView = binding.newsDetailTitle
        newsDetailTitle.text = news?.title

        val newsDetailDescription: TextView = binding.newsDetailDescription
        newsDetailDescription.text = news?.description

        val newsDetailContent: TextView = binding.newsDetailContent
        newsDetailContent.text = news?.content


    }
}
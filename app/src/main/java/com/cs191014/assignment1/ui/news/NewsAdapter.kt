package com.cs191014.assignment1.ui.news

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs191014.assignment1.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NewsAdapter(
    private var mNews: ArrayList<News>,
    val itemClickHandler: (News) -> Unit,
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val cardView = itemView.findViewById<CardView>(R.id.news_card)!!
        val imageView = itemView.findViewById<ImageView>(R.id.news_image)!!
        val titleTextView = itemView.findViewById<TextView>(R.id.news_title)!!
        val authorTextView = itemView.findViewById<TextView>(R.id.news_author)!!
        val descriptionTextView = itemView.findViewById<TextView>(R.id.news_description)!!
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val newsView = inflater.inflate(R.layout.item_news, parent, false)
        val viewHolder = ViewHolder(newsView)
        viewHolder.cardView.setOnClickListener {
            if (viewHolder.adapterPosition >= 0 && viewHolder.adapterPosition < mNews.size) {
                itemClickHandler(mNews[viewHolder.adapterPosition])
            }
        }

        // Return a new holder instance
        return viewHolder
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val news: News = mNews[position]
        // Set item views based on your views and data model

        val imageView = viewHolder.imageView
        Glide.with(imageView.context)
            .load(news.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(Uri.parse("file:///android_asset/breaking_news.jpg"))
            .into(imageView)
        val titleTextView = viewHolder.titleTextView
        titleTextView.text = news.title
        val authorTextView = viewHolder.authorTextView
        val publishingDetails = "${news.author ?: "Anonymous"} at ${(SimpleDateFormat("dd MMM yyyy, HH:mm:ss ").format(news.publishedAt))}"
        authorTextView.text = publishingDetails
        val descriptionTextView = viewHolder.descriptionTextView
        descriptionTextView.text = news.description
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mNews.size
    }
    fun addData(newData: List<News>) {
        mNews.addAll(newData)
        notifyDataSetChanged()
    }
}
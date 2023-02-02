package com.cs191014.assignment1.ui.records

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cs191014.assignment1.R


class RecordAdapter(
    private var mRecords: ArrayList<Record>,
    val itemClickHandler: (Record) -> Unit,
    val deleteHandler: (Record, Context) -> Unit,
    val updateHandler: (Record, Context) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val cardView = itemView.findViewById<CardView>(R.id.record_card)!!
        val imageView = itemView.findViewById<ImageView>(R.id.record_image)!!
        val titleTextView = itemView.findViewById<TextView>(R.id.record_name)!!
        val subtitleTextView = itemView.findViewById<TextView>(R.id.record_description)!!
        val favButton = itemView.findViewById<ImageButton>(R.id.favButton)!!
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)!!
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val recordView = inflater.inflate(R.layout.item_record, parent, false)
        val viewHolder = ViewHolder(recordView)
        viewHolder.cardView.setOnClickListener {
            if (viewHolder.adapterPosition >= 0 && viewHolder.adapterPosition < mRecords.size) {
                itemClickHandler(mRecords[viewHolder.adapterPosition])
            }
        }
        viewHolder.favButton.setOnClickListener {
            if (viewHolder.adapterPosition >= 0 && viewHolder.adapterPosition < mRecords.size) {
                updateHandler(mRecords[viewHolder.adapterPosition], context)
            }
            notifyItemChanged(viewHolder.adapterPosition)
        }
        val deleteButton = viewHolder.deleteButton
        deleteButton.setOnClickListener {
            if (viewHolder.adapterPosition >= 0 && viewHolder.adapterPosition < mRecords.size) {
                deleteHandler(mRecords.removeAt(viewHolder.adapterPosition), context)
            }
            notifyItemRemoved(viewHolder.adapterPosition)
        }

        // Return a new holder instance
        return viewHolder
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val record: Record = mRecords[position]
        // Set item views based on your views and data model

        val imageView = viewHolder.imageView
        Glide.with(imageView.context)
            .load(record.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_home_black_24dp)
            .into(imageView)
        val titleTextView = viewHolder.titleTextView
        titleTextView.text = record.name
        val subtitleTextView = viewHolder.subtitleTextView
        subtitleTextView.text = record.description

        val favButton = viewHolder.favButton
        favButton.setColorFilter(
            ContextCompat.getColor(
                context,
                if(record.isFav) R.color.red else R.color.grey
            ))
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mRecords.size
    }
}
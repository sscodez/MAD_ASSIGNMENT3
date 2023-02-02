package com.cs191014.assignment1.ui.news

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class News(
    @SerializedName("source")
    val source: Source?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val image: String?,
    @SerializedName("publishedAt")
    var publishedAt: Date,
    @SerializedName("content")
    var content: String?
) : Serializable

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
) : Serializable
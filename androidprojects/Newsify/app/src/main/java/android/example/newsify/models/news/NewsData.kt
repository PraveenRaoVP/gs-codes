package android.example.newsify.models.news

import android.example.newsify.models.news.Data

data class NewsData(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)
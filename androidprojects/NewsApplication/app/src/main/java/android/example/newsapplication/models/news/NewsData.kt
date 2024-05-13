package android.example.newsapplication.models.news

import android.example.newsapplication.models.news.Data

data class NewsData(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)
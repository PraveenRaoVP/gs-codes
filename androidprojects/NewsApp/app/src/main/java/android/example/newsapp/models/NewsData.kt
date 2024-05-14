package android.example.newsapp.models

data class NewsData(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)
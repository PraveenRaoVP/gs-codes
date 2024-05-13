package android.example.newsifytrial.models.news

data class NewsData(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)
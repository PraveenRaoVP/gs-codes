package android.example.newsifytrial.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_property_table")
data class NewsProperty (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: String,
    val author: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
)
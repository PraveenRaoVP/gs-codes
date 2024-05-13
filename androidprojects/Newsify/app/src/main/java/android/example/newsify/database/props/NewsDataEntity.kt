package android.example.newsify.database.props

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_data")
data class NewsDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: String,
    val success: Boolean
)
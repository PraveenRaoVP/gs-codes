package android.example.newsify.database.props

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "data"
)
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val newsDataId: Int, // Foreign key referencing NewsDataEntity
    val author: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
)
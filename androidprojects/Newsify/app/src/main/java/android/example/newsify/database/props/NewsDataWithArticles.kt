package android.example.newsify.database.props

import androidx.room.Embedded
import androidx.room.Relation

data class NewsDataWithArticles(
    @Embedded val newsData: NewsDataEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "newsDataId"
    )
    val articles: List<DataEntity>
)

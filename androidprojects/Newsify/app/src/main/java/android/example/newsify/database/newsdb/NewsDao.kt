package android.example.newsify.database.newsdb

import android.example.newsify.database.props.DataEntity
import android.example.newsify.database.props.NewsDataEntity
import android.example.newsify.database.props.NewsDataWithArticles
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    // Insert NewsDataEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsData(newsData: NewsDataEntity)

    // Insert list of DataEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<DataEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: DataEntity)

    // Get all news data order by date desc
    @Transaction
    @Query("SELECT * FROM data ORDER BY id DESC")
    fun getAllNews(): Flow<List<DataEntity>>

    // Get news data by category
    @Transaction
    @Query("SELECT * FROM data INNER JOIN news_data ON data.id = news_data.id WHERE news_data.category = :category ORDER BY data.id DESC")
    fun getNewsByCategory(category: String): Flow<List<DataEntity>>

    // Get news data by id
    @Transaction
    @Query("SELECT * FROM news_data WHERE id = :id")
    fun getNewsById(id: Int): Flow<NewsDataWithArticles>

    // Get paginated news data by category
    @Transaction
    @Query("SELECT * FROM data INNER JOIN news_data ON data.id = news_data.id WHERE news_data.category = :category ORDER BY data.id DESC LIMIT :pageSize OFFSET :offset")
    fun getPaginatedNewsByCategory(category: String, pageSize: Int, offset: Int): Flow<List<DataEntity>>

    @Query("DELETE FROM news_data")
    suspend fun deleteAllNewsData()

    @Query("DELETE FROM data")
    suspend fun deleteAllNews()

}
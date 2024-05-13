package android.example.newsifytrial.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface NewsDao {
    @Insert
    suspend fun insertNews(newsProperty: NewsProperty)

    @Transaction
    @Query("SELECT * FROM news_property_table ORDER BY id DESC LIMIT :pageSize OFFSET :offset")
    suspend fun getAllNews(pageSize: Int, offset: Int): List<NewsProperty>

    @Transaction
    @Query("SELECT * FROM news_property_table WHERE category = :category")
    suspend fun getNewsByCategory(category: String): List<NewsProperty>

    @Transaction
    @Query("SELECT * FROM news_property_table WHERE category = :category ORDER BY id DESC LIMIT :pageSize OFFSET :offset")
    suspend fun getPaginatedNewsByCategory(category: String, pageSize: Int, offset: Int): List<NewsProperty>

    @Query("DELETE FROM news_property_table")
    suspend fun deleteAllNews()

}
package android.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert
    suspend fun insert(news: NewsProperty)

    @Query("SELECT * FROM news_table ORDER BY id DESC")
    suspend fun getAllNews() : List<NewsProperty>

    @Query("SELECT * FROM news_table WHERE category = :category ORDER BY id DESC")
    suspend fun getNewsByCategory(category: String) : List<NewsProperty>

    // pagination
    @Query("SELECT * FROM news_table WHERE category = :category ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getNewsByCategoryWithLimit(category: String, limit: Int, offset: Int) : List<NewsProperty>

    @Query("SELECT * FROM news_table ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllNewsWithLimit(limit: Int, offset: Int) : List<NewsProperty>

    // search and pagination
    @Query("SELECT * FROM news_table WHERE title LIKE :searchQuery ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun searchNewsWithLimit(searchQuery: String, limit: Int, offset: Int) : List<NewsProperty>

    @Query("SELECT * FROM news_table WHERE title LIKE :searchQuery ORDER BY id DESC")
    suspend fun searchNews(searchQuery: String) : List<NewsProperty>

    @Query("DELETE FROM news_table")
    suspend fun clear()

    @Query("SELECT * FROM news_table WHERE url = :url")
    suspend fun getNewsByUrl(url: String): NewsProperty

    // get number of records in the database
    @Query("SELECT COUNT(*) FROM news_table")
    suspend fun getNumberOfRecords(): Int
}
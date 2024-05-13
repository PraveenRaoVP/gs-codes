package android.example.newsify3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao{
    @Insert
    suspend fun insert(news: NewsProperty)

    @Query("SELECT * FROM News")
    suspend fun getAll(): List<NewsProperty?>

    @Query("DELETE FROM News")
    suspend fun clear()

    @Query("SELECT * FROM News WHERE category = :category LIMIT :pageSize OFFSET :offset")
    suspend fun getAllByCategory(category: String, pageSize: Int, offset: Int): List<NewsProperty?>

    @Query("SELECT * FROM News LIMIT :pageSize OFFSET :offset")
    suspend fun getAll(pageSize: Int, offset: Int): List<NewsProperty?>

    @Query("DELETE FROM News")
    suspend fun deleteAll()

    @Query("SELECT * FROM News WHERE title LIKE '%' || :query || '%' LIMIT :pageSize OFFSET :offset")
    fun search(query: String?, pageSize: Int, offset: Int): List<NewsProperty>
}
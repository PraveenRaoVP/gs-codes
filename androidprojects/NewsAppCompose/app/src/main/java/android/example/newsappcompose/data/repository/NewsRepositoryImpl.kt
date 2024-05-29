package android.example.newsappcompose.data.repository

import android.example.newsappcompose.data.remote.NewsApi
import android.example.newsappcompose.data.remote.NewsPagingSources
import android.example.newsappcompose.domain.model.Article
import android.example.newsappcompose.domain.repository.NewsRepository
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> { // gets news from the source page by page. This requires the api to be paginated.
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSources(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}
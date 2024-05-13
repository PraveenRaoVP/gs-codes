package android.example.newsify.adapters

import android.example.newsify.database.props.DataEntity
import android.example.newsify.databinding.ListNewsItemBinding
import android.example.newsify.screens.newslist.NewsListViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class NewsItemDiffCallback : DiffUtil.ItemCallback<DataEntity>() {
    override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
        return oldItem == newItem
    }
}
class NewsItemAdapter(private val newsListViewModel: NewsListViewModel) : ListAdapter<DataEntity, NewsItemAdapter.NewsItemViewHolder>(NewsItemDiffCallback()) {

    class NewsItemViewHolder(private val binding: ListNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val newsImage: ImageView = binding.articleImage
        val newsCard: CardView = binding.cardView
        var newsTitle: TextView = binding.titleText
        val authorText: TextView = binding.authorText

        fun bind(newsData: DataEntity) {
            binding.newsData = newsData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListNewsItemBinding.inflate(layoutInflater, parent, false)
                return NewsItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsData = getItem(position)
        holder.bind(newsData)

        Picasso.get().load(newsData.imageUrl).into(holder.newsImage, object :  Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Log.e("NewsItemAdapter", "Failed to load image: ${newsData.imageUrl}")
            }
        })

        holder.newsTitle.text = newsData.title
        holder.authorText.text = newsData.author

        holder.newsCard.setOnClickListener {
            newsListViewModel.onNewsItemClicked(newsData)
        }
    }
}
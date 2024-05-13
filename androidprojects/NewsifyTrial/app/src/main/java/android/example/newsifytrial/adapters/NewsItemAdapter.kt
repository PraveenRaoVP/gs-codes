package android.example.newsifytrial.adapters

import android.example.newsifytrial.database.NewsProperty
import android.example.newsifytrial.databinding.ListNewsItemBinding
import android.example.newsifytrial.screens.newslist.NewsListViewModel
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

class NewsItemDiffCallback : DiffUtil.ItemCallback<NewsProperty>() {
    override fun areItemsTheSame(oldItem: NewsProperty, newItem: NewsProperty): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: NewsProperty, newItem: NewsProperty): Boolean {
        return oldItem == newItem
    }
}

class NewsItemAdapter(private val newsListViewModel: NewsListViewModel) :
    ListAdapter<NewsProperty, NewsItemAdapter.NewsItemViewHolder>(NewsItemDiffCallback()){
    class NewsItemViewHolder(private val binding: ListNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val newsImage: ImageView = binding.articleImage
        val newsCard: CardView = binding.cardView
        var newsTitle: TextView = binding.titleText
        val authorText: TextView = binding.authorText

        fun bind(newsProperty: NewsProperty) {
            binding.newsProperty = newsProperty
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
        val newsProperty = getItem(position)
        holder.bind(newsProperty)

        Picasso.get().load(newsProperty.imageUrl).into(holder.newsImage, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Log.e("NewsItemAdapter", "Failed to load image: ${newsProperty.imageUrl}")
            }
        })

        holder.newsTitle.text = newsProperty.title
        holder.authorText.text = newsProperty.author

        holder.newsCard.setOnClickListener {
            Log.i("NewsItemAdapter", "Clicked on news item with title: ${newsProperty.title}")
            newsListViewModel.navigateToDetails(newsProperty.readMoreUrl!!)
        }
    }
}
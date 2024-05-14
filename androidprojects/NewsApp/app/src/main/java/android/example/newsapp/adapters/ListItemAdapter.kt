package android.example.newsapp.adapters

import android.example.newsapp.databinding.ListCategoriesItemBinding
import android.example.newsapp.screens.newslist.NewsListViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListItemDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }


}

class ListItemAdapter(private val newsListViewModel: NewsListViewModel) : ListAdapter<String, ListItemAdapter.ListItemViewHolder>(ListItemDiffUtil()) {
    class ListItemViewHolder(private val binding: ListCategoriesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val categoryItem = binding.categoryItem

        fun bind(category: String) {
            binding.category = category
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ListItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListCategoriesItemBinding.inflate(layoutInflater, parent, false)
                return ListItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)

        holder.categoryItem.setOnClickListener {
            newsListViewModel.onClickCategory(category!!)
        }
    }
}
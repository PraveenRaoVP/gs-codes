package android.example.newsifytrial.adapters

import android.example.newsifytrial.databinding.ListCategoryItemBinding
import android.example.newsifytrial.screens.newslist.NewsListViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CategoryItemDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class CategoryItemAdapter(private val newsListViewModel: NewsListViewModel) :
    ListAdapter<String, CategoryItemAdapter.CategoryViewHolder>(CategoryItemDiffCallback()) {

    class CategoryViewHolder(private val binding: ListCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryItem = binding.categoryItem

        fun bind(category: String) {
            binding.category = category
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListCategoryItemBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)

        holder.categoryItem.setBackgroundColor(
            holder.itemView.context.resources.getColor(android.R.color.transparent)
        )

        holder.categoryItem.setOnClickListener {
            newsListViewModel.currentCategory.value = category

            holder.categoryItem.setBackgroundColor(
                holder.itemView.context.resources.getColor(android.R.color.holo_blue_light)
            )

            val recyclerView = holder.itemView.parent as? RecyclerView
            recyclerView?.let { rv ->
                for (i in 0 until rv.childCount) {
                    val child = rv.getChildAt(i)
                    val viewHolder = rv.getChildViewHolder(child) as? CategoryViewHolder
                    if (viewHolder != null && viewHolder.adapterPosition != position) {
                        viewHolder.categoryItem.setBackgroundColor(
                            viewHolder.itemView.context.resources.getColor(android.R.color.transparent)
                        )
                    }
                }
            }

            // Get the category corresponding to the clicked item
            val category = getItem(position)

            // Call ViewModel function to get news by category
            Log.i("CategoryItemAdapter", "Category clicked: $category")
            newsListViewModel.currentPage = 0
            newsListViewModel.onClickCategory(category)
        }
    }

}



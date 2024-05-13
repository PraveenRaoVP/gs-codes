package android.example.newsify.adapters

import android.example.newsify.databinding.ListCategoryItemBinding
import android.example.newsify.screens.newslist.NewsListViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CategoryItemAdapter(private val newsListViewModel: NewsListViewModel) :
    ListAdapter<String, CategoryItemAdapter.CategoryItemViewHolder>(CategoryItemDiffCallback()) {

    class CategoryItemViewHolder(private val binding: ListCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryItem = binding.categoryItem

        fun bind(category: String) {
            binding.category = category
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListCategoryItemBinding.inflate(layoutInflater, parent, false)
                return CategoryItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)

        holder.categoryItem.setBackgroundColor(
            holder.itemView.context.resources.getColor(android.R.color.transparent))

        // Set click listener for item
        holder.itemView.setOnClickListener {
            // Background color change for the clicked item
            newsListViewModel.currentCategory.value = category
            holder.categoryItem.setBackgroundColor(
                holder.itemView.context.resources.getColor(android.R.color.holo_blue_light)
            )

            // Remove background color from other items
            val recyclerView = holder.itemView.parent as? RecyclerView
            recyclerView?.let { rv ->
                for (i in 0 until rv.childCount) {
                    val child = rv.getChildAt(i)
                    val viewHolder = rv.getChildViewHolder(child) as? CategoryItemViewHolder
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
            newsListViewModel.getDataFromDatabase(category)
        }
    }
}

class CategoryItemDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
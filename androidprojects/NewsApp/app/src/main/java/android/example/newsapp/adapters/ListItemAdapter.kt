package android.example.newsapp.adapters

import android.example.newsapp.databinding.ListCategoriesItemBinding
import android.example.newsapp.screens.newslist.NewsListViewModel
import android.graphics.Color
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

        if (position == 0)
            holder.categoryItem.setCardBackgroundColor(Color.parseColor("#E4DDDD"))
        else
            holder.categoryItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        // the category all should have a different color in first load

        holder.categoryItem.setOnClickListener {

            // change the color of the selected category to #E4DDDD and the rest to white
            holder.categoryItem.setCardBackgroundColor(Color.parseColor("#E4DDDD"))

            val recyclerView = holder.itemView.parent as? RecyclerView
            recyclerView?.let { rv ->
                for (i in 0 until rv.childCount) {
                    val child = rv.getChildAt(i)
                    val viewHolder = rv.getChildViewHolder(child) as? ListItemViewHolder
                    if (viewHolder != null && viewHolder.adapterPosition != position) {
                        viewHolder.categoryItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                    }
                }
            }
            newsListViewModel.onClickCategory(category!!)
        }
    }
}
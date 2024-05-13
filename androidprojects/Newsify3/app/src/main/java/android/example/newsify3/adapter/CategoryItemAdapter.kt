package android.example.newsify3.adapter

import android.example.newsify3.R
import android.example.newsify3.screens.newslist.NewsListViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryItemAdapter(private val dataList: List<String>,
                          private val newsViewModel: NewsListViewModel)
    : RecyclerView.Adapter<CategoryItemAdapter.ViewHolderClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_category_item, parent, false)
        return ViewHolderClass(itemView)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]

        holder.rvTitle.text = currentItem

        // Set click listener
        holder.itemView.setOnClickListener {
            newsViewModel.category = currentItem
            newsViewModel.currentPage = 0
            newsViewModel.searchFlag = false
            newsViewModel.dbRetrieveCategory(currentItem, "category")
        }
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvTitle: TextView = itemView.findViewById(R.id.category_name)
    }
}
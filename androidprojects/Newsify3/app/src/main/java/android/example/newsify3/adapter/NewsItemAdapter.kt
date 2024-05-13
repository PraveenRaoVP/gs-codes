package android.example.newsify3.adapter

import android.example.newsify3.R
import android.example.newsify3.database.NewsProperty
import android.example.newsify3.databinding.ListNewsItemBinding
import android.example.newsify3.screens.newslist.NewsListFragmentDirections
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsItemAdapter(private val navController: NavController):
    RecyclerView.Adapter<NewsItemAdapter.ViewHolderClass>() {
    private val dataList: MutableList<NewsProperty?>? = mutableListOf()

    fun addData(newData: List<NewsProperty?>?) {
        if (newData != null) {
            dataList?.addAll(newData)
        }
        notifyDataSetChanged() // Notify adapter that data set has changed
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_news_item, parent, false)
        return ViewHolderClass.from(parent)
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList?.get(position)!!

        val url: String = currentItem?.imageUrl!!.toString()

        // Load the image into the ImageView using Glide
        Picasso.get().load(url).into(holder.articleImage)
        holder.titleText.text = currentItem?.title.toString()

        holder.authorText.text = currentItem?.author.toString()

        // Set click listener
        holder.itemView.setOnClickListener {
            navController.navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsWebViewFragment(currentItem?.readMoreUrl.toString()))
        }
    }

    fun clearData() {
        dataList?.clear()
        notifyDataSetChanged()
    }

    class ViewHolderClass(private val binding: ListNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        val articleImage: ImageView = binding.articleImage
        val titleText: TextView = binding.titleText
        val authorText: TextView = binding.authorText

        companion object {
            fun from(parent: ViewGroup): ViewHolderClass {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListNewsItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolderClass(binding)
            }
        }
    }
}
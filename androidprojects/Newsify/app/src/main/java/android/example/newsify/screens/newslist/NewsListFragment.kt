package android.example.newsify.screens.newslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsify.adapters.CategoryItemAdapter
import android.example.newsify.adapters.NewsItemAdapter
import android.example.newsify.database.newsdb.NewsDatabase
import android.example.newsify.databinding.FragmentNewsListBinding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NewsListFragment : Fragment() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var newsListRecyclerView: RecyclerView
    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var binding: FragmentNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        categoryRecyclerView = binding.categoryRecyclerView
        categoryRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        newsListRecyclerView = binding.newsListRecyclerView
        newsListRecyclerView.layoutManager = LinearLayoutManager(this.context)

        val application = requireNotNull(this.activity).application

        val dataSource = NewsDatabase.getInstance(application).newsDataDao()

        val viewModelFactory = NewsListViewModelFactory(dataSource, application)

        newsListViewModel = ViewModelProvider(this, viewModelFactory)[NewsListViewModel::class.java]

        binding.lifecycleOwner = this

        getCategories()

        newsListViewModel.showErrorToast.observe(viewLifecycleOwner) {
            if(it) {
                Toast.makeText(context, "Something went wrong. Please Try Again.", Toast.LENGTH_SHORT).show()
                newsListViewModel.onShowErrorToastComplete()
            }
        }

        newsListViewModel.clickedNewsItem.observe(viewLifecycleOwner) {
            if(it) {
                newsListViewModel.clickedItemDetails.value?.let { it1 ->
                    this.findNavController().navigate(
                        NewsListFragmentDirections.actionNewsListFragmentToNewsWebViewFragment(it1.readMoreUrl)
                    )
                }
                newsListViewModel.onCompleteNavigation()
            }
        }

        newsListViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                // Show loading screen
                showLoadingScreen()
            } else {
                // Hide loading screen
                hideLoadingScreen()
            }
        }

        newsListViewModel.hasMoreData.observe(viewLifecycleOwner) { hasMoreData ->
            if (!hasMoreData) {
                // Show "No more data" toast message
                Toast.makeText(context, "No more data.", Toast.LENGTH_SHORT).show()
            }
        }

        newsListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                 super.onScrolled(recyclerView, dx, dy)
                 val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                 val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                 val totalItemCount = layoutManager.itemCount
                 if (lastVisibleItemPosition == totalItemCount - 1) {
                     // Load next page
                     newsListViewModel.loadNextPage(newsListViewModel.currentCategory.value ?: "all")
                 }
             }
         })

        return binding.root
    }

    private fun getCategories() {
        val adapter = CategoryItemAdapter(newsListViewModel)
        categoryRecyclerView.adapter = adapter

        adapter.submitList(newsListViewModel.categories)
    }

    private fun populateNewsList(category: String) {
        val adapter = NewsItemAdapter(newsListViewModel)
        newsListRecyclerView.adapter = adapter

        newsListViewModel.fetchAllDataFromApi()
        newsListViewModel.getDataFromDatabase(category)

        newsListViewModel.newList.observe(viewLifecycleOwner) { newsDataList ->
            adapter.submitList(newsDataList)
        }
    }

    private fun showLoadingScreen() {
        // Show loading screen
        binding.loadingIndicator.visibility = View.VISIBLE
        // Disable scrolling to prevent user interaction while loading
        newsListRecyclerView.isEnabled = false
    }

    private fun hideLoadingScreen() {
        // Hide loading screen
        binding.loadingIndicator.visibility = View.GONE
        // Enable scrolling after loading is finished
        newsListRecyclerView.isEnabled = true
    }

    companion object {

    }
}
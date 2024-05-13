package android.example.newsifytrial.screens.newslist

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsifytrial.R
import android.example.newsifytrial.adapters.CategoryItemAdapter
import android.example.newsifytrial.adapters.NewsItemAdapter
import android.example.newsifytrial.database.NewsDatabase
import android.example.newsifytrial.databinding.FragmentNewsListBinding
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsListFragment : Fragment() {
    private lateinit var binding: FragmentNewsListBinding
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application: Application = requireNotNull(this.activity).application
        val dataSource = NewsDatabase.getInstance(application).newsDao()
        val viewModelFactory = NewsListViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory)[NewsListViewModel::class.java]

        viewModel.populatingData()
//        viewModel.getDataFromDatabase("all")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        newsRecyclerView = binding.newsListRecyclerView
        categoryRecyclerView = binding.categoryRecyclerView
        categoryRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        newsRecyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.getDataFromDatabase(viewModel.currentCategory.value ?: "all")

        viewModel.categoryClicked.observe(viewLifecycleOwner) {
            if(it) {
                viewModel.getDataFromDatabase(viewModel.currentCategory.value ?: "all")
            }
        }

        viewModel.showErrorToast.observe(viewLifecycleOwner) {
            if(it) {
                Toast.makeText(context, "Something went wrong. Please Try Again.", Toast.LENGTH_SHORT).show()
                viewModel.onCompleteToastShow()
            }
        }

        viewModel.clickedNewsItem.observe(viewLifecycleOwner) { clicked ->
            if(clicked) {
                Log.i("NewsListFragment", "Clicked Item: ${viewModel.clickedItemDetails.value}")
                val action = NewsListFragmentDirections.actionNewsListFragmentToNewsWebViewFragment(viewModel.clickedItemDetails.value ?: "")
                findNavController().navigate(action)
                viewModel.onCompleteNavigation()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.GONE
            }
        }

        val adapter = NewsItemAdapter(viewModel)
        newsRecyclerView.adapter = adapter

        val categoryItemAdapter = CategoryItemAdapter(viewModel)
        categoryRecyclerView.adapter = categoryItemAdapter

        // Add scroll listener to RecyclerView
        newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value!! && !viewModel.hasMoreData.value!! &&
                    (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    // Load more data
                    viewModel.loadMoreData()
                }
            }
        })

        categoryItemAdapter.submitList(viewModel.categories)

        // Observe news list data in ViewModel and submit it to the adapter
        viewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            adapter.submitList(newsList)
        }

        return binding.root
    }



    companion object {

    }
}
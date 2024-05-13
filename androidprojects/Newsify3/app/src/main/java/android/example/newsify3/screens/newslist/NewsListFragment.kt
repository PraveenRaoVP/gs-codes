package android.example.newsify3.screens.newslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsify3.adapter.CategoryItemAdapter
import android.example.newsify3.adapter.NewsItemAdapter
import android.example.newsify3.database.NewsDatabase
import android.example.newsify3.databinding.FragmentNewsListBinding
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var binding: FragmentNewsListBinding
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val binding: FragmentNewsListBinding = FragmentNewsListBinding.inflate(inflater, container, false)

            val application = requireNotNull(this.activity).application
            val dataSource = NewsDatabase.getInstance(application).newsDatabaseDao

            val newsViewModelFactory = NewsListViewModelFactory(dataSource, application)

            val newsViewModel = ViewModelProvider(this, newsViewModelFactory)[NewsListViewModel::class.java]

            binding.viewModel = newsViewModel

            newsViewModel.getMyData()
            newsViewModel.dbRetrieve()
            newsViewModel.showWeather()

            newsRecyclerView = binding.newsListRecyclerView
            newsRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
            newsRecyclerView.setHasFixedSize(true)

            categoryRecyclerView = binding.categoryRecyclerView
            categoryRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            categoryRecyclerView.setHasFixedSize(true)


            categoryRecyclerView.adapter = CategoryItemAdapter(newsViewModel.categories,  newsViewModel)
            val navController = findNavController()
            val newsItemAdapter = NewsItemAdapter(navController)
            newsRecyclerView.adapter = newsItemAdapter



            Log.e("listingscreen", newsViewModel.allDataListFromRoom.toString())
            newsViewModel.allDataListFromRoom.observe(viewLifecycleOwner) { dataList ->
                // Update the RecyclerView adapter with the new data list
                newsItemAdapter.addData(dataList)
            }
            newsViewModel._categoryDataListFromRoom.observe(viewLifecycleOwner){ dataList ->
                newsItemAdapter.clearData()
                newsItemAdapter.addData(dataList)
            }

            newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        if(newsViewModel.category == "all"){
                            Log.i("Listingscreen", "all scrolling")
                            newsViewModel.dbRetrieve()
                        }else if(newsViewModel.searchFlag == true){
                            Log.i("Listingscreen", "search scrolling")
                            newsViewModel.dbRetrieveBySearch(newsViewModel.category, "scrollview")
                        }else{
                            Log.i("Listingscreen", "category scrolling")
                            newsViewModel.dbRetrieveCategory(newsViewModel.category, "allData")
                        }
                    }
                }
            })

            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Perform search when user submits query (e.g., press search button)
                    newsViewModel.dbRetrieveBySearch(query, "searchview")
                    newsViewModel.category = query!!
                    newsViewModel.searchFlag = true
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Perform search as user types (optional)
//                performSearch(newText)
                    return true
                }
            })
//        recyclerView.adapter = AdapterClass(newsViewModel.dataListFromRoom)


            return binding.root
        }

    companion object {
    }
}
package android.example.newsapp.screens.newslist

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsapp.R
import android.example.newsapp.adapters.ListItemAdapter
import android.example.newsapp.adapters.NewsListAdapter
import android.example.newsapp.database.NewsDatabase
import android.example.newsapp.databinding.FragmentNewsListBinding
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale


class NewsListFragment : Fragment() {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationName = MutableLiveData<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = NewsDatabase.getInstance(application).newsDao()

        val viewModelFactory = NewsListViewModelFactory(dataSource, application)
        newsListViewModel = ViewModelProvider(this, viewModelFactory)[NewsListViewModel::class.java]
        Log.i("NewsListFragment", "currentCategory is ${newsListViewModel.currentCategory.value}")

        newsListViewModel.populateDataFromDatabase(newsListViewModel.currentCategory.value ?: "all")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val binding: FragmentNewsListBinding = FragmentNewsListBinding.inflate(inflater, container, false)

        newsRecyclerView = binding.newsListRecyclerView
        val newsItemAdapter = NewsListAdapter(newsListViewModel)
        newsRecyclerView.adapter = newsItemAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this.context)


        categoryRecyclerView = binding.categoryRecyclerView
        val categoryItemAdapter = ListItemAdapter(newsListViewModel)
        categoryRecyclerView.adapter = categoryItemAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        categoryItemAdapter.submitList(newsListViewModel.categories)

        binding.lifecycleOwner = this

        if (requireContext().checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocation()
        } else {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    requestLocation()
                } else {
                    Log.i("NewsListFragment", "Location permission denied")
                }
            }.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
        locationName.observe(viewLifecycleOwner, Observer {
            getWeatherData(it)
        })

        newsListViewModel.categoryClicked.observe(viewLifecycleOwner) {
            if(it) {
                Log.i("NewsListFragment", "categoryClicked with category ${newsListViewModel.currentCategory.value}")
                newsListViewModel.prePopulating()
                newsListViewModel.getDataFromDatabase(newsListViewModel.currentCategory.value ?: "all")
                populateData()
                newsRecyclerView.scrollToPosition(0)
                newsListViewModel.onCompleteCategoryClicked()
            }
        }

        newsListViewModel.showErrorToast.observe(viewLifecycleOwner) {
            if(it) {
                Log.i("NewsListFragment", "showErrorToast")
                val toast = Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT)
                toast.show()
                newsListViewModel.onCompletedShowErrorToast()
            }
        }

        newsListViewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.GONE
            }
        }

        newsListViewModel.isLoadingWeather.observe(viewLifecycleOwner) {
            if(it) {
                binding.weatherBar.visibility = View.VISIBLE
            } else {
                binding.weatherBar.visibility = View.GONE
            }
        }

        newsListViewModel.addWeatherData.observe(viewLifecycleOwner) {
            if(it) {
                binding.tempText.text = newsListViewModel.temperature.toString() + "°C"
                newsListViewModel.onCompletedAddWeatherData()
            }
        }

        newsListViewModel.newsItemClicked.observe(viewLifecycleOwner) {
            if(it) {
                Log.i("NewsListFragment", "newsItemClicked with url ${newsListViewModel.newsItemUrl.value}")
                val action = NewsListFragmentDirections.actionNewsListFragmentToNewsWebViewFragment(newsListViewModel.newsItemUrl.value!!)
                findNavController().navigate(action)
                newsListViewModel.onCompletedNavigation()
            }
        }

        newsListViewModel.newsData.observe(viewLifecycleOwner) {
            newsItemAdapter.submitList(it)
        }

        newsListViewModel.showNoNewsToast.observe(viewLifecycleOwner) {
            if(it) {
                Log.i("NewsListFragment", "showNoNewsToast")
                val toast = Toast.makeText(context, "No news available", Toast.LENGTH_SHORT)
                toast.show()
                newsListViewModel.onCompletedShowNoNewsToast()
            }
        }

        newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!newsListViewModel.isLoading.value!! && visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    newsListViewModel.loadMoreData(newsListViewModel.currentCategory.value ?: "all")
                }
            }
        })

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform search when user submits query
                query?.let { newsListViewModel.searchNews(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optionally, perform search as user types (if needed)
                if(newText == "") {
                    newsListViewModel.getDataFromDatabase(newsListViewModel.currentCategory.value ?: "all")
                    populateData()
                }
                return false
            }
        })

        return binding.root
    }

    private fun populateData() {
        val adapter = newsRecyclerView.adapter as NewsListAdapter
        if(newsListViewModel.newsData.value != null)
            adapter.submitList(newsListViewModel.newsData.value)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        // Get the last known location from Fused Location Provider
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Convert coordinates to location name
                    locationName.value = convertCoordinatesToLocation(latitude, longitude).split(",")[2]
                    Log.d("ListViewFragment -> location", "Location Name: ${locationName.value}")
                } ?: run {
                    Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show()
                    locationName.value = "chennai"
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error getting location: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun convertCoordinatesToLocation(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val locationName = addresses?.get(0)?.getAddressLine(0)
        Log.d("ListViewFragment -> location", "Location Name: $locationName")
        return locationName?:"Unknown"
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation()
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getWeatherData(location: String) {
        newsListViewModel._location.value = location
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 123
    }
}
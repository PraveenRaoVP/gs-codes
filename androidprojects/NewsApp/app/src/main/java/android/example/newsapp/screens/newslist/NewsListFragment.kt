package android.example.newsapp.screens.newslist

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsapp.adapters.ListItemAdapter
import android.example.newsapp.adapters.NewsItemClickListener
import android.example.newsapp.adapters.NewsListAdapter
import android.example.newsapp.database.NewsDatabase
import android.example.newsapp.databinding.FragmentNewsListBinding
import android.example.newsapp.screens.enlargeimage.EnlargeImageDialog
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ShareCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import java.util.Locale


class NewsListFragment : Fragment(), NewsItemClickListener {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationName = MutableLiveData<String>()

    val locationRequest = LocationRequest.create().apply {
        interval = 10000 // 10 seconds
        fastestInterval = 5000 // 5 seconds
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            for (location in p0.locations) {
                // Handle location updates here
                val latitude = location.latitude
                val longitude = location.longitude
                Log.i("Location Update", "Latitude: $latitude, Longitude: $longitude")
                locationName.value = convertCoordinatesToLocation(latitude, longitude)
            }
        }
    }

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
        val newsItemAdapter = NewsListAdapter(newsListViewModel, this)
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

        newsListViewModel.weatherData.observe(viewLifecycleOwner) { weatherData ->
            // Update UI with weather data
            weatherData?.let {
                binding.tempText.text = "${it.temperature}°C"
                Picasso.get().load(newsListViewModel.getWeatherImage(newsListViewModel.getWeatherCondition(it))).into(binding.tempImage)
            }
        }

        newsListViewModel.newsItemClicked.observe(viewLifecycleOwner) {
            if(it) {
                Log.i("NewsListFragment", "newsItemClicked with url ${newsListViewModel.newsItemUrl.value}")
                val action = NewsListFragmentDirections.actionNewsListFragmentToNewsWebViewFragment(newsListViewModel.newsItemUrl.value!!, newsListViewModel.newsItemTitle.value!!)
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

        newsListViewModel.shareNews.observe(viewLifecycleOwner) {
            if(it) {
                startActivity(getShareIntent())
                newsListViewModel.onCompletedShareNews()
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

        val searchViewWidth = searchView.layoutParams.width

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // SearchView gained focus, collapse temperature details
                binding.tempText.visibility = View.GONE
                // match parent width
                searchView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            } else {
                // SearchView lost focus, bring back temperature details
                binding.tempText.visibility = View.VISIBLE
                // wrap content width
                searchView.layoutParams.width = searchViewWidth
            }
        }

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

    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(newsListViewModel.shareNewsTitle.value + "\n" + newsListViewModel.shareNewsUrl.value)
            .setType("text/plain")
            .intent
    }

    private fun populateData() {
        val adapter = newsRecyclerView.adapter as NewsListAdapter
        if(newsListViewModel.newsData.value != null)
            adapter.submitList(newsListViewModel.newsData.value)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Convert coordinates to location name
                    locationName.value = convertCoordinatesToLocation(latitude, longitude)
                    Log.d("ListViewFragment -> location", "Location Name: ${locationName.value}")
                } ?: run {
                    // If last known location is not available, request location updates
                    fusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        null /* Looper */
                    )
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
        return locationName ?: "Unknown"
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

    override fun onImageClicked(imageUrl: String) {
        val dialog = EnlargeImageDialog(requireContext(), imageUrl)
        dialog.show()
    }
}
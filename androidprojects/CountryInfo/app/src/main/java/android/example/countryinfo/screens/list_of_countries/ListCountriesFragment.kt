package android.example.countryinfo.screens.list_of_countries

import android.example.countryinfo.database.CountryDatabase
import android.example.countryinfo.databinding.FragmentListCountriesBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [ListCountriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListCountriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var listCountriesViewModel: ListCountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListCountriesBinding = FragmentListCountriesBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDao()

        val viewModelFactory = ListCountriesViewModelFactory(dataSource, application)
        listCountriesViewModel = ViewModelProvider(this, viewModelFactory)[ListCountriesViewModel::class.java]

        recyclerView = binding.recyclerView

        binding.lifecycleOwner = this
        binding.listCountriesViewModel = listCountriesViewModel
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)

        getCountries()

        listCountriesViewModel.navigateToNewCountry.observe(viewLifecycleOwner) {
            if(it == true) {
                this.findNavController().navigate(ListCountriesFragmentDirections.actionListCountriesFragmentToNewCountry())
                listCountriesViewModel.doneNavigatingToNewCountry()
            }
        }

        listCountriesViewModel.navigateToCountryDetail.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                listCountriesViewModel.countryId.value?.let { id ->
                    this.findNavController().navigate(
                        ListCountriesFragmentDirections.actionListCountriesFragmentToCountryFragment(id)
                    )
                    listCountriesViewModel.doneNavigatingToCountryDetail()
                }
            }
        }

        return binding.root
    }

    private fun getCountries() {
        val adapter = CountryItemAdapter(listCountriesViewModel)
        recyclerView.adapter = adapter
        listCountriesViewModel.countries.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

}
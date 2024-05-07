package android.example.countryinfo.screens.add_new_country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.countryinfo.R
import android.example.countryinfo.database.CountryDatabase
import android.example.countryinfo.databinding.FragmentCountryBinding
import android.example.countryinfo.databinding.FragmentNewCountryBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


class NewCountry : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewCountryBinding = FragmentNewCountryBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDao()

        val viewModelFactory = NewCountryViewModelFactory(dataSource, application)

        val newCountryViewModel = ViewModelProvider(this, viewModelFactory)[NewCountryViewModel::class.java]

        binding.lifecycleOwner = this
        binding.newCountryViewModel = newCountryViewModel

        newCountryViewModel.onSearchClickBtn.observe(viewLifecycleOwner) {
            if(it == true) {
                newCountryViewModel.searchCountry(binding.searchEditText.text.toString())
                newCountryViewModel.onDoneSearching()
            }
        }

        newCountryViewModel.navigateToCountry.observe(viewLifecycleOwner) {
            if(it == true) {
                this.findNavController().navigate(NewCountryDirections.actionNewCountryToListCountriesFragment())
                newCountryViewModel.finishNavigatingToCountry()
            }
        }

        newCountryViewModel.showSnackBarEvent.observe(viewLifecycleOwner) {
            if(it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.error_loading_data),
                    Snackbar.LENGTH_SHORT
                ).show()
                newCountryViewModel.onShowSnackBarEventFinish()
            }
        }

        return binding.root
    }
}
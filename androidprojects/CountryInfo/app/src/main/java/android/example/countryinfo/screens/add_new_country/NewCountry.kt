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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewCountry.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewCountry : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewCountry.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewCountry().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
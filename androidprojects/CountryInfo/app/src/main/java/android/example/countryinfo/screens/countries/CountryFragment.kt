package android.example.countryinfo.screens.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.countryinfo.R
import android.example.countryinfo.database.CountryDatabase
import android.example.countryinfo.databinding.FragmentCountryBinding
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CountryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCountryBinding = FragmentCountryBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDao()

        val viewModelFactory = CountryViewModelFactory(dataSource, application)
        val countryViewModel = ViewModelProvider(this, viewModelFactory)[CountryViewModel::class.java]

        val arguments = CountryFragmentArgs.fromBundle(requireArguments())

        countryViewModel.countryId = arguments.id

        binding.lifecycleOwner = this
        binding.countryViewModel = countryViewModel

        countryViewModel.countryDetail.observe(viewLifecycleOwner) { countryDetail ->
            Picasso.get().load(countryDetail.flagImageUrl).into(binding.flag, object : Callback {
                override fun onSuccess() {
//                    Log.i("CountryFragment", "Image loaded successfully")
                }

                override fun onError(e: Exception?) {
//                    Log.e("CountryFragment", "Error loading image: $e")
                }
            })
        }

        countryViewModel.backButtonPressed.observe(viewLifecycleOwner) {
            if(it == true) {
                this.findNavController().navigate(CountryFragmentDirections.actionCountryFragmentToListCountriesFragment())
                countryViewModel.doneNavigatingBack()
            }
        }

        return binding.root
    }
}
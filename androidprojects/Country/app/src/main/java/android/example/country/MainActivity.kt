package android.example.country

import android.example.country.databinding.ActivityMainBinding
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val textView = binding.textView
        val editText = binding.countryName

        val application = requireNotNull(this).application
        val viewModelFactory = MainViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.onSubmitBtnClick.observe(this) {
            if(it) {
                viewModel.getCountryDetails(editText.text.toString())
                viewModel.doneFetching()
            }
        }

        viewModel.countryDetails.observe(this) {
            textView.text = it
        }

        textView.movementMethod = ScrollingMovementMethod()

//        setContentView(R.layout.activity_main)

    }
}
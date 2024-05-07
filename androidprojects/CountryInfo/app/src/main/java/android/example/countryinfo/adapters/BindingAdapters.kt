package android.example.countryinfo.adapters

import android.example.countryinfo.R
import android.example.countryinfo.screens.add_new_country.CountryAPIStatus
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("countryAPIStatus")
    @JvmStatic
    fun bindStatus(statusImageView: ImageView, status: CountryAPIStatus?) {
        when (status) {
            CountryAPIStatus.LOADING -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.loading_animation)
            }

            CountryAPIStatus.ERROR -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.ic_connection_error)
            }

            CountryAPIStatus.DONE -> {
                statusImageView.visibility = View.GONE
            }

            CountryAPIStatus.IDLE -> {
                statusImageView.visibility = View.GONE
            }

            null -> {
                statusImageView.visibility = View.GONE
            }
        }
    }
}
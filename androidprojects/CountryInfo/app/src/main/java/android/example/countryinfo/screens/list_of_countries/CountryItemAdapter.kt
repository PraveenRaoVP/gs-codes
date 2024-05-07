package android.example.countryinfo.screens.list_of_countries

import android.example.countryinfo.R
import android.example.countryinfo.databinding.ItemCountryBinding
import android.example.countryinfo.models.CountryDetails
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CountryItemAdapter(private val listCountriesViewModel: ListCountriesViewModel) : ListAdapter<CountryDetails, CountryItemAdapter.CountryViewHolder>(CountryDiffCallBack()) {
    class CountryViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        val countryCard: CardView = binding.countryCard
        val deleteBtn: ImageButton = binding.deleteBtn
        val countryFlag: ImageView = binding.countryFlag
        fun bind(countryDetails: CountryDetails) {
            binding.country = countryDetails
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): CountryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                layoutInflater.inflate(R.layout.item_country, parent, false)
                val binding = ItemCountryBinding.inflate(layoutInflater, parent, false)
                return CountryViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryDetails = getItem(position)
        holder.bind(countryDetails)

        Picasso.get().load(countryDetails.flagImageUrl).into(holder.countryFlag, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Log.e("CountryItemAdapter", "Error loading image: $e")
            }
        })

        holder.countryCard.setOnClickListener {
            listCountriesViewModel.onCountryItemClicked(countryDetails.id)
        }
        holder.deleteBtn.setOnClickListener {
            listCountriesViewModel.onDeleteBtnClicked(countryDetails.id)
        }
    }
}



class CountryDiffCallBack : DiffUtil.ItemCallback<CountryDetails>() {
    override fun areItemsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
        return oldItem == newItem
    }
}
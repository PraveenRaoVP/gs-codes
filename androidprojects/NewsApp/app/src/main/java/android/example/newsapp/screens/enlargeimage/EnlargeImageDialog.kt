package android.example.newsapp.screens.enlargeimage

import android.app.Dialog
import android.content.Context
import android.example.newsapp.databinding.DialogEnlargeImageBinding
import android.view.LayoutInflater
import android.view.Window
import com.squareup.picasso.Picasso

class EnlargeImageDialog(context: Context, private val imageUrl: String) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DialogEnlargeImageBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        // Load image into ImageView using Picasso
        Picasso.get().load(imageUrl).into(binding.imageView)

        // Close button click listener
        binding.closeButton.setOnClickListener {
            dismiss() // Dismiss the dialog
        }
    }
}
package android.example.newsapp.screens.newsdetails

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsapp.R
import android.example.newsapp.databinding.FragmentNewsDetailsBinding
import android.example.newsapp.screens.enlargeimage.EnlargeImageDialog
import android.example.newsapp.utils.ImageClickListener
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso


class NewsDetailsFragment : Fragment(), ImageClickListener {

    private lateinit var arguments: NewsDetailsFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentNewsDetailsBinding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        arguments = NewsDetailsFragmentArgs.fromBundle(requireArguments()!!)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NewsDetailsViewModelFactory(application)

        val newsDetailsViewModel = ViewModelProvider(this, viewModelFactory)[NewsDetailsViewModel::class.java]

        binding.newsDetailsViewModel = newsDetailsViewModel

        Picasso.get().load(arguments.imageUrl).into(binding.newsImage)
        binding.titleText.text = arguments.newsTitle
        binding.contentText.text = arguments.newsContent
        binding.lifecycleOwner = this

        binding.contentText.movementMethod = ScrollingMovementMethod()

        newsDetailsViewModel.isImageClicked.observe(viewLifecycleOwner) {
            if(it) {
                onImageClicked(arguments.imageUrl)
                newsDetailsViewModel.onImageClickComplete()
            }
        }

        newsDetailsViewModel.isShareClicked.observe(viewLifecycleOwner) {
            if(it) {
                startActivity(getShareIntent())
                newsDetailsViewModel.onShareComplete()
            }
        }

        newsDetailsViewModel.isLinkClicked.observe(viewLifecycleOwner) {
            if(it) {
                val action = NewsDetailsFragmentDirections.actionNewsDetailsFragmentToNewsWebViewFragment(arguments.readMoreUrl, arguments.newsTitle)
                findNavController().navigate(action)
                newsDetailsViewModel.onLinkComplete()
            }
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = arguments.newsTitle

        return binding.root
    }

    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText("Check out this article: ${arguments.newsTitle} \n ${arguments.readMoreUrl}")
            .setType("text/plain")
            .intent
    }

    companion object {
    }

    override fun onImageClicked(imageUrl: String) {
        val dialog = EnlargeImageDialog(requireContext(), imageUrl)
        dialog.show()
    }
}
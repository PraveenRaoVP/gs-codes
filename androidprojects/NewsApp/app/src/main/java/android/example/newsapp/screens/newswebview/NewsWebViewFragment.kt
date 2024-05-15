package android.example.newsapp.screens.newswebview

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsapp.R
import android.example.newsapp.databinding.FragmentNewsWebViewBinding
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

class NewsWebViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsWebViewBinding =
            FragmentNewsWebViewBinding.inflate(inflater, container, false)

        val args = NewsWebViewFragmentArgs.fromBundle(requireArguments()!!)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = MyWebViewClient()
        binding.webView.loadUrl(args.webPageLink)
        (activity as AppCompatActivity).supportActionBar?.title = args.webPageTitle

        return binding.root
    }

//    class MyWebViewClient() : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            view?.loadUrl(url!!)
//            return true
//        }
//    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()

            if (isExternalAppUrl(url)) {
                if (openExternalApp(url)) {
                    return true
                }
            }

            view?.loadUrl(url)
            return true
        }

        private fun isExternalAppUrl(url: String): Boolean {
            return url.startsWith("https://twitter.com") ||
                    url.startsWith("https://www.youtube.com") ||
                    url.startsWith("https://www.amazon.in")
        }

        private fun openExternalApp(url: String): Boolean {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            when {
                url.startsWith("https://twitter.com") -> {
                    intent.setPackage("com.twitter.android")
                }

                url.startsWith("https://www.youtube.com") -> {
                    intent.setPackage("com.google.android.youtube")
                }

                url.startsWith("https://www.amazon.in") -> {
                    intent.setPackage("in.amazon.mShop.android.shopping")
                }

                else -> return false
            }
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
                return true
            }
            return false
        }
    }

    companion object {
    }
}
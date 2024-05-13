package android.example.newsify3.screens.newswebview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsify3.R
import android.example.newsify3.databinding.FragmentNewsWebViewBinding
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsWebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsWebViewFragment : Fragment() {
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
        val binding: FragmentNewsWebViewBinding = FragmentNewsWebViewBinding.inflate(inflater, container, false)

        val arguments = NewsWebViewFragmentArgs.fromBundle(requireArguments()!!)

        val url = arguments.webPageLink

        binding.newsWebView.settings.javaScriptEnabled = true
        binding.newsWebView.webViewClient = MyWebViewClient()
        binding.newsWebView.loadUrl(url)

        return binding.root
    }


    private class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return true
        }
    }
}
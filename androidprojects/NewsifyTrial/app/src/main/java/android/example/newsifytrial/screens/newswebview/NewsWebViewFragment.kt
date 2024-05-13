package android.example.newsifytrial.screens.newswebview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsifytrial.R
import android.example.newsifytrial.databinding.FragmentNewsWebViewBinding
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

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

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsWebViewBinding = FragmentNewsWebViewBinding.inflate(inflater, container, false)

        val arguments = NewsWebViewFragmentArgs.fromBundle(requireArguments())

        webView = binding.newsWebView
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl(arguments.webPageLink)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        return binding.root
    }

    companion object {

    }
}
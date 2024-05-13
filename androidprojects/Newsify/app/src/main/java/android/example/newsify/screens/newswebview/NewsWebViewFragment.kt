package android.example.newsify.screens.newswebview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.newsify.R
import android.example.newsify.databinding.FragmentNewsWebViewBinding
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient


class NewsWebViewFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

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
package com.example.embededlinkwrtcplaytestapp.views

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.embededlinkwrtcplaytestapp.R
import com.example.embededlinkwrtcplaytestapp.databinding.ActivityMainBinding
import com.example.embededlinkwrtcplaytestapp.dataclasses.StreamingURls
import com.example.embededlinkwrtcplaytestapp.models.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var embedString: String
    private lateinit var embededUrlObj:StreamingURls
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        embededUrlObj = StreamingURls(getString(R.string.embeded_url))
        binding.streamingUrlName = embededUrlObj

        embedString = "<iframe src=" + embededUrlObj._streamingURl + "frameborder=\"0\" allowfullscreen></iframe>"
        mainViewModel.setLiveData(embedString)


        mainViewModel.streaminUrlWitHtmlLiveData.observe(this, Observer {
            webViewClientImplementation(it)

        })



    }



    private fun webViewClientImplementation(htmlData:String) {

        binding.mWebView.isScrollContainer = false
        binding.mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        val webSettings = binding.mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.builtInZoomControls = false

        binding.mWebView.loadData(htmlData, getString(R.string.mime_type), getString(R.string.encoding_str));


    }

    }
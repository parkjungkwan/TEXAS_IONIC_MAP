package com.example.application

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.view.View
import android.webkit.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(false)
        webView.webChromeClient = object : WebChromeClient(){
            override fun onShowFileChooser(
                webview : WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: WebChromeClient.FileChooserParams
            ): Boolean{
                return true
            }
        }
        webView.addJavascriptInterface(WebBridge(),"java")
        webView.loadUrl("file:///android_asset/index.html")
        webView.setWebViewClient(object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pg.visibility = View.VISIBLE

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pg.visibility = View.INVISIBLE
            }
        })
    }
    class WebBridge{
        @JavascriptInterface
        fun test(){

        }
    }
}

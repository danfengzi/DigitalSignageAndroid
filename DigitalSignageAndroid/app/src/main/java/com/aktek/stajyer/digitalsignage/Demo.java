package com.aktek.stajyer.digitalsignage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Demo extends AppCompatActivity {
    WebView webViewText;
    WebView webViewClock;
    WebView webViewTextTwo;
    WebView webViewDataSet;
    WebView webViewEmbedded;
    String fileLib = "file:///android_asset/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        webViewText = (WebView) findViewById(R.id.webViewText);
        webViewTextTwo = (WebView) findViewById(R.id.webViewTextTwo);
        webViewClock = (WebView) findViewById(R.id.webViewClock);
        webViewDataSet = (WebView) findViewById(R.id.webViewDataSet);
        webViewEmbedded = (WebView) findViewById(R.id.webViewEmbedded);

        setSettings(webViewText);
        setSettings(webViewTextTwo);
        setSettings(webViewClock);
        setSettings(webViewDataSet);
        setSettings(webViewEmbedded);


        webViewText.loadUrl(getFileLib() + "7ca4e03c0b9fe880fa1829f458c44880.htm");
        webViewTextTwo.loadUrl(getFileLib() + "793d604f009f8c3e5f01e72d137222b3.htm");
        webViewClock.loadUrl(getFileLib() + "34615ef8d08a82d3284ecb09bd0063c6.htm");
        webViewDataSet.loadUrl(getFileLib()+"c9b4d5245f3fb8d2bb7f6819ca8b6414.htm");
        webViewEmbedded.loadUrl(getFileLib()+"bf8507c61b5825536932f7fc7c31638c.htm");



    }

    public String getFileLib() {
        return fileLib;
    }

    public void setSettings(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
    }
}

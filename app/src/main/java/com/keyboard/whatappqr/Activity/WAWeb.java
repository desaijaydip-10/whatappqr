package com.keyboard.whatappqr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.keyboard.whatappqr.R;

import java.io.InputStream;

public class WAWeb extends AppCompatActivity {

    MainActivity main = new MainActivity();
    protected WebSettings webSettings;
    protected WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waweb);
        WebView webView2 = (WebView) findViewById(R.id.webview);
        this.webView = webView2;
        webView2.loadUrl(getString(R.string.whatsapp_web_url));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setAllowContentAccess(true);
        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setUserAgentString(getString(R.string.user_agent_string));
        this.webView.setWebViewClient(new NavWebViewClient());
    }


    class NavWebViewClient extends WebViewClient {
        private NavWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return handleUri(Uri.parse(str));
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return handleUri(webResourceRequest.getUrl());
        }

        private boolean handleUri(Uri uri) {
            if (uri.getHost().contains(WAWeb.this.getString(R.string.whatsapp_host))) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (intent.resolveActivity(WAWeb.this.getPackageManager()) == null) {
                return true;
            }
            WAWeb.this.startActivity(intent);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            WAWeb.this.injectCSS();
            super.onPageFinished(webView, str);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void injectCSS() {
        try {
            InputStream open = getAssets().open("style.css");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            String encodeToString = Base64.encodeToString(bArr, 2);
            WebView webView2 = this.webView;
            webView2.loadUrl("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var style = document.createElement('style');style.type = 'text/css';style.innerHTML = window.atob('" + encodeToString + "');parent.appendChild(style)})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.keyboard.qrwhatapps.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.keyboard.qrwhatapps.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WAWeb extends AppCompatActivity {

    MainActivity main = new MainActivity();
     WebSettings webSettings;
    protected WebView webView;
    Bitmap myBitmap;
    LinearLayout linearLayout;

    ImageView refreshimg, cropimgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_waweb);

        WebView webView2 =  findViewById(R.id.webview);

        refreshimg= findViewById(R.id.refreshimg);
        cropimgview= findViewById(R.id.cropimg);

        linearLayout= findViewById(R.id.linerlayout);

        webView = webView2;
        webView2.loadUrl(getString(R.string.whatsapp_web_url));

        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setAllowContentAccess(true);
        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setUserAgentString(getString(R.string.user_agent_string));
        this.webView.setWebViewClient(new NavWebViewClient());



        refreshimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webView.reload();

            }
        });


        ViewTreeObserver vto = linearLayout.getViewTreeObserver();

        cropimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
//               setclickpicture();
            }
        });

    }

    private void saveBitmap(Bitmap bitmap) {



        File imagePath = new File(Environment.getExternalStorageDirectory() + "/abcde");
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            Log.e("GREC", e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private Bitmap takeScreenshot() {

        View rootView =linearLayout.getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();

    }

    private void setclickpicture() {


        ViewTreeObserver vto = linearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                getScreen();
            }
        });
    }

    private void getScreen() {





//
//        View v = view.getRootView();
//        v.setDrawingCacheEnabled(true);
//        Bitmap b = v.getDrawingCache();
//        String extr = Environment.getExternalStorageDirectory().toString();
//        File myPath = new File(extr, getString(R.string.free_tiket)+".jpg");
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(myPath);
//            b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//            MediaStore.Images.Media.insertImage( getContentResolver(), b,
//                    "Screen", "screen");
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    private void saveImage(Bitmap bitmap) throws IOException {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "test.png");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
    }

    private Bitmap captureScreen(View v) {
        Bitmap screenshot = null;
        try {

            if(v!=null) {

                screenshot = Bitmap.createBitmap(v.getMeasuredWidth(),v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(screenshot);
                v.draw(canvas);
            }

        }catch (Exception e){
            Log.d("ScreenShotActivity", "Failed to capture screenshot because:" + e.getMessage());
        }

        return  screenshot;
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
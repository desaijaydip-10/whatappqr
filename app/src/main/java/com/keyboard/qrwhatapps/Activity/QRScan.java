package com.keyboard.qrwhatapps.Activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.keyboard.qrwhatapps.R;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QRScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {


     Handler handler = new Handler();
    /* access modifiers changed from: private */
    public ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
//        this.adRequest = new AdRequest.Builder().build();
//        AdView adView2 = (AdView) findViewById(C0781R.C0783id.adView);
//        this.adView = adView2;
//        adView2.loadAd(this.adRequest);
        ZXingScannerView zXingScannerView = new ZXingScannerView(this);
        this.mScannerView = zXingScannerView;
        ((ViewGroup) findViewById(R.id.content)).addView(zXingScannerView);
    }

    public void onResume() {
        super.onResume();
//        AdView adView2 = this.adView;
//        if (adView2 != null) {
//            adView2.resume();
//        }
        this.mScannerView.setResultHandler(this);
        this.mScannerView.startCamera();
    }

    public void onDestroy() {
        super.onDestroy();
//        AdView adView2 = this.adView;
//        if (adView2 != null) {
//            adView2.destroy();
//        }
    }

    public void onPause() {
        super.onPause();
//        AdView adView2 = this.adView;
//        if (adView2 != null) {
//            adView2.pause();
//        }
        this.mScannerView.stopCamera();
    }

    public void handleResult(final Result result) {

    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(this, "Permission denied to camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
//                    AdLoaderQR.showAd();
                }
            }, 5000);
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
//                AdLoaderQR.showAd();
            }
        }, 5000);
        super.onBackPressed();
    }


    @Override
    public void handleResult(com.google.zxing.Result result) {

        result.getBarcodeFormat();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_layout);

//        AdView adView2 = (AdView) dialog.findViewById(C0781R.C0783id.adView);
//        this.adView = adView2;
//        adView2.loadAd(this.adRequest);
        View decorView = dialog.getWindow().getDecorView();
        dialog.getWindow().setLayout(-1, -2);
//        decorView.setBackgroundResource(17170443);


        ((TextView) dialog.findViewById(R.id.someText)).setText(result.getText());
        ((ImageView) dialog.findViewById(R.id.imgOfDialog)).setImageResource(R.drawable.ic_done_gr);
        ((ImageView) dialog.findViewById(R.id.searchButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                if (Patterns.WEB_URL.matcher(result.getText()).matches()) {
                    str = result.getText();
                } else {
                    str = "http://www.google.com/#q=" + result.getText();
                }
                QRScan.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                dialog.dismiss();
                QRScan.this.mScannerView.resumeCameraPreview(QRScan.this);
            }
        });
        ((ImageView) dialog.findViewById(R.id.copyButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) QRScan.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("", result.getText()));
                Toast.makeText(QRScan.this.getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                QRScan.this.mScannerView.resumeCameraPreview(QRScan.this);
            }
        });
        ((ImageView) dialog.findViewById(R.id.shareButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", result.getText());
                QRScan.this.startActivity(Intent.createChooser(intent, "Share "));
                dialog.dismiss();
                QRScan.this.mScannerView.resumeCameraPreview(QRScan.this);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                QRScan.this.mScannerView.resumeCameraPreview(QRScan.this);
            }
        });
        dialog.show();

    }

}
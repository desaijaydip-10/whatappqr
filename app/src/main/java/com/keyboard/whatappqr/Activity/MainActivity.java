package com.keyboard.whatappqr.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.keyboard.whatappqr.Drawer;
import com.keyboard.whatappqr.R;

public class MainActivity extends AppCompatActivity {

    private static int count;
    LinearLayout DChat;

    /* renamed from: QR */
    LinearLayout f121QR;
    LinearLayout StatusSaver;
    //    AdView adView;
//    AdView adView1;
    ImageView rateus;
    ImageView sharebtn;
    LinearLayout whatsweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f121QR =  findViewById(R.id.qrscan);
        whatsweb =  findViewById(R.id.whatsweb);
        DChat =  findViewById(R.id.dchat);
        StatusSaver =  findViewById(R.id.status_saver);
        sharebtn = (ImageView) findViewById(R.id.shareus);
        rateus = (ImageView) findViewById(R.id.rateus);

        this.f121QR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, QRScan.class));
            }
        });
        this.whatsweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, WAWeb.class));
            }
        });
        this.DChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, DirectChat.class));
            }
        });
        this.StatusSaver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, Drawer.class));
            }
        });
        this.sharebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.shareApp();
            }
        });
        this.rateus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.rateUsOnPlayStore();
            }
        });
    }

    public void shareApp() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "Whats Web Scan and Status Saver");
        intent.putExtra("android.intent.extra.TEXT", "Clone multiple accounts on single device and save whatsapp statuses on your device  https://play.google.com/store/apps/details?id=com.hbus.WhatsWebScanforWhatsappWhatscanQRCode2020");
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    public void rateUsOnPlayStore() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
//        intent.addFlags(1208483840);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    private void showexitdialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.exit_dialog);
//        AdRequest build = new AdRequest.Builder().build();
//        AdView adView2 = (AdView) dialog.findViewById(C0781R.C0783id.adView);
//        this.adView = adView2;
//        adView2.loadAd(build);
        View decorView = dialog.getWindow().getDecorView();
        dialog.getWindow().setLayout(-2, -2);

//         decorView.setBackgroundResource(17170443);
        dialog.show();



        ((Button) dialog.findViewById(R.id.exit_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                AdLoaderSplash.destroyAd();
//                AdLoaderDirectChat.destroyAd();
//                AdLoaderQR.destroyAd();
//                AdLoaderStatus.destroyAd();
                MainActivity.this.finish();
            }
        });
        ((Button) dialog.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void onBackPressed() {
        showexitdialog();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
//        AdView adView2 = this.adView1;
//        if (adView2 != null) {
//            adView2.pause();
//        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
//        AdView adView2 = this.adView1;
//        if (adView2 != null) {
//            adView2.destroy();
//        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
//        AdView adView2 = this.adView1;
//        if (adView2 != null) {
//            adView2.resume();
//        }
    }



}
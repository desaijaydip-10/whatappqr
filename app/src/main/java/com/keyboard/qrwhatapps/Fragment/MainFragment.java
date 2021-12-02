package com.keyboard.qrwhatapps.Fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.keyboard.qrwhatapps.Activity.DirectChat;
import com.keyboard.qrwhatapps.Activity.MainActivity;
import com.keyboard.qrwhatapps.Activity.QRScan;
import com.keyboard.qrwhatapps.Activity.WAWeb;
import com.keyboard.qrwhatapps.Drawer;
import com.keyboard.qrwhatapps.R;


public class MainFragment extends Fragment {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);


        f121QR =  view.findViewById(R.id.qrscan);
        whatsweb =   view.findViewById(R.id.whatsweb);
        DChat =   view.findViewById(R.id.dchat);
        StatusSaver =  view. findViewById(R.id.status_saver);
        sharebtn = (ImageView) view. findViewById(R.id.shareus);
        rateus = (ImageView) view. findViewById(R.id.rateus);



        f121QR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getContext(), QRScan.class));
            }
        });
        this.whatsweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                startActivity(new Intent(getContext(),   WAWeb.class));
            }
        });
        this.DChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               startActivity(new Intent(getContext(), DirectChat.class));
            }
        });
        this.StatusSaver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Drawer.class));
            }
        });
        this.sharebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                shareApp();
            }
        });
        this.rateus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              rateUsOnPlayStore();
            }
        });
        return  view;
    }




    private void rateUsOnPlayStore() {

        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" +getContext(). getPackageName()));
//        intent.addFlags(1208483840);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" +getContext(). getPackageName())));
        }
        
    }




    private void shareApp() {

        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "Whats Web Scan and Status Saver");
        intent.putExtra("android.intent.extra.TEXT", "Clone multiple accounts on single device and save whatsapp statuses on your device  https://play.google.com/store/apps/details?id=com.hbus.WhatsWebScanforWhatsappWhatscanQRCode2020");
        startActivity(Intent.createChooser(intent, "Share via"));
    }






    
}
package com.keyboard.qrwhatapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.keyboard.qrwhatapps.Fragment.MainFragment;
import com.keyboard.qrwhatapps.R;

public class custom_drawer extends AppCompatActivity {
    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        DrawerLayout drawerLayout =  findViewById(R.id.drawerlayout);
        navigationView =  findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
         getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();


         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                 switch (item.getItemId()){

                     case R.id.nav_privacy:
                         Toast.makeText(custom_drawer.this, "nav pricxy", Toast.LENGTH_SHORT).show();
                         drawerLayout.closeDrawers();
                         break;
                     case R.id.nav_security:

                         break;

                 }

                 return true;
             }
         });

    }

    @Override
    public void onBackPressed() {


        showexitdialog();

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
                finishAffinity();
            }
        });
        ((Button) dialog.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
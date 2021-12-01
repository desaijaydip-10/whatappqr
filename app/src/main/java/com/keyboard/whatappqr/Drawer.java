package com.keyboard.whatappqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.keyboard.whatappqr.Fragment.WhatsAppPicture;
import com.keyboard.whatappqr.Fragment.WhatsAppSaveStatuses;
import com.keyboard.whatappqr.Fragment.WhatsAppVideos;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    private final String TAG = "DrawerTAG";
    boolean doubleBackToExitPressedOnce = false;
    NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((CharSequence) "WhatsApp Statuses");
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view);
        this.navigationView = navigationView2;
        navigationView2.setNavigationItemSelectedListener(this);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.viewPager.setOffscreenPageLimit(0);
        setupViewPager(this.viewPager);
        this.tabLayout.setupWithViewPager(this.viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        viewPagerAdapter.addFragment(new WhatsAppPicture(), "Picture");
        viewPagerAdapter.addFragment(new WhatsAppVideos(), "Videos");
        viewPagerAdapter.addFragment(new WhatsAppSaveStatuses(), "Saved");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            drawerLayout.closeDrawer((int) GravityCompat.START);
        }
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            super.onBackPressed();
        } else if (this.doubleBackToExitPressedOnce) {
//            AdLoaderStatus.showAd();
            finish();
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Drawer.this.doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        int itemId = menuItem.getItemId();
        if (itemId != R.id.nav_simple && itemId == R.id.nav_business) {
            startActivity(new Intent(this, BusinessWhatsApp.class));
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private Context mContext;
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.mContext = context;
        }

        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }

}
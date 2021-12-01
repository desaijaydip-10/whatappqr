package com.keyboard.whatappqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.keyboard.whatappqr.Fragment.BusinessWhatsAppPicture;
import com.keyboard.whatappqr.Fragment.BusinessWhatsAppSaveStatuses;
import com.keyboard.whatappqr.Fragment.BusinessWhatsAppVideos;

import java.util.ArrayList;
import java.util.List;

public class BusinessWhatsApp extends AppCompatActivity {


    final String TAG = Drawer.class.getSimpleName();
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_whats_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BusinessWhatsApp.super.onBackPressed();
            }
        });
        toolbar.setTitle((CharSequence) "WhatsApp Business Statuses");
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewPager);
        this.viewPager = viewPager2;
        viewPager2.setOffscreenPageLimit(0);
        setupViewPager(this.viewPager);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout = tabLayout2;
        tabLayout2.setupWithViewPager(this.viewPager);
    }

    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        viewPagerAdapter.addFragment(new BusinessWhatsAppPicture(), "Picture");
        viewPagerAdapter.addFragment(new BusinessWhatsAppVideos(), "Videos");
        viewPagerAdapter.addFragment(new BusinessWhatsAppSaveStatuses(), "Saved");
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setCurrentItem(0);
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
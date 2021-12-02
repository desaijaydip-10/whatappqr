package com.keyboard.qrwhatapps.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.keyboard.qrwhatapps.Activity.WhatsAppVideosAdaptor;
import com.keyboard.qrwhatapps.Models.ModelStatus;
import com.keyboard.qrwhatapps.R;
import com.keyboard.qrwhatapps.Utils.Config;

import java.io.File;
import java.util.ArrayList;


public class BusinessWhatsAppVideos extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    ArrayList<ModelStatus> data;
    SwipeRefreshLayout mSwipeRefreshLayout;

    /* renamed from: rv */
    RecyclerView f136rv;
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_business_whats_app_videos, container, false);
        this.f136rv = (RecyclerView) view.findViewById(R.id.rv_status);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.contentView);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.textView = (TextView) view.findViewById(R.id.textView);
        this.f136rv.setHasFixedSize(true);
        loadData();
        return view;
    }

    private void loadData() {

        this.data = new ArrayList<>();
        File file = new File(Config.WhatsAppBusinessDirectoryPath);
        if (file.exists()) {
            final File[] listFiles = file.listFiles();
            Log.d("Files", "Size: " + listFiles.length);
            final String[] strArr = {""};
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    for (int i = 0; i < listFiles.length; i++) {
                        Log.d("Files", "FileName:" + listFiles[i].getName());
                        Log.d("Files", "FileName:" + listFiles[i].getName().substring(0, listFiles[i].getName().length() + -4));
                        if (listFiles[i].getName().endsWith(".mp4")) {
                            String[] strArr = new String[]{};
                            strArr[0] = Config.WhatsAppBusinessDirectoryPath + listFiles[i].getName();
                            BusinessWhatsAppVideos.this.data.add(new ModelStatus(strArr[0], listFiles[i].getName().substring(0, listFiles[i].getName().length() + -4), 1));
                        }
                    }
                    return null;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Void voidR) {
                    super.onPostExecute(voidR);
                    if (BusinessWhatsAppVideos.this.data.toArray().length <= 0) {
                        BusinessWhatsAppVideos.this.textView.setVisibility(View.VISIBLE);
                        BusinessWhatsAppVideos.this.textView.setText("No Status Available \n Check Out some Status & come back again...");
                    }
//                    BusinessWhatsAppVideos.this.f136rv.setAdapter(new WhatsAppVideosAdaptor(BusinessWhatsAppVideos.this.getActivity(), BusinessWhatsAppVideos.this.data));
//                    BusinessWhatsAppVideos.this.f136rv.setLayoutManager(new GridLayoutManager(BusinessWhatsAppVideos.this.getActivity(), 2));
                }
            }.execute(new Void[0]);
        } else {
            this.textView.setVisibility(View.VISIBLE);
            this.textView.setText("No Status Available \n Check Out some Status & come back again...");
//            Snackbar.make(getActivity().findViewById(16908290), (CharSequence) "WhatsApp Business Not Installed", -1).show();
        }
        refreshItems();

    }

    private void refreshItems() {
        onItemsLoadComplete();

    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void onItemsLoadComplete() {
        this.mSwipeRefreshLayout.setRefreshing(false);
    }
}
package com.keyboard.whatappqr.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyboard.whatappqr.Adapters.WhatsAppSavedStatusesAdaptor;
import com.keyboard.whatappqr.Models.ModelStatus;
import com.keyboard.whatappqr.R;
import com.keyboard.whatappqr.Utils.Config;

import java.io.File;
import java.util.ArrayList;

public class BusinessWhatsAppSaveStatuses extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    ArrayList<ModelStatus> data;
    SwipeRefreshLayout mSwipeRefreshLayout;

    /* renamed from: rv */
    RecyclerView f135rv;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_business_whats_app_save_statuses, container, false);
        this.f135rv = (RecyclerView) view.findViewById(R.id.rv_status);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.contentView);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.textView = (TextView) view.findViewById(R.id.textView);
        this.f135rv.setHasFixedSize(true);
        loadData();

        return  view;
    }

    private void loadData() {

        this.data = new ArrayList<>();
        File file = new File(Config.WhatsAppBusinessSaveStatus);
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
                        if (listFiles[i].getName().endsWith(".jpg") || listFiles[i].getName().endsWith("gif") || listFiles[i].getName().endsWith(".mp4")) {
                            String[] strArr = new String[]{};
                            strArr[0] = Config.WhatsAppBusinessSaveStatus + listFiles[i].getName();
                            BusinessWhatsAppSaveStatuses.this.data.add(new ModelStatus(strArr[0], listFiles[i].getName().substring(0, listFiles[i].getName().length() + -4), 1));
                        }
                    }
                    return null;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Void voidR) {
                    super.onPostExecute(voidR);
                    if (BusinessWhatsAppSaveStatuses.this.data.toArray().length <= 0) {
                        BusinessWhatsAppSaveStatuses.this.textView.setVisibility(View.VISIBLE);
                        BusinessWhatsAppSaveStatuses.this.textView.setText("No Status Available \n Check Out some Status & come back again...");
                    }
                    BusinessWhatsAppSaveStatuses.this.f135rv.setAdapter(new WhatsAppSavedStatusesAdaptor(BusinessWhatsAppSaveStatuses.this.getActivity(), BusinessWhatsAppSaveStatuses.this.data));
                    BusinessWhatsAppSaveStatuses.this.f135rv.setLayoutManager(new GridLayoutManager(BusinessWhatsAppSaveStatuses.this.getActivity(), 2));
                }
            }.execute(new Void[0]);
        } else {
            this.textView.setVisibility(View.VISIBLE);
            this.textView.setText("No Status Available \n Check Out some Status & come back again...");
        }
        refreshItems();
    }

    private void refreshItems() {
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete()
    {
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
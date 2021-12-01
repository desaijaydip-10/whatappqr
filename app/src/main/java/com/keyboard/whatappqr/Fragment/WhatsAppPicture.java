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

import com.keyboard.whatappqr.Adapters.WhatsAppPictureAdaptor;
import com.keyboard.whatappqr.Models.ModelStatus;
import com.keyboard.whatappqr.R;
import com.keyboard.whatappqr.Utils.Config;

import java.io.File;
import java.util.ArrayList;


public class WhatsAppPicture extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<ModelStatus> data;
    SwipeRefreshLayout mSwipeRefreshLayout;

    /* renamed from: rv */
    RecyclerView f137rv;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate= inflater.inflate(R.layout.fragment_whats_app_picture, container, false);
        f137rv = (RecyclerView) inflate.findViewById(R.id.rv_status);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.contentView);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.textView = (TextView) inflate.findViewById(R.id.textView);
        this.f137rv.setHasFixedSize(true);
        loadData();
        return  inflate;


    }

    private void loadData() {


        this.data = new ArrayList<>();
        File file = new File(Config.WhatsAppDirectoryPath);
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
                        if (listFiles[i].getName().endsWith(".jpg") || listFiles[i].getName().endsWith("gif")) {
                            String[] strArr = new String[]{};
                            strArr[0] = Config.WhatsAppDirectoryPath + listFiles[i].getName();
                            WhatsAppPicture.this.data.add(new ModelStatus(strArr[0], listFiles[i].getName().substring(0, listFiles[i].getName().length() + -4), 0));
                        }
                    }
                    return null;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Void voidR) {
                    super.onPostExecute(voidR);
                    if (WhatsAppPicture.this.data.toArray().length <= 0) {
                        WhatsAppPicture.this.textView.setVisibility(View.VISIBLE);
                        WhatsAppPicture.this.textView.setText("No Status Available \n Check Out some Status & come back again...");
                    }
                    WhatsAppPicture.this.f137rv.setAdapter(new WhatsAppPictureAdaptor(WhatsAppPicture.this.getActivity(), WhatsAppPicture.this.data));
                    WhatsAppPicture.this.f137rv.setLayoutManager(new GridLayoutManager(WhatsAppPicture.this.getActivity(), 2));
                }
            }.execute(new Void[0]);
        } else {
            this.textView.setVisibility(View.VISIBLE);
            this.textView.setText("No Status Available \n Check Out some Status & come back again...");
//            Snackbar.make(getActivity().findViewById(16908290), (CharSequence) "WhatsApp Not Installed", -1).show();
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

    public void onItemsLoadComplete() {
        this.mSwipeRefreshLayout.setRefreshing(false);
    }
}
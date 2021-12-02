package com.keyboard.qrwhatapps.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.keyboard.qrwhatapps.Adapters.WhatsAppPictureAdaptor;
import com.keyboard.qrwhatapps.Models.ModelStatus;
import com.keyboard.qrwhatapps.Models.Model_images;
import com.keyboard.qrwhatapps.R;
import com.keyboard.qrwhatapps.Utils.Config;

import java.io.File;
import java.util.ArrayList;


public class WhatsAppPicture extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<ModelStatus> data;
    SwipeRefreshLayout mSwipeRefreshLayout;

    /* renamed from: rv */
    RecyclerView f137rv;
    TextView textView;

    ArrayList<ModelStatus> imgarralist;
    private static final int REQUEST_PERMISSIONS = 100;


    public static ArrayList<Model_images> al_images = new ArrayList<>();
    boolean boolean_folder;
    //    Adapter_PhotosFolder obj_adapter;
    GridView gv_folder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_whats_app_picture, container, false);
        f137rv = (RecyclerView) inflate.findViewById(R.id.rv_status);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.contentView);
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.textView = (TextView) inflate.findViewById(R.id.textView);
        this.f137rv.setHasFixedSize(true);


        imgarralist = new ArrayList<>();


        fn_imagespath();

//        Dexter.withContext(getContext())
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
//                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
//                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
//                }).check();


        loadData();
        return inflate;

    }


    public void fn_imagespath() {

//        al_images.clear();


        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 1; i < al_images.size(); i++) {
                if (al_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }


            if (boolean_folder) {

                ArrayList<String> al_path = new ArrayList<>();


                al_path.addAll(al_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                al_images.get(int_position).setAl_imagepath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images obj_model = new Model_images();
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                obj_model.setAl_imagepath(al_path);

                al_images.add(obj_model);


            }


        }


        for (int i = 0; i < al_images.size(); i++) {
            Log.e("FOLDER", al_images.get(i).getStr_folder());

            if (al_images.get(i).getStr_folder().equals("WhatsApp Images")) {


                for (int j = 0; j < al_images.get(i).getAl_imagepath().size(); j++) {
//                     Log.e("FILE", al_images.get(i).getAl_imagepath().get(j));

                    imgarralist.add(new ModelStatus(al_images.get(i).getAl_imagepath().get(j)));

                }
            }

        }

         if(imgarralist.size()<=0){
             WhatsAppPicture.this.textView.setVisibility(View.VISIBLE);
             WhatsAppPicture.this.textView.setText("No Status Available \n Check Out some Status & come back again...");
         }
         else {
             f137rv.setLayoutManager(new GridLayoutManager(WhatsAppPicture.this.getActivity(), 2));
             f137rv.setAdapter(new WhatsAppPictureAdaptor(getContext(), imgarralist));
         }


    }

//        obj_adapter = new Adapter_PhotosFolder(getApplicationContext(),al_images);
//        gv_folder.setAdapter(obj_adapter);
//        return al_images;



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
//                        WhatsAppPicture.this.textView.setVisibility(View.VISIBLE);
//                        WhatsAppPicture.this.textView.setText("No Status Available \n Check Out some Status & come back again...");
                    }
//                    WhatsAppPicture.this.f137rv.setAdapter(new WhatsAppPictureAdaptor(WhatsAppPicture.this.getActivity(), WhatsAppPicture.this.data));
//                    WhatsAppPicture.this.f137rv.setLayoutManager(new GridLayoutManager(WhatsAppPicture.this.getActivity(), 2));
                }
            }.execute(new Void[0]);
        } else {

//            this.textView.setVisibility(View.VISIBLE);
//            this.textView.setText("No Status Available \n Check Out some Status & come back again...");
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
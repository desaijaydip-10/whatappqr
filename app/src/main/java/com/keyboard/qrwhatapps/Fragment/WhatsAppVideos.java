package com.keyboard.qrwhatapps.Fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.keyboard.qrwhatapps.Activity.WhatsAppVideosAdaptor;
import com.keyboard.qrwhatapps.Adapters.WhatsAppPictureAdaptor;
import com.keyboard.qrwhatapps.Models.ModelStatus;
import com.keyboard.qrwhatapps.Models.Model_Video;
import com.keyboard.qrwhatapps.Models.Model_images;
import com.keyboard.qrwhatapps.Models.VideoModel;
import com.keyboard.qrwhatapps.R;
import com.keyboard.qrwhatapps.Utils.Config;

import java.io.File;
import java.util.ArrayList;


public class WhatsAppVideos extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<ModelStatus> data;
    SwipeRefreshLayout mSwipeRefreshLayout;

    /* renamed from: rv */
    RecyclerView f139rv;

    ArrayList<Model_Video> al_video;
    public static ArrayList<VideoModel > videoArrayList;

    TextView textView;
    boolean boolean_folder;
    public static ArrayList<Model_images> al_images = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_whats_app_videos, container, false);
        f139rv = (RecyclerView) view.findViewById(R.id.video_recyclerview);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.contentView);
        mSwipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        textView = (TextView) view.findViewById(R.id.textView);
        this.f139rv.setHasFixedSize(true);

        al_video= new ArrayList<>();


//        fn_imagespath();
        setvideos();


//        loadData();
        return  view;
    }

    private void setvideos() {

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name,column_id,thum;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media._ID,MediaStore.Video.Thumbnails.DATA};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));
            Log.e("column_id", cursor.getString(column_id));
            Log.e("thum", cursor.getString(thum));

            Model_Video obj_model = new Model_Video();
            obj_model.setBoolean_selected(false);
            obj_model.setStr_path(absolutePathOfImage);
            obj_model.setStr_thumb(cursor.getString(thum));

            al_video.add(obj_model);

        }





//        ContentResolver contentResolver = getContext().getContentResolver();
//        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//
//        Cursor cursor = contentResolver.query(uri, null, null, null, null);
//
//        //looping through all rows and adding to list
//
//
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
//                @SuppressLint("Range") String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
//                @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
////                @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.RELATIVE_PATH));
//
//
//
//                VideoModel videoModel = new VideoModel();
//                videoModel.setVideoTitle(title);
//                videoModel.setVideoUri(Uri.parse(data));
//                videoModel.setVideoDuration(timeConversion(Long.parseLong(duration)));
//                videoArrayList.add(videoModel);
//
//            } while (cursor.moveToNext());


            f139rv.setLayoutManager(new GridLayoutManager(WhatsAppVideos.this.getActivity(), 2));
            f139rv.setAdapter(new WhatsAppVideosAdaptor(getActivity(), al_video));

        }

        //    public void fn_imagespath() {
//        al_images.clear();
//
//
//        int int_position = 0;
//        Uri uri;
//        Cursor cursor;
//        int column_index_data, column_index_folder_name;
//
//        String absolutePathOfImage = null;
//        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
//
//        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
//        cursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//        while (cursor.moveToNext()) {
//            absolutePathOfImage = cursor.getString(column_index_data);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));
//
//            for (int i = 0; i < al_images.size(); i++) {
//                if (al_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
//                    boolean_folder = true;
//                    int_position = i;
//                    break;
//                } else {
//                    boolean_folder = false;
//                }
//            }
//
//
//            if (boolean_folder) {
//
//                ArrayList<String> al_path = new ArrayList<>();
//                al_path.addAll(al_images.get(int_position).getAl_imagepath());
//                al_path.add(absolutePathOfImage);
//                al_images.get(int_position).setAl_imagepath(al_path);
//
//            } else {
//                ArrayList<String> al_path = new ArrayList<>();
//                al_path.add(absolutePathOfImage);
//                Model_images obj_model = new Model_images();
//                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
//                obj_model.setAl_imagepath(al_path);
//
//                al_images.add(obj_model);
//
//
//            }
//
//
//        }
//
//
//        for (int i = 0; i < al_images.size(); i++) {
//            Log.e("FOLDER", al_images.get(i).getStr_folder());
//
//            if (al_images.get(i).getStr_folder().equals("WhatsApp Video")) {
//                Log.e("videos", al_images.get(i).getStr_folder());
//
//                for (int j = 0; j < al_images.get(i).getAl_imagepath().size(); j++) {
//                     Log.e("FILE", al_images.get(i).getAl_imagepath().get(j));
//
////                    imgarralist.add(new ModelStatus(al_images.get(i).getAl_imagepath().get(j)));
//
//                }
//            }
//
//        }
//
////        if(imgarralist.size()<=0){
////           textView.setVisibility(View.VISIBLE);
////           textView.setText("No Status Available \n Check Out some Status & come back again...");
////        }
////        else {
////            f137rv.setLayoutManager(new GridLayoutManager(WhatsAppPicture.this.getActivity(), 2));
////            f137rv.setAdapter(new WhatsAppPictureAdaptor(getContext(), imgarralist));
////        }
//
//
//    }



    private String timeConversion(long value) {
        String videoTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            videoTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            videoTime = String.format("%02d:%02d", mns, scs);
        }
        return videoTime;
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
                        if (listFiles[i].getName().endsWith(".mp4")) {
                            String[] strArr = new  String[]{};
                            strArr[0] = Config.WhatsAppDirectoryPath + listFiles[i].getName();
                            WhatsAppVideos.this.data.add(new ModelStatus(strArr[0], listFiles[i].getName().substring(0, listFiles[i].getName().length() + -4), 0));
                        }
                    }
                    return null;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Void voidR) {
                    super.onPostExecute(voidR);
                    if (WhatsAppVideos.this.data.toArray().length <= 0) {
                        WhatsAppVideos.this.textView.setVisibility(View.VISIBLE);
                        WhatsAppVideos.this.textView.setText("No Status Available \n Check Out some Status & come back again...");
                    }
//                    WhatsAppVideos.this.f139rv.setAdapter(new WhatsAppVideosAdaptor(WhatsAppVideos.this.getActivity(), WhatsAppVideos.this.data));
//                    WhatsAppVideos.this.f139rv.setLayoutManager(new GridLayoutManager(WhatsAppVideos.this.getActivity(), 2));
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

    private void onItemsLoadComplete() {

        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadData();
        
    }
}
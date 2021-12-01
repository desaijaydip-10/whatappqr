package com.keyboard.whatappqr.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.keyboard.whatappqr.Drawer;
import com.keyboard.whatappqr.Models.ModelStatus;
import com.keyboard.whatappqr.R;
import com.keyboard.whatappqr.Utils.Config;

import java.io.File;
import java.util.ArrayList;

public class WhatsAppVideosAdaptor extends   RecyclerView.Adapter<WhatsAppVideosAdaptor.MyViewHolder>{
    private final String TAG = "VIDEOAdaptor";
    /* access modifiers changed from: private */
    public Context acontext;
    /* access modifiers changed from: private */
    public ArrayList<ModelStatus> arrayList;
    public int count = 6;
    Drawer drawer = new Drawer();
    String listenerValue = "";


    public WhatsAppVideosAdaptor(Context context, ArrayList<ModelStatus> arrayList2) {
        this.arrayList = arrayList2;
        this.acontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_whatsapp_videos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(this.acontext).load(this.arrayList.get(position).getFull_path()).into(holder.imageView);    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder {
        public LinearLayout btn_download;
        public LinearLayout btn_share;
        public ImageView imageView;
        public ImageButton img_btn_download;
        public ImageButton img_btn_share;
        public CardView mCardView;

        public MyViewHolder(@NonNull View view) {
            super(view);

            this.mCardView = (CardView) view.findViewById(R.id.card_view_order_cancel);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.btn_download);
            this.btn_download = linearLayout;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str;
                    ModelStatus modelStatus = (ModelStatus) WhatsAppVideosAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getType() == 0) {
                        str = Config.WhatsAppSaveStatus;
                    } else {
                        str = modelStatus.getType() == 1 ? Config.WhatsAppBusinessSaveStatus : "";
                    }
                    WhatsAppVideosAdaptor.this.copyFileOrDirectory(modelStatus.getFull_path(), str);
                }
            });
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.btn_share);
            this.btn_share = linearLayout2;
            linearLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelStatus modelStatus = (ModelStatus) WhatsAppVideosAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getFull_path().endsWith(".jpg")) {
                        WhatsAppVideosAdaptor.this.shareVia("image/jpg", modelStatus.getFull_path());
                    } else if (modelStatus.getFull_path().endsWith(".mp4")) {
                        WhatsAppVideosAdaptor.this.shareVia("video/mp4", modelStatus.getFull_path());
                    }
                }
            });
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.img_btn_share);
            this.img_btn_share = imageButton;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ModelStatus modelStatus = (ModelStatus) WhatsAppVideosAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getFull_path().endsWith(".jpg")) {
                        WhatsAppVideosAdaptor.this.shareVia("image/jpg", modelStatus.getFull_path());
                    } else if (modelStatus.getFull_path().endsWith(".mp4")) {
                        WhatsAppVideosAdaptor.this.shareVia("video/mp4", modelStatus.getFull_path());
                    }
                }
            });
            ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.img_btn_download);
            this.img_btn_download = imageButton2;
            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String str;
                    ModelStatus modelStatus = (ModelStatus) WhatsAppVideosAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getType() == 0) {
                        str = Config.WhatsAppSaveStatus;
                    } else {
                        str = modelStatus.getType() == 1 ? Config.WhatsAppBusinessSaveStatus : "";
                    }
                    WhatsAppVideosAdaptor.this.copyFileOrDirectory(modelStatus.getFull_path(), str);
                }
            });
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelStatus modelStatus = (ModelStatus) WhatsAppVideosAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    Intent intent = new Intent(WhatsAppVideosAdaptor.this.acontext, VIdeoViewerActivity.class);
                    intent.putExtra("video", modelStatus.getFull_path());
                    intent.putExtra("type", "" + modelStatus.getType());
                    intent.putExtra("atype", "1");
                    WhatsAppVideosAdaptor.this.acontext.startActivity(intent);
                }
            });
        }
    }

    private void shareVia(String str, String str2) {

        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str);
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
        this.acontext.startActivity(Intent.createChooser(intent, "Share via"));
    }

    private void copyFileOrDirectory(String str, String str2) {

        try {
            File file = new File(str);
            File file2 = new File(str2, file.getName());
            if (file.isDirectory()) {
                for (String file3 : file.list()) {
                    copyFileOrDirectory(new File(file, file3).getPath(), file2.getPath());
                }
                return;
            }
            copyFile(file, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void copyFile(java.io.File r10, java.io.File r11) throws java.io.IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.hbus.WhatsWebScanforWhatsappWhatscanQRCode2020.Adaptor.WhatsAppVideosAdaptor.copyFile(java.io.File, java.io.File):void");
    }

}
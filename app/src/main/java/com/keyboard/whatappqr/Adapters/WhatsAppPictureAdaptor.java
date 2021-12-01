package com.keyboard.whatappqr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.keyboard.whatappqr.Drawer;
import com.keyboard.whatappqr.Activity.ImageViewerActivity;
import com.keyboard.whatappqr.Models.ModelStatus;
import com.keyboard.whatappqr.R;
import com.keyboard.whatappqr.Utils.Config;

import java.io.File;
import java.util.ArrayList;

public class WhatsAppPictureAdaptor  extends RecyclerView.Adapter<WhatsAppPictureAdaptor.MyViewHolder> {
    private final String TAG = "PICTUREAdaptor";
    /* access modifiers changed from: private */
    public Context acontext;
    /* access modifiers changed from: private */
    public ArrayList<ModelStatus> arrayList;
    public int count = 6;
    Drawer drawer = new Drawer();
    String listenerValue = "";

    public WhatsAppPictureAdaptor(Context context, ArrayList<ModelStatus> arrayList2) {
        this.arrayList = arrayList2;
        this.acontext = context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_whatsapp_pictures, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Glide.with(this.acontext).load(this.arrayList.get(i).getFull_path()).into(myViewHolder.imageView);
    }

    public int getItemCount() {
        return this.arrayList.size();
    }

    public void copyFileOrDirectory(String str, String str2) {
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

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyFile(java.io.File r10, java.io.File r11) throws java.io.IOException {
        /*
            r9 = this;
            java.io.File r0 = r11.getParentFile()
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0011
            java.io.File r0 = r11.getParentFile()
            r0.mkdirs()
        L_0x0011:
            boolean r0 = r11.exists()
            if (r0 != 0) goto L_0x001a
            r11.createNewFile()
        L_0x001a:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0054 }
            r1.<init>(r10)     // Catch:{ all -> 0x0054 }
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch:{ all -> 0x0054 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x004f }
            r1.<init>(r11)     // Catch:{ all -> 0x004f }
            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch:{ all -> 0x004f }
            r4 = 0
            long r6 = r10.size()     // Catch:{ all -> 0x004f }
            r2 = r0
            r3 = r10
            r2.transferFrom(r3, r4, r6)     // Catch:{ all -> 0x004f }
            android.content.Context r11 = r9.acontext     // Catch:{ all -> 0x004f }
            java.lang.String r1 = "Picture Saved"
            r2 = 0
            android.widget.Toast r11 = android.widget.Toast.makeText(r11, r1, r2)     // Catch:{ all -> 0x004f }
            r11.show()     // Catch:{ all -> 0x004f }
            if (r10 == 0) goto L_0x0049
            r10.close()
        L_0x0049:
            if (r0 == 0) goto L_0x004e
            r0.close()
        L_0x004e:
            return
        L_0x004f:
            r11 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L_0x0056
        L_0x0054:
            r11 = move-exception
            r10 = r0
        L_0x0056:
            if (r0 == 0) goto L_0x005b
            r0.close()
        L_0x005b:
            if (r10 == 0) goto L_0x0060
            r10.close()
        L_0x0060:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbus.WhatsWebScanforWhatsappWhatscanQRCode2020.Adaptor.WhatsAppPictureAdaptor.copyFile(java.io.File, java.io.File):void");
    }

    public void shareVia(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str);
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        this.acontext.startActivity(Intent.createChooser(intent, "Share via"));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout btn_download;
        public LinearLayout btn_share;
        public ImageView imageView;
        public ImageButton img_btn_download;
        public ImageButton img_btn_share;
        public CardView mCardView;

        public MyViewHolder(View view) {
            super(view);
            this.mCardView = (CardView) view.findViewById(R.id.card_view_order_cancel);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.btn_download);
            this.btn_download = linearLayout;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.btn_share);



            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str;
                    ModelStatus modelStatus = (ModelStatus) WhatsAppPictureAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getType() == 0) {
                        str = Config.WhatsAppSaveStatus;
                    } else {
                        str = modelStatus.getType() == 1 ? Config.WhatsAppBusinessSaveStatus : "";
                    }
                    WhatsAppPictureAdaptor.this.copyFileOrDirectory(modelStatus.getFull_path(), str);
                }
            });
            this.btn_share = linearLayout2;



            linearLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ModelStatus modelStatus = (ModelStatus) WhatsAppPictureAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getFull_path().endsWith(".jpg")) {
                        WhatsAppPictureAdaptor.this.shareVia("image/jpg", modelStatus.getFull_path());
                    } else if (modelStatus.getFull_path().endsWith(".mp4")) {
                        WhatsAppPictureAdaptor.this.shareVia("video/mp4", modelStatus.getFull_path());
                    }
                }
            });
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.img_btn_share);
            this.img_btn_share = imageButton;


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelStatus modelStatus = (ModelStatus) WhatsAppPictureAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getFull_path().endsWith(".jpg")) {
                        WhatsAppPictureAdaptor.this.shareVia("image/jpg", modelStatus.getFull_path());
                    } else if (modelStatus.getFull_path().endsWith(".mp4")) {
                        WhatsAppPictureAdaptor.this.shareVia("video/mp4", modelStatus.getFull_path());
                    }
                }
            });

            ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.img_btn_download);
            this.img_btn_download = imageButton2;



            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String str;
                    ModelStatus modelStatus = (ModelStatus) WhatsAppPictureAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    if (modelStatus.getType() == 0) {
                        str = Config.WhatsAppSaveStatus;
                    } else {
                        str = modelStatus.getType() == 1 ? Config.WhatsAppBusinessSaveStatus : "";
                    }
                    WhatsAppPictureAdaptor.this.copyFileOrDirectory(modelStatus.getFull_path(), str);
                }
            });

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelStatus modelStatus = (ModelStatus) WhatsAppPictureAdaptor.this.arrayList.get(MyViewHolder.this.getAdapterPosition());
                    Intent intent = new Intent(WhatsAppPictureAdaptor.this.acontext, ImageViewerActivity.class);
                    intent.putExtra("image", modelStatus.getFull_path());
                    intent.putExtra("type", "" + modelStatus.getType());
                    intent.putExtra("atype", "1");
                    WhatsAppPictureAdaptor.this.acontext.startActivity(intent);
                }
            });
        }
    }
}


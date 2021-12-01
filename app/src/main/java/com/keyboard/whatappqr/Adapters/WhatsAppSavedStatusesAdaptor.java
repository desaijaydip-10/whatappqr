package com.keyboard.whatappqr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.keyboard.whatappqr.Drawer;
import com.keyboard.whatappqr.Activity.ImageViewerActivity;
import com.keyboard.whatappqr.Models.ModelStatus;
import com.keyboard.whatappqr.R;
import com.keyboard.whatappqr.Activity.VIdeoViewerActivity;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

public class WhatsAppSavedStatusesAdaptor extends RecyclerView.Adapter<WhatsAppSavedStatusesAdaptor.CustomViewHoldwer> {
private final String TAG = "SAVEAdaptor";
/* access modifiers changed from: private */
public Context acontext;
/* access modifiers changed from: private */
public ArrayList<ModelStatus> arrayList;
public int count = 6;
        Drawer drawer = new Drawer();
        String listenerValue = "";

public WhatsAppSavedStatusesAdaptor(Context context, ArrayList<ModelStatus> arrayList2) {
        this.arrayList = arrayList2;
        this.acontext = context;
        }

@NonNull
@Override
public CustomViewHoldwer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHoldwer(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_whatsapp_saved_pictures, parent, false));
        }

@Override
public void onBindViewHolder(@NonNull CustomViewHoldwer holder, int position) {

        Glide.with(this.acontext).load(this.arrayList.get(position).getFull_path()).into(holder.imageView);
        }

@Override
public int getItemCount() {
        return arrayList.size();
        }


class  CustomViewHoldwer  extends  RecyclerView.ViewHolder {

    public LinearLayout btn_delete;
    public LinearLayout btn_share;
    public ImageView imageView;
    public ImageButton img_btn_delete;
    public ImageButton img_btn_share;
    public CardView mCardView;

    public CustomViewHoldwer(@NonNull View view) {
        super(view);
        this.mCardView = (CardView) view.findViewById(R.id.card_view_order_cancel);
        this.imageView = (ImageView) view.findViewById(R.id.imageView);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.btn_delete);


        this.btn_delete = linearLayout;

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WhatsAppSavedStatusesAdaptor.this.deleteFile(((ModelStatus) WhatsAppSavedStatusesAdaptor.this.arrayList.get(CustomViewHoldwer.this.getAdapterPosition())).getFull_path(), CustomViewHoldwer.this.getAdapterPosition());
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        });


        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.btn_share);
        this.btn_share = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelStatus modelStatus = (ModelStatus) WhatsAppSavedStatusesAdaptor.this.arrayList.get(CustomViewHoldwer.this.getAdapterPosition());
                if (modelStatus.getFull_path().endsWith(".jpg")) {
                    WhatsAppSavedStatusesAdaptor.this.shareVia("image/jpg", modelStatus.getFull_path());
                } else if (modelStatus.getFull_path().endsWith(".mp4")) {
                    WhatsAppSavedStatusesAdaptor.this.shareVia("video/mp4", modelStatus.getFull_path());
                }
            }
        });
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.img_btn_share);
        this.img_btn_share = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelStatus modelStatus = (ModelStatus) WhatsAppSavedStatusesAdaptor.this.arrayList.get(CustomViewHoldwer.this.getAdapterPosition());
                if (modelStatus.getFull_path().endsWith(".jpg")) {
                    WhatsAppSavedStatusesAdaptor.this.shareVia("image/jpg", modelStatus.getFull_path());
                } else if (modelStatus.getFull_path().endsWith(".mp4")) {
                    WhatsAppSavedStatusesAdaptor.this.shareVia("video/mp4", modelStatus.getFull_path());
                }
            }
        });

        ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.img_btn_delete);
        this.img_btn_delete = imageButton2;
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WhatsAppSavedStatusesAdaptor.this.deleteFile(((ModelStatus) WhatsAppSavedStatusesAdaptor.this.arrayList.get(CustomViewHoldwer.this.getAdapterPosition())).getFull_path(), CustomViewHoldwer.this.getAdapterPosition());
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        });


        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelStatus modelStatus = (ModelStatus) WhatsAppSavedStatusesAdaptor.this.arrayList.get(CustomViewHoldwer.this.getAdapterPosition());
                if (modelStatus.getFull_path().endsWith(".jpg")) {
                    Intent intent = new Intent(WhatsAppSavedStatusesAdaptor.this.acontext, ImageViewerActivity.class);
                    intent.putExtra("image", modelStatus.getFull_path());
                    intent.putExtra("type", "" + modelStatus.getType());
                    intent.putExtra("atype", "0");
                    WhatsAppSavedStatusesAdaptor.this.acontext.startActivity(intent);
                    return;
                }
                Intent intent2 = new Intent(WhatsAppSavedStatusesAdaptor.this.acontext, VIdeoViewerActivity.class);
                intent2.putExtra("video", modelStatus.getFull_path());
                intent2.putExtra("type", "" + modelStatus.getType());
                intent2.putExtra("atype", "0");
                WhatsAppSavedStatusesAdaptor.this.acontext.startActivity(intent2);
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
    public void deleteFile(String str, int i) {
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        if (file.delete()) {
            removeAt(i);
            Toast.makeText(this.acontext, "Delete Success", Toast.LENGTH_SHORT).show();
            PrintStream printStream = System.out;
            printStream.println("file Deleted :" + str);
            return;
        }
        Toast.makeText(this.acontext, "Delete Failed", Toast.LENGTH_SHORT).show();
        PrintStream printStream2 = System.out;
        printStream2.println("file not Deleted :" + str);
    }

    private void removeAt(int i) {

        this.arrayList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.arrayList.size());
    }

}


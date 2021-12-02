package com.keyboard.qrwhatapps.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.keyboard.qrwhatapps.Drawer;
import com.keyboard.qrwhatapps.R;
import com.keyboard.qrwhatapps.Utils.Config;


import java.io.File;

public class ImageViewerActivity extends AppCompatActivity {

    private final String TAG = "ImageViewer";
    String atype = "";
    public LinearLayout btn_download;
    public LinearLayout btn_re_post;
    public LinearLayout btn_share;
    public int count = 6;
    Drawer drawer = new Drawer();
    ImageView imageView;
    String image_path = "";
    public ImageButton img_btn_download;
    public ImageButton img_btn_share;
    public ImageButton img_re_post;
    String listenerValue = "";
    String package_name = "";
    String path = "";
    String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        getSupportActionBar().setTitle((CharSequence) "Image Viewer");
        this.imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        if (intent != null) {
            this.image_path = intent.getStringExtra("image");
            this.type = intent.getStringExtra("type");
            this.atype = intent.getStringExtra("atype");
            if (this.image_path != null) {
                Glide.with((FragmentActivity) this).load(this.image_path).into(this.imageView);
            }
        }
        if (this.type.equals("0")) {
            this.path = Config.WhatsAppSaveStatus;
            this.package_name = "com.whatsapp";
        } else if (this.type.equals("1")) {
            this.path = Config.WhatsAppBusinessSaveStatus;
            this.package_name = "com.whatsapp.w4b";
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.btn_download);
        this.btn_download = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                imageViewerActivity.copyFileOrDirectory(imageViewerActivity.image_path, ImageViewerActivity.this.path);
            }
        });
        ImageButton imageButton = (ImageButton) findViewById(R.id.img_btn_download);
        this.img_btn_download = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                imageViewerActivity.copyFileOrDirectory(imageViewerActivity.image_path, ImageViewerActivity.this.path);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.btn_share);
        this.btn_share = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
                if (ImageViewerActivity.this.image_path.endsWith(".jpg")) {
                    ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                    imageViewerActivity.shareVia("image/jpg", imageViewerActivity.image_path);
                } else if (ImageViewerActivity.this.image_path.endsWith(".mp4")) {
                    ImageViewerActivity imageViewerActivity2 = ImageViewerActivity.this;
                    imageViewerActivity2.shareVia("video/mp4", imageViewerActivity2.image_path);
                }
            }
        });
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.img_btn_share);
        this.img_btn_share = imageButton2;
        imageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
                if (ImageViewerActivity.this.image_path.endsWith(".jpg")) {
                    ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                    imageViewerActivity.shareVia("image/jpg", imageViewerActivity.image_path);
                } else if (ImageViewerActivity.this.image_path.endsWith(".mp4")) {
                    ImageViewerActivity imageViewerActivity2 = ImageViewerActivity.this;
                    imageViewerActivity2.shareVia("video/mp4", imageViewerActivity2.image_path);
                }
            }
        });
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.btn_re_post);
        this.btn_re_post = linearLayout3;
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ImageViewerActivity.this.image_path.endsWith(".jpg")) {
                    ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                    imageViewerActivity.shareViaWhatsApp("image/jpg", imageViewerActivity.image_path, ImageViewerActivity.this.package_name);
                } else if (ImageViewerActivity.this.image_path.endsWith(".mp4")) {
                    ImageViewerActivity imageViewerActivity2 = ImageViewerActivity.this;
                    imageViewerActivity2.shareViaWhatsApp("video/mp4", imageViewerActivity2.image_path, ImageViewerActivity.this.package_name);
                }
            }
        });
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.img_re_post);
        this.img_re_post = imageButton3;
        imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ImageViewerActivity.this.image_path.endsWith(".jpg")) {
                    ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                    imageViewerActivity.shareViaWhatsApp("image/jpg", imageViewerActivity.image_path, ImageViewerActivity.this.package_name);
                } else if (ImageViewerActivity.this.image_path.endsWith(".mp4")) {
                    ImageViewerActivity imageViewerActivity2 = ImageViewerActivity.this;
                    imageViewerActivity2.shareViaWhatsApp("video/mp4", imageViewerActivity2.image_path, ImageViewerActivity.this.package_name);
                }
            }
        });
        if (this.atype.equals("0")) {
            this.btn_download.setVisibility(View.GONE);
        } else if (this.atype.equals("1")) {
            this.btn_download.setVisibility(View.VISIBLE);
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyFile(File r10, File r11) throws java.io.IOException {
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
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0056 }
            r1.<init>(r10)     // Catch:{ all -> 0x0056 }
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch:{ all -> 0x0056 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0051 }
            r1.<init>(r11)     // Catch:{ all -> 0x0051 }
            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch:{ all -> 0x0051 }
            r4 = 0
            long r6 = r10.size()     // Catch:{ all -> 0x0051 }
            r2 = r0
            r3 = r10
            r2.transferFrom(r3, r4, r6)     // Catch:{ all -> 0x0051 }
            android.content.Context r11 = r9.getApplicationContext()     // Catch:{ all -> 0x0051 }
            java.lang.String r1 = "Picture Saved"
            r2 = 0
            android.widget.Toast r11 = android.widget.Toast.makeText(r11, r1, r2)     // Catch:{ all -> 0x0051 }
            r11.show()     // Catch:{ all -> 0x0051 }
            if (r10 == 0) goto L_0x004b
            r10.close()
        L_0x004b:
            if (r0 == 0) goto L_0x0050
            r0.close()
        L_0x0050:
            return
        L_0x0051:
            r11 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L_0x0058
        L_0x0056:
            r11 = move-exception
            r10 = r0
        L_0x0058:
            if (r0 == 0) goto L_0x005d
            r0.close()
        L_0x005d:
            if (r10 == 0) goto L_0x0062
            r10.close()
        L_0x0062:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbus.WhatsWebScanforWhatsappWhatscanQRCode2020.ViewerActivity.ImageViewerActivity.copyFile(java.io.File, java.io.File):void");
    }

    public void shareVia(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str);
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    public void shareViaWhatsApp(String str, String str2, String str3) {
        try {
            getPackageManager().getPackageInfo(str3, 0);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(str);
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
            intent.setPackage(str3);
            startActivity(Intent.createChooser(intent, "Share via"));
        } catch (PackageManager.NameNotFoundException unused) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
        }

    }
}
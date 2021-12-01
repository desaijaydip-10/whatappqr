package com.keyboard.whatappqr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.keyboard.whatappqr.C0781R;
import com.keyboard.whatappqr.Drawer;
import com.keyboard.whatappqr.R;
import com.keyboard.whatappqr.Utils.Config;

import java.io.File;

public class VIdeoViewerActivity extends AppCompatActivity {

    private final String TAG = "VideoViewer";
    String atype = "";
    public LinearLayout btn_download;
    public LinearLayout btn_re_post;
    public LinearLayout btn_share;
    public int count = 6;
    Drawer drawer = new Drawer();
    public ImageButton img_btn_download;
    public ImageButton img_btn_share;
    public ImageButton img_re_post;
    String listenerValue = "";
    String package_name = "";
    String path = "";
    /* access modifiers changed from: private */
    public int position = 0;
    String type = "";
    String videoPath = "";
    VideoView video_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_viewer);


        this.video_view = (VideoView) findViewById(R.id.video_view);
        getSupportActionBar().setTitle((CharSequence) "Video Viewer");
        Intent intent = getIntent();
        if (intent != null) {
            this.videoPath = intent.getStringExtra("video");
            this.type = intent.getStringExtra("type");
            this.atype = intent.getStringExtra("atype");
            this.video_view.setVideoPath(this.videoPath);
            this.video_view.start();
        }
        this.video_view.setMediaController(new MediaController(this));
        try {
            this.video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    VIdeoViewerActivity.this.video_view.seekTo(VIdeoViewerActivity.this.position);
                    if (VIdeoViewerActivity.this.position == 0) {
                        VIdeoViewerActivity.this.video_view.start();
                    } else {
                        VIdeoViewerActivity.this.video_view.resume();
                    }
                }
            });
        } catch (Exception unused) {

        }


        if (this.type.equals("0")) {
            this.path = Config.WhatsAppSaveStatus;
            this.package_name = "com.whatsapp";
        } else if (this.type.equals("1")) {
            this.path = Config.WhatsAppBusinessSaveStatus;
            this.package_name = "com.whatsapp.w4b";
        }


        LinearLayout linearLayout = (LinearLayout) findViewById(C0781R.C0783id.btn_download);
        this.btn_download = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VIdeoViewerActivity vIdeoViewerActivity = VIdeoViewerActivity.this;
                vIdeoViewerActivity.copyFileOrDirectory(vIdeoViewerActivity.videoPath, VIdeoViewerActivity.this.path);
            }
        });
        ImageButton imageButton = (ImageButton) findViewById(C0781R.C0783id.img_btn_download);
        this.img_btn_download = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VIdeoViewerActivity vIdeoViewerActivity = VIdeoViewerActivity.this;
                vIdeoViewerActivity.copyFileOrDirectory(vIdeoViewerActivity.videoPath, VIdeoViewerActivity.this.path);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) findViewById(C0781R.C0783id.btn_share);
        this.btn_share = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
                if (VIdeoViewerActivity.this.videoPath.endsWith(".jpg")) {
                    VIdeoViewerActivity vIdeoViewerActivity = VIdeoViewerActivity.this;
                    vIdeoViewerActivity.shareVia("image/jpg", vIdeoViewerActivity.videoPath);
                } else if (VIdeoViewerActivity.this.videoPath.endsWith(".mp4")) {
                    VIdeoViewerActivity vIdeoViewerActivity2 = VIdeoViewerActivity.this;
                    vIdeoViewerActivity2.shareVia("video/mp4", vIdeoViewerActivity2.videoPath);
                }
            }
        });
        ImageButton imageButton2 = (ImageButton) findViewById(C0781R.C0783id.img_btn_share);
        this.img_btn_share = imageButton2;
        imageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
                if (VIdeoViewerActivity.this.videoPath.endsWith(".jpg")) {
                    VIdeoViewerActivity vIdeoViewerActivity = VIdeoViewerActivity.this;
                    vIdeoViewerActivity.shareVia("image/jpg", vIdeoViewerActivity.videoPath);
                } else if (VIdeoViewerActivity.this.videoPath.endsWith(".mp4")) {
                    VIdeoViewerActivity vIdeoViewerActivity2 = VIdeoViewerActivity.this;
                    vIdeoViewerActivity2.shareVia("video/mp4", vIdeoViewerActivity2.videoPath);
                }
            }
        });
        LinearLayout linearLayout3 = (LinearLayout) findViewById(C0781R.C0783id.btn_re_post);
        this.btn_re_post = linearLayout3;
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VIdeoViewerActivity.this.videoPath.endsWith(".jpg")) {
                    VIdeoViewerActivity vIdeoViewerActivity = VIdeoViewerActivity.this;
                    vIdeoViewerActivity.shareViaWhatsApp("image/jpg", vIdeoViewerActivity.videoPath, VIdeoViewerActivity.this.package_name);
                } else if (VIdeoViewerActivity.this.videoPath.endsWith(".mp4")) {
                    VIdeoViewerActivity vIdeoViewerActivity2 = VIdeoViewerActivity.this;
                    vIdeoViewerActivity2.shareViaWhatsApp("video/mp4", vIdeoViewerActivity2.videoPath, VIdeoViewerActivity.this.package_name);
                }
            }
        });
        ImageButton imageButton3 = (ImageButton) findViewById(C0781R.C0783id.img_re_post);
        this.img_re_post = imageButton3;
        imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VIdeoViewerActivity.this.videoPath.endsWith(".jpg")) {
                    VIdeoViewerActivity vIdeoViewerActivity = VIdeoViewerActivity.this;
                    vIdeoViewerActivity.shareViaWhatsApp("image/jpg", vIdeoViewerActivity.videoPath, VIdeoViewerActivity.this.package_name);
                } else if (VIdeoViewerActivity.this.videoPath.endsWith(".mp4")) {
                    VIdeoViewerActivity vIdeoViewerActivity2 = VIdeoViewerActivity.this;
                    vIdeoViewerActivity2.shareViaWhatsApp("video/mp4", vIdeoViewerActivity2.videoPath, VIdeoViewerActivity.this.package_name);
                }
            }
        });
        if (this.atype.equals("0")) {
            this.btn_download.setVisibility(View.GONE);
        } else if (this.atype.equals("1")) {
            this.btn_download.setVisibility(View.VISIBLE);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("Position", this.video_view.getCurrentPosition());
        this.video_view.pause();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("Position");
        this.position = i;
        this.video_view.seekTo(i);
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
            java.lang.String r1 = "Video Saved"
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
        throw new UnsupportedOperationException("Method not decompiled: com.hbus.WhatsWebScanforWhatsappWhatscanQRCode2020.ViewerActivity.VIdeoViewerActivity.copyFile(java.io.File, java.io.File):void");
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

    /* access modifiers changed from: protected */
    public void onResume() {
        this.video_view.start();
        super.onResume();
    }
}
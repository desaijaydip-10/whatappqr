package com.keyboard.whatappqr.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.github.ybq.android.spinkit.SpinKitView;
import com.keyboard.whatappqr.R;

public class SplashScreen extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 200;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    int progressStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final SpinKitView spinKitView = (SpinKitView) findViewById(R.id.progressbar);
        if (!checkPermission()) {
            requestPermission();
        }
        new Thread(new Runnable() {
            public void run() {
                while (SplashScreen.this.progressStatus < 500) {
                    SplashScreen.this.progressStatus++;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SplashScreen.this.handler.post(new Runnable() {
                        public void run() {
                            spinKitView.setProgress(SplashScreen.this.progressStatus);
                        }
                    });
                }
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }).start();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"}, 200);
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_NETWORK_STATE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_WIFI_STATE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.INTERNET") == 0;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 200 && iArr.length > 0) {
            boolean z = false;
            int i2 = iArr[0];
            boolean z2 = iArr[1] == 0;
            boolean z3 = iArr[2] == 0;
            boolean z4 = iArr[3] == 0;
            if (iArr[4] == 0) {
                z = true;
            }
            if (z2 && z3 && z4 && z) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else if (Build.VERSION.SDK_INT < 23) {
            } else {
                if (shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE") || shouldShowRequestPermissionRationale("android.permission.CAMERA") || shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE") || shouldShowRequestPermissionRationale("android.permission.WRITE_CONTACTS") || shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
                    showMessageOKCancel("You need to allow access to both the permissions", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SplashScreen.this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"}, 200);
                        }
                    });
                }
            }
        }
    }

    private void showMessageOKCancel(String str, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(this).setMessage((CharSequence) str).setPositiveButton((CharSequence) "OK", onClickListener).setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

}

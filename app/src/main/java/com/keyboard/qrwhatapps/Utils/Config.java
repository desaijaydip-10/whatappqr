package com.keyboard.qrwhatapps.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {

    public static final String WhatsAppBusinessDirectoryPath = "/storage/emulated/0/WhatsApp Business/Media/.Statuses/";
    public static final String WhatsAppBusinessSaveStatus = "/storage/emulated/0/WhatsAppStatusesDir/Media/WhatsAppBusiness/";
    public static final String WhatsAppDirectoryPath = "/storage/emulated/0/WhatsApp/Media/.Statuses/";
    public static final String WhatsAppSaveStatus = "/storage/emulated/0/WhatsAppStatusesDir/Media/WhatsApp/";
    public static final int count = 6;

    public static String getPictureShareState(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);

        if (sharedPreferences.getString("SharePicture", "").length() > 0) {
            return sharedPreferences.getString("SharePicture", "");
        }
        return "";
    }

    public static String getPictureDownloadState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);
        if (sharedPreferences.getString("DownloadPicture", "").length() > 0) {
            return sharedPreferences.getString("DownloadPicture", "");
        }

        return "";
    }

    public static String getVideoDownloadState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);

        if (sharedPreferences.getString("DownloadVideo", "").length() > 0) {

            return sharedPreferences.getString("DownloadVideo", "");
        }
        return "";
    }

    public static String getVideoShareState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);
        if (sharedPreferences.getString("ShareVideo", "").length() > 0) {
            return sharedPreferences.getString("ShareVideo", "");
        }
        return "";
    }

    public static String getSaveDeleteState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);
        if (sharedPreferences.getString("DeleteSave", "").length() > 0) {
            return sharedPreferences.getString("DeleteSave", "");
        }
        return "";
    }

    public static String getSaveShareState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);
        if (sharedPreferences.getString("ShareSave", "").length() > 0) {
            return sharedPreferences.getString("ShareSave", "");
        }
        return "";
    }

    public static String getALLState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFRENCE", 0);
        if (sharedPreferences.getString("ALL", "").length() > 0) {
            return sharedPreferences.getString("ALL", "");
        }
        return "";
    }
}
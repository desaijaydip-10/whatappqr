package com.keyboard.qrwhatapps.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;
import com.keyboard.qrwhatapps.R;


public class DirectChat extends AppCompatActivity {




    public final String TAG = DirectChat.class.getSimpleName();
    private LinearLayout adView1;
    LinearLayout button_save_number;
    CountryCodePicker country_code;
    /* access modifiers changed from: private */
//    public NativeAd nativeAd;
//    private NativeAdLayout nativeAdLayout;
    LinearLayout send_message_button;
    EditText text_message;
    EditText text_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_chat);
        loadNativeAd();
        ((TextView) findViewById(R.id.title)).setText(getString(R.string.message_menu));
        ((ImageView) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DirectChat.this.onBackPressed();
            }
        });
        this.text_number = (EditText) findViewById(R.id.text_number);
        this.button_save_number = (LinearLayout) findViewById(R.id.save_contact);
        this.send_message_button = (LinearLayout) findViewById(R.id.send_message_button);
        this.text_message = (EditText) findViewById(R.id.text_message);
        this.country_code = (CountryCodePicker) findViewById(R.id.country_code);
        this.send_message_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String selectedCountryCode = DirectChat.this.country_code.getSelectedCountryCode();
                String trim = DirectChat.this.text_number.getText().toString().trim();
                String trim2 = DirectChat.this.text_message.getText().toString().trim();
                String str = selectedCountryCode + trim;
                if (!TextUtils.isEmpty(trim)) {
                    if (TextUtils.isEmpty(trim2)) {
                        trim2 = " ";
                    }
                    DirectChat.this.sendMessageOnWhatsApp(str, trim2);
                    return;
                }
                DirectChat.this.text_number.setError("Please enter phone number first.");
            }
        });
        this.button_save_number.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DirectChat.this.SaveContact();
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendMessageOnWhatsApp(String str, String str2) {
        str.replace("+", "").replace(" ", "");
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", new Object[]{str, str2}))));
    }

    public void SaveContact() {
        if (this.text_number.getText().toString().isEmpty()) {
            Alert_Dialog_Blank_Input();
            return;
        }
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setType("vnd.android.cursor.dir/raw_contact");
        intent.putExtra("phone", ((CountryCodePicker) findViewById(R.id.country_code)).getFullNumberWithPlus() + this.text_number.getText());
        startActivity(intent);
    }

    public void Alert_Dialog_Blank_Input() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle((CharSequence) "Error");
        builder.setMessage((CharSequence) "Please Enter A Number");
        AlertDialog create = builder.create();
        create.getWindow().setGravity(17);
        create.show();
    }

    public void onBackPressed() {
        supportFinishAfterTransition();
//        AdLoaderDirectChat.showAd();
        super.onBackPressed();
    }

    private void loadNativeAd() {
//        this.nativeAd = new NativeAd((Context) this, getString(C0781R.string.direct_native_ad));
//        C07654 r0 = new NativeAdListener() {
//            public void onMediaDownloaded(C0569Ad ad) {
//                Log.e(DirectChat.this.TAG, "Native ad finished downloading all assets.");
//            }
//
//            public void onError(C0569Ad ad, AdError adError) {
//                String access$100 = DirectChat.this.TAG;
//                Log.e(access$100, "Native ad failed to load: " + adError.getErrorMessage());
//            }
//
//            public void onAdLoaded(C0569Ad ad) {
//                if (DirectChat.this.nativeAd != null && DirectChat.this.nativeAd == ad) {
//                    DirectChat directChat = DirectChat.this;
//                    directChat.inflateAd(directChat.nativeAd);
//                    Log.d(DirectChat.this.TAG, "Native ad is loaded and ready to be displayed!");
//                }
//            }
//
//            public void onAdClicked(C0569Ad ad) {
//                Log.d(DirectChat.this.TAG, "Native ad clicked!");
//            }
//
//            public void onLoggingImpression(C0569Ad ad) {
//                Log.d(DirectChat.this.TAG, "Native ad impression logged!");
//            }
//        };
//        NativeAd nativeAd2 = this.nativeAd;
//        nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(r0).build());
    }

    /* access modifiers changed from: private */
//    public void inflateAd(NativeAd nativeAd2) {
//        nativeAd2.unregisterView();
//        this.nativeAdLayout = (NativeAdLayout) findViewById(C0781R.C0783id.native_ad_container_direct_chat);
//        int i = 0;
//        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(C0781R.layout.native_ad_layout, this.nativeAdLayout, false);
//        this.adView1 = linearLayout;
//        this.nativeAdLayout.addView(linearLayout);
//        LinearLayout linearLayout2 = (LinearLayout) findViewById(C0781R.C0783id.ad_choices_container);
//        AdOptionsView adOptionsView = new AdOptionsView(this, nativeAd2, this.nativeAdLayout);
//        linearLayout2.removeAllViews();
//        linearLayout2.addView(adOptionsView, 0);
//        MediaView mediaView = (MediaView) this.adView1.findViewById(C0781R.C0783id.native_ad_icon);
//        TextView textView = (TextView) this.adView1.findViewById(C0781R.C0783id.native_ad_title);
//        MediaView mediaView2 = (MediaView) this.adView1.findViewById(C0781R.C0783id.native_ad_media);
//        TextView textView2 = (TextView) this.adView1.findViewById(C0781R.C0783id.native_ad_sponsored_label);
//        Button button = (Button) this.adView1.findViewById(C0781R.C0783id.native_ad_call_to_action);
//        textView.setText(nativeAd2.getAdvertiserName());
//        ((TextView) this.adView1.findViewById(C0781R.C0783id.native_ad_body)).setText(nativeAd2.getAdBodyText());
//        ((TextView) this.adView1.findViewById(C0781R.C0783id.native_ad_social_context)).setText(nativeAd2.getAdSocialContext());
//        if (!nativeAd2.hasCallToAction()) {
//            i = 4;
//        }
//        button.setVisibility(i);
//        button.setText(nativeAd2.getAdCallToAction());
//        textView2.setText(nativeAd2.getSponsoredTranslation());
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(textView);
//        arrayList.add(button);
//        nativeAd2.registerViewForInteraction((View) this.adView1, mediaView2, mediaView, (List<View>) arrayList);
//    }
//    }

}
package com.keyboard.qrwhatapps.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.keyboard.qrwhatapps.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        setAlertDialoge();
    }



    private void setAlertDialoge() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setMessage("Write your message here.");
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                       startActivity(new Intent(StartActivity.this, MainActivity.class));
//                    }
//                });
//
//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                        finishAffinity();
//                    }
//                });

        AlertDialog alert11 = builder1.create();



//        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        View view = LayoutInflater.from(StartActivity.this).inflate(R.layout.start_alert_dialog,null);

        CardView cardView = view.findViewById(R.id.card);
        cardView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

//        AlertDialog alertDialog= builder.create();
        alert11.setView(view);


        Button okbtn= view.findViewById(R.id.yesntn);
        Button btnno= view.findViewById(R.id.nobtn);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));

                alert11.dismiss();
            }
        });

        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert11.dismiss();
               finish();
            }
        });
        alert11.show();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
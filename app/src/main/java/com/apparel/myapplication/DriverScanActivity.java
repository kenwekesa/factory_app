package com.apparel.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;

public class DriverScanActivity extends AppCompatActivity {

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_scan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CardView cv = findViewById(R.id.driver_scan_button);

        activity = this;

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    Intent intentt = new Intent(getApplicationContext(), QrCodeScannerDriver.class);
                    // intent.putExtra(intentt); // "PRODUCT_MODE for bar codes
                    startActivity(intentt);




            }
        });
    }



    }










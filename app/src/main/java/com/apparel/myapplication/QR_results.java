package com.apparel.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QR_results extends AppCompatActivity {


    ConnectionVariables con_variables=new ConnectionVariables();
    public static Activity activity;
    VehicleScanning vs;
    String ip= con_variables.getGlobal_ip(),
            db = con_variables.getDb(),
            u_name = con_variables.getU_name(),
            pass= con_variables.getPass();


    public static String scanStatus, vehicle_number;

    DBConnection connection;
    GetData getData;
    Connection con;


    static String selected_gatename,user_location;
    static int location_id, gate_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity = this;

        this.setTitle("Vehicle Scan");

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vs = new VehicleScanning();


        Bundle extras = getIntent().getExtras();
        vehicle_number = extras.getString("vehicle_number");

        user_location = extras.getString("location");
        selected_gatename = extras.getString("gatename");

        location_id = vs.getGateLocationID();
        gate_id = vs.getGateID();


        //Find views from layout
        TextView number_view = findViewById(R.id.vehicle_number_view);
        Button vehicle_signin_btn = findViewById(R.id.vehicle_signin_btn);
        Button vehicle_signout_btn = findViewById(R.id.vehicle_signout_btn);

        //Initialise instances of relevant classes
        connection = new DBConnection();
        getData = new GetData();


        number_view.setText(vehicle_number);


        vehicle_signin_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                    // Change below query according to your own database.
                    scanStatus ="I";
                    Intent intent = new Intent(getApplicationContext(),DriverScanActivity.class);
                    startActivity(intent);


            }
        });

        vehicle_signout_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                    // Change below query according to your own database.
                    scanStatus = "O";
                Intent intent = new Intent(getApplicationContext(),DriverScanActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                Intent result_intent = new Intent(getApplicationContext(),QrCodeScanner2.class);
                startActivity(result_intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String passStatus()
    {
        return scanStatus;
    }

    public static String passVehicleNumber()
    {
        return vehicle_number;
    }

    public static String gatename()
    {
        return selected_gatename;
    }

    public static String passLocation()
    {
        return user_location;
    }

}

package com.apparel.myapplication;

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
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Driver_Scan_results extends AppCompatActivity {


    ConnectionVariables con_variables=new ConnectionVariables();
    VehicleScanning vs;
    MainPageActivity mpa;
    QR_results qr_results;

    String ip= con_variables.getGlobal_ip(),
            db = con_variables.getDb(),
            u_name = con_variables.getU_name(),
            pass= con_variables.getPass(), scanStatus;

    int user_id;

    DBConnection connection;
    GetData getData;
    Connection con;

    String user_location,selected_gatename,vehicleScanStatus, driver_number, remarks;
    int driver_id, odometer_value=0;

    String from_what_intent;
    String vehicle_number;

    static int location_id, gate_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__scan_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Vehicle Scan");

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vs = new VehicleScanning();
        mpa = new MainPageActivity();


        Bundle extras = getIntent().getExtras();
        driver_number = extras.getString("driverNO");
        from_what_intent = extras.getString("from_intent");







        user_location = qr_results.passLocation();
        selected_gatename = new VehicleScanning().getGateName();


        // Check from which intent we implemented this intent
        if(from_what_intent.equals("ManualVehicleSelection"))
        {
            vehicle_number = extras.getString("vehicleNumber");
            vehicleScanStatus = extras.getString("status");

        }
        else
        {

            vehicleScanStatus = qr_results.passStatus();
            vehicle_number = qr_results.passVehicleNumber();

        }




        //Get relevant variables' values
        location_id = vs.getGateLocationID();
        gate_id = vs.getGateID();

        driver_id = Integer.parseInt(driver_number);



        //Find views from layout
        final TextView number_view = findViewById(R.id.vehicle_number_view);
        TextView driver_number_view = findViewById(R.id.driver_number_view);
        TextView gate_name_view = findViewById(R.id.gate_name);

       // EditText remarks_text_box=findViewById(R.id.remarks_box);


        Button submit_btn = findViewById(R.id.submit_button);

        //Initialise instances of relevant classes
        connection = new DBConnection();
        getData = new GetData();


        number_view.setText(vehicle_number);
        gate_name_view.setText(selected_gatename);
        driver_number_view.setText(driver_number);



        submit_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                EditText remarks_text_box=findViewById(R.id.remarks_box);


                EditText odometer_box=findViewById(R.id.odometer_box);




                // TODO Auto-generated method stub
                con = connection.connectionclass();        // Connect to database
                if (con == null) {
                    TextView login_error_textview = findViewById(R.id.login_error_textview);
                    login_error_textview.setText("Connection failure");
                    login_error_textview.setVisibility(View.VISIBLE);
                } else {
                    // Change below query according to your own database.

                    if((odometer_box.getText().toString().trim().length() < 1)&&(remarks_text_box.getText().toString().trim().length() >0))
                    {
                        TextView data_error_view = findViewById(R.id.scan_submit_error_view);
                        data_error_view.setText("The odometer field is empty, please fill it.");
                        data_error_view.setVisibility(View.VISIBLE);
                    }
                    else if((odometer_box.getText().toString().trim().length() >0)&&(remarks_text_box.getText().toString().trim().length() <1))
                    {
                        TextView data_error_view = findViewById(R.id.scan_submit_error_view);
                        data_error_view.setText("The Remarks field is empty, please fill it.");
                        data_error_view.setVisibility(View.VISIBLE);
                    }

                    else if((odometer_box.getText().toString().trim().length() < 1)&&(remarks_text_box.getText().toString().trim().length() <1))
                    {
                        TextView data_error_view = findViewById(R.id.scan_submit_error_view);
                        data_error_view.setText("The Remarks and Odometer fields are empty, please fill them.");
                        data_error_view.setVisibility(View.VISIBLE);

                    }

                    else {


                        remarks= remarks_text_box.getText().toString();

                        odometer_value= Integer.parseInt(odometer_box.getText().toString());




                        int rs = getData.insertLogData(vehicle_number, vehicleScanStatus, gate_id, location_id, driver_id, remarks, odometer_value);

                        Intent intent = new Intent(getApplicationContext(), QR_results.class);

                        if (rs > 0) {

                            // show the scanner result into dialog box.


                            AlertDialog.Builder builder = new AlertDialog.Builder(Driver_Scan_results.this);
                            builder.setTitle("Done");
                            builder.setMessage("Vehicle signing successfully done");
                            AlertDialog alert1 = builder.create();
                            alert1.show();
                            alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                @Override
                                public void onCancel(DialogInterface dialog) {

                                    QR_results.activity.finish();//finish the qr results for vehicle as well
                                    DriverScanActivity.activity.finish();//finish the scan driver activity as well dependent on scan vehicle.
                                    finish();

                                }
                            });

                        } else {

                            // show the scanner result into dialog box.
                            AlertDialog.Builder builder = new AlertDialog.Builder(Driver_Scan_results.this);
                            builder.setTitle("Failed.");
                            builder.setMessage("An Error occurred, try again.");
                            AlertDialog alert1 = builder.create();
                            alert1.show();

                            alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    finish();
                                }
                            });
                        }
                    }

                }
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

}

package com.apparel.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManualVehicleSelection extends AppCompatActivity {


    Connection con;
    GetData getData;

    String veh_number=" ";

    ArrayList<String> vehicle_num_list = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_vehicle_selection);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.setTitle("Manual Vehicle Selection");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //Classes
        getData = new GetData();

        //Views
        Button v_in_btn = findViewById(R.id.vehicle_signin_btn);
        Button v_out_btn = findViewById(R.id.vehicle_signout_btn);

        Spinner v_number_spinner = findViewById(R.id.vehicle_selection_spinner);

        final TextView error_textview = findViewById(R.id.errorview);



        //Data
        ResultSet v_num_rs = getData.getVehiclenumbers();

        try {

            while (v_num_rs.next())
            {
                String v_num = v_num_rs.getString("RegistrationNo");
                vehicle_num_list.add(v_num);
            }

        }
        catch (Exception ex)
        {
            Log.e("Error caught: ", ex.getMessage());

        }

        ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicle_num_list);
        NoCoreAdapter.notifyDataSetChanged();
        v_number_spinner.setAdapter(NoCoreAdapter);



        v_number_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // pos = position;
                if(parent.getItemAtPosition(position)!=null) {
                    veh_number = parent.getItemAtPosition(position).toString();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        v_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(veh_number.equals(" "))
                {
                    error_textview.setText("No vehicle selected, you can't proceed.");
                    error_textview.setVisibility(View.VISIBLE);

                }
                else
                    {

                    Intent result_intent = new Intent(getApplicationContext(), Driver_Scan_results.class);
                    result_intent.putExtra("driverNO", "0");
                    result_intent.putExtra("from_intent", "ManualVehicleSelection");
                    result_intent.putExtra("status", "I");
                    result_intent.putExtra("vehicleNumber", veh_number);
                    finish();
                    startActivity(result_intent);
                }

            }
        });

        v_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (veh_number.equals(" ")) {
                    error_textview.setText("No vehicle selected, you can't proceed.");
                    error_textview.setVisibility(View.VISIBLE);

                } else {
                    Intent result_intent = new Intent(getApplicationContext(), Driver_Scan_results.class);
                    result_intent.putExtra("driverNO", "0");
                    result_intent.putExtra("from_intent", "ManualVehicleSelection");
                    result_intent.putExtra("status", "O");
                    result_intent.putExtra("vehicleNumber", veh_number);

                    finish();
                    startActivity(result_intent);

                }
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

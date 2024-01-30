package com.apparel.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleScanning extends AppCompatActivity {



Connection con;
DBConnection connection;
GetData getData;


static String selected_gatename=" ";

private static int locationID;
private static int gateID;


private static String location;


    ArrayList<String> gatename_list = new ArrayList();
    ArrayList<String> gateID_list = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_scanning);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialise other used variables


        this.setTitle("Vehicle Scanning");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //Create instance of classes
        connection = new DBConnection();
        getData = new GetData();

        //find views
        Spinner gate_name_selection_spinner = findViewById(R.id.gate_selection_spinner);
        CardView cv = findViewById(R.id.scan_vehicle_button);
        CardView manual_card = findViewById((R.id.manual_vehicle_button));
        final TextView gate_error_view = findViewById(R.id.gate_selection_errorview);


        Bundle extras = getIntent().getExtras();
        final String user_location = extras.getString("location");

        location = user_location;

        locationID = new MainPageActivity().passLocationID();

        con = connection.connectionclass();
        if (con == null) {

        } else {
            ResultSet location_rs = getData.getGateData(locationID);
            // String query3 = "select distinct LocationID from vw_EmployeeMaster em where Location = "+location_spinner_selected_value;
            //String query2 = "select u.Username,u.Id,e.Location,u.LocationID,e.EmpID,u.Username,e.FirstName from UsersMaster_tbl u inner join vw_EmployeeMaster e on e.EmpID=u.Empid where u.LocationID = '"+selected_location_id+"' and e.Status = 'A' order by u.Username";


            try {

                Statement st = con.createStatement();
                Statement st3 = con.createStatement();


                while (location_rs.next()) {
                    String Gatename = location_rs.getString("GateName");
                    String gateID = location_rs.getString("GateID");
                    // String Location_ID=location_rs.getString("LocationID");
                    gatename_list.add(Gatename);
                    gateID_list.add(gateID);
                    // gateid_list.add(Location_ID);

                }


            } catch (SQLException se) {
                Log.e("error here 1 : ", se.getMessage());
            }

            //Set data to first Spinner field
            // String[] array = location_list.toArray(new String[0]);
            ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, gatename_list);
            NoCoreAdapter.notifyDataSetChanged();
            gate_name_selection_spinner.setAdapter(NoCoreAdapter);


            gate_name_selection_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                   // pos = position;
                   if(parent.getItemAtPosition(position)!=null) {
                       selected_gatename = parent.getItemAtPosition(position).toString();
                       gateID = Integer.parseInt(gateID_list.get(position));

                   }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            //Litener for scan button

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (selected_gatename.equals(" ")) {

                        gate_error_view.setText("No gate selection, you can't scan!!!");
                        gate_error_view.setVisibility(View.VISIBLE);


                    } else {
                        Intent intentt = new Intent(getApplicationContext(), QrCodeScanner2.class);
                        // intent.putExtra(intentt); // "PRODUCT_MODE for bar codes
                        intentt.putExtra("location", user_location);
                        intentt.putExtra("gatename", selected_gatename);

                        startActivity(intentt);



              /*  try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

                    startActivityForResult(intent, 0);

                } catch (Exception e) {

                    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                    startActivity(marketIntent);

                }*/

                    }
                }
            });


            //Litener for manual button

            manual_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (selected_gatename.equals(" ")) {

                        gate_error_view.setText("No gate selection, you can't record!!!");
                        gate_error_view.setVisibility(View.VISIBLE);


                    } else {
                        Intent intentt = new Intent(getApplicationContext(), ManualVehicleSelection.class);
                        // intent.putExtra(intentt); // "PRODUCT_MODE for bar codes
                        intentt.putExtra("location", user_location);
                        intentt.putExtra("gatename", selected_gatename);

                        startActivity(intentt);



              /*  try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

                    startActivityForResult(intent, 0);

                } catch (Exception e) {

                    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                    startActivity(marketIntent);

                }*/

                    }
                }
            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int getGateID()
    {
           return gateID;
    }

    public static int getGateLocationID()
    {
        return locationID;
    }

    public static String getLocation()
    {
        return location;
    }

    public static String getGateName(){return selected_gatename;}

}

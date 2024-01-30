package com.apparel.myapplication;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static android.icu.lang.UProperty.INT_START;

public class DeviceAllocation extends AppCompatActivity {

    ArrayList<String> dev_list;
    ArrayList<String> dev_id_list = new ArrayList<>();
    ArrayList<Integer> dev_isempidrequired_list= new ArrayList<>();

    ArrayList<String> status_list = new ArrayList<>();

    ArrayList<String> department_list = new ArrayList<>();
    ArrayList<String> dep_id_list = new ArrayList<>();
    ArrayList<String> username_list = new ArrayList<>();
    ArrayList<Integer> designation_list = new ArrayList<>();
    ArrayList<Integer> location_id_list = new ArrayList<>();



    ArrayList<Integer> emp_id_list = new ArrayList<>();
    ArrayList<Integer> ip_series = new ArrayList<>();
    ArrayList<Integer> ips = new ArrayList<>();

    int selected_dp_pk, designationPk ,selected_empid, selected_device_id;
    String selected_department, selected_devicetype, selected_username, selected_status, remarks;
    int isempid_required_status;
    int user_id, selected_loca_id;


    ResultSet empid_rs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_allocation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Device Allocation");

        String[] likely_remarks = getResources().getStringArray(R.array.Likely_remarks);
        //views
        Spinner dev_type_sp = findViewById(R.id.dev_type_spinner);
        Spinner dep_spinner = findViewById(R.id.department_spinner);
        final Spinner empid_spinner = findViewById(R.id.empid_spinner);
        Spinner status_sp = findViewById(R.id.status_spinner);

        //---------------------
        Button submit_btn = findViewById(R.id.submit_btn);

        //---------------------
        final TextView username_view = findViewById(R.id.username_tv);
        final TextView empid_tv = findViewById(R.id.empid_tv);
        final TextView username_lbl = findViewById(R.id.username_lbl);

        final EditText remarks_tv = findViewById(R.id.remarks_tv);

        //The machine status added to an arraylist
        status_list.add("Repair");
        status_list.add("Scrap");
        status_list.add("Good");



        //Classes
        AshtonDevices devs = new AshtonDevices();
        final GetData getData = new GetData();
        MainPageActivity mainPageActivity = new MainPageActivity();

        //Data from database
        ResultSet department_rs = getData.getDepartments();


        //Process data from database
        try {
            while (department_rs.next()) {
                String department_name = department_rs.getString("DepartmentName");
                department_list.add(department_name);
                dep_id_list.add(department_rs.getString("DepartmeentPk"));

            }
        } catch (Exception ex) {
            Log.e("Error message:", ex.getMessage());
        }




//IP Address spinners-------------------------------------
        for(int i=1; i<=8;i++)
        {
            ip_series.add(i);
        }
        for(int j=10;j<=254;j++)
        {
            ips.add(j);
        }
//-------------------------------------------------------





        dev_list = devs.getDevices();
        dev_id_list= devs.getDeviceID();
        dev_isempidrequired_list = devs.getDeviceNeededstatus();

        user_id = mainPageActivity.PassEID();

//Initialising adapters-----------------------------------------------------------------------------------------------------------------------
        ArrayAdapter device_adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, dev_list);
        ArrayAdapter department_adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_expandable_list_item_1, department_list);
        ArrayAdapter  ip_series_adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, ip_series);
        ArrayAdapter  ips_adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, ips);
        ArrayAdapter  status_adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, status_list);

        ArrayAdapter<String> remarks_adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, likely_remarks);



        device_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ip_series_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ips_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//--------------------------------------------------------------------------------------------------------------------------------------------

      //Setting adapters----------------------------------

        device_adapter.notifyDataSetChanged();
        dev_type_sp.setAdapter(device_adapter);
        status_sp.setAdapter(status_adapter);

        department_adapter.notifyDataSetChanged();
        dep_spinner.setAdapter(department_adapter);

        ip_series_adapter.notifyDataSetChanged();



        ips_adapter.notifyDataSetChanged();

        //--------------------------------------------------------------



        dep_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                emp_id_list.clear();
                username_list.clear();

                selected_dp_pk = Integer.parseInt(dep_id_list.get(position));
                selected_department = department_list.get(position);
                empid_rs = getData.getEmpID(selected_dp_pk);

                try {

                    while (empid_rs.next()) {
                        int empid = empid_rs.getInt("EmpID");
                        String username = empid_rs.getString("Name");
                        int desig_pk = empid_rs.getInt("DesignationPk");
                        int locationpk = empid_rs.getInt("LocationID");

                        emp_id_list.add(empid);
                        username_list.add(username);
                        designation_list.add(desig_pk);
                        location_id_list.add(locationpk);


                    }
                } catch (SQLException se) {
                    Log.e("error here 1 : ", se.getMessage());
                }
                ArrayAdapter dataAdapter = new ArrayAdapter(parent.getContext(), android.R.layout.simple_list_item_1, emp_id_list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                empid_spinner.setAdapter(dataAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //-------------------------------------Device type spinner-------------------------------
        dev_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selected_devicetype = parent.getItemAtPosition(position).toString();
                selected_device_id = Integer.parseInt(dev_id_list.get(position));
                isempid_required_status =  dev_isempidrequired_list.get(position);

                if(isempid_required_status>0)
                {
                    empid_spinner.setEnabled(true);
                    username_view.setEnabled(true);
                    username_lbl.setEnabled(true);
                    empid_tv.setEnabled(true);
                    empid_spinner.setVisibility(View.VISIBLE);
                    username_view.setVisibility(View.VISIBLE);
                    username_lbl.setVisibility(View.VISIBLE);
                    empid_tv.setVisibility(View.VISIBLE);
                }
                else
                {
                    empid_spinner.setVisibility(View.GONE);
                    username_view.setVisibility(View.GONE);
                    username_lbl.setVisibility(View.GONE);
                    empid_tv.setVisibility(View.GONE);
                    username_view.setEnabled(false);
                    empid_spinner.setEnabled(false);
                    username_lbl.setEnabled(false);
                    empid_tv.setEnabled(false);
                    selected_empid = 0;
                    selected_username= null;

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }


        });
        //-------------------------------------------------------------------------------

        //-----------------------------------EmpID type spinner----------------------
        empid_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selected_empid = Integer.parseInt(parent.getItemAtPosition(position).toString());
                selected_username = username_list.get(position);
                designationPk = designation_list.get(position);

                selected_loca_id = location_id_list.get(position);

                username_view.setText(selected_username);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //-------------------------------------------------------------------------------


        //----------------------------------- Status spinner----------------------
        status_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selected_status = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //-----------------------------------IP series spinner----------------------
                   //deleted
        //-------------------------------------------------------------------------------


        //-----------------------------------IP address spinner----------------------
                   //deleted
        //-------------------------------------------------------------------------------


       submit_btn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {


               remarks = remarks_tv.getText().toString();
               TextView error_view = findViewById(R.id.error_tv);

               if(remarks.equals("")) {
                   error_view.setText("Fields cannot be left empty, fill them out.");
                   error_view.setVisibility(View.VISIBLE);
               }
               else
               {
                   error_view.setVisibility(View.GONE);

                   AlertDialog.Builder builder = new AlertDialog.Builder(DeviceAllocation.this);
               builder.setTitle("Confirm Device Details");
               builder.setMessage("Device type:  "+selected_devicetype+"\n\n"+
                                  "Department:   "+selected_department+"\n\n"+
                                  "EmpID:        "+selected_empid+"\n\n"+
                                  "Emp Name:     "+selected_username+"\n\n"+
                                   "Status:      "+selected_status+"\n\n"+
                                   "Remarks:     "+remarks+"\n");
               builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which)
                   {
                        //Do the data submission to database.

                           int insert_data = getData.insertDeviceData(selected_empid, selected_loca_id, selected_dp_pk, selected_status, selected_device_id, user_id, remarks, designationPk);
                           if (insert_data > 0) {
                               AlertDialog.Builder builder_b = new AlertDialog.Builder(DeviceAllocation.this);
                               builder_b.setTitle("Device Allocation");
                               builder_b.setMessage("Device successfully allocated");
                               AlertDialog alert2 = builder_b.create();
                               alert2.show();
                           } else {
                               AlertDialog.Builder builder_b = new AlertDialog.Builder(DeviceAllocation.this);
                               builder_b.setTitle("Device Allocation");
                               builder_b.setMessage("An error occured, device not allocated.");
                               AlertDialog alert2 = builder_b.create();
                               alert2.show();
                           }
                       }


               });
               builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                       /*
                       finish();
                       overridePendingTransition(0, 0);
                       startActivity(getIntent());
                       overridePendingTransition(0, 0);
                       */
                   }
               });

               AlertDialog alert1 = builder.create();
               alert1.show();
           }}
       });




    }

}

package com.apparel.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionEmployeeDeActivationFactoryGMApproval extends AppCompatActivity {


    final ArrayList<String> location_list = new ArrayList();
    final ArrayList<String> locid_list = new ArrayList();


    final List<Integer> dpks = new ArrayList<>();

    int locid;
    String s;



    GetData getData;

    ResultSet loc_set, load_data;
    int id;

    Spinner loc_sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_de_activation_hrapproval);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Deactivation Approval 2");


        //Views
        final TableLayout mTable = findViewById(R.id.contact_table);
        loc_sp = findViewById(R.id.contract_location_spinner);
        final Button rbutton = findViewById(R.id.r_btn);
        final Button abutton = findViewById(R.id.a_btn);
        Button lbtn = findViewById(R.id.load_buttonn);
        Button restbtn = findViewById(R.id.reset_button);


        //Classes
        getData = new GetData();

        id = Integer.parseInt(new LoginActivity().pass_EmpID());


        loc_set = getData.getContractLocation(id);
        try {

            while (loc_set.next()) {
                location_list.add(loc_set.getString("Location"));
                locid_list.add(loc_set.getString("LocationID"));
            }

        } catch (Exception e) {
            Log.e("Error caught: ", e.getMessage());
        }


        ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, location_list);
        NoCoreAdapter.notifyDataSetChanged();
        loc_sp.setAdapter(NoCoreAdapter);


        loc_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // pos = position;
                if (parent.getItemAtPosition(position) != null) {
                    locid = Integer.parseInt(locid_list.get(position));

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), String.valueOf(locid), Toast.LENGTH_SHORT).show();
                load_data = getData.loadDeactivationgmapprovals(locid);


               /* try {
                    if (!load_data.next()) {



                        Toast.makeText(getApplicationContext(), "No contract available, sorry.", Toast.LENGTH_SHORT).show();

                        mTable.setVisibility(View.INVISIBLE);
                        rbutton.setVisibility(View.INVISIBLE);
                        abutton.setVisibility(View.INVISIBLE);



                    }*/

                // else {


                abutton.setVisibility(View.VISIBLE);
                rbutton.setVisibility(View.VISIBLE);
                mTable.setVisibility(View.VISIBLE);

                TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f);
                TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

                textViewParam.setMargins(8, 0, 8, 0);

                mTable.setStretchAllColumns(true);
                mTable.bringToFront();
                mTable.removeAllViews();


                TableRow head_row = new TableRow(TransactionEmployeeDeActivationFactoryGMApproval.this);
                TextView id_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                head_row.setBackgroundColor(Color.parseColor("#2B547E"));
                id_head.setText("ID");

                id_head.setLayoutParams(textViewParam);
                id_head.setGravity(Gravity.CENTER_VERTICAL);
                id_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                id_head.setTypeface(null, Typeface.BOLD);
                id_head.setTextColor(Color.parseColor("#FFFFFF"));


                TextView empid_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                empid_head.setText("EmployeeID");
                empid_head.setLayoutParams(textViewParam);
                empid_head.setGravity(Gravity.CENTER_VERTICAL);
                empid_head.setTypeface(null, Typeface.BOLD);
                empid_head.setTextColor(Color.parseColor("#FFFFFF"));
                empid_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView name_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                name_head.setText("Name");
                name_head.setLayoutParams(textViewParam);
                name_head.setGravity(Gravity.CENTER_VERTICAL);
                name_head.setTypeface(null, Typeface.BOLD);
                name_head.setTextColor(Color.parseColor("#FFFFFF"));
                name_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView desig_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                desig_head.setText("Designation");
                desig_head.setLayoutParams(textViewParam);
                desig_head.setGravity(Gravity.CENTER_VERTICAL);
                desig_head.setTypeface(null, Typeface.BOLD);
                desig_head.setTextColor(Color.parseColor("#FFFFFF"));
                desig_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView depname_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                depname_head.setText("DepartmentName");
                depname_head.setLayoutParams(textViewParam);
                depname_head.setGravity(Gravity.CENTER_VERTICAL);
                depname_head.setTypeface(null, Typeface.BOLD);
                depname_head.setTextColor(Color.parseColor("#FFFFFF"));
                depname_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView dateadded_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                dateadded_head.setText("DateAdded");
                dateadded_head.setLayoutParams(textViewParam);
                dateadded_head.setGravity(Gravity.CENTER_VERTICAL);
                dateadded_head.setTypeface(null, Typeface.BOLD);
                dateadded_head.setTextColor(Color.parseColor("#FFFFFF"));
                dateadded_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView remark_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                remark_head.setText("Remark");
                remark_head.setLayoutParams(textViewParam);
                remark_head.setGravity(Gravity.CENTER_VERTICAL);
                remark_head.setTypeface(null, Typeface.BOLD);
                remark_head.setTextColor(Color.parseColor("#FFFFFF"));
                remark_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                CheckBox checkall_head = new CheckBox(getApplicationContext());
                checkall_head.setText("Select all");
                checkall_head.setLayoutParams(textViewParam);
                checkall_head.setGravity(Gravity.CENTER_VERTICAL);
                checkall_head.setTypeface(null, Typeface.BOLD);
                checkall_head.setTextColor(Color.parseColor("#FFFFFF"));
                checkall_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView islevel1_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                islevel1_head.setText("Islevel1");
                islevel1_head.setLayoutParams(textViewParam);
                islevel1_head.setGravity(Gravity.CENTER_VERTICAL);
                islevel1_head.setTypeface(null, Typeface.BOLD);
                islevel1_head.setTextColor(Color.parseColor("#FFFFFF"));
                islevel1_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView islevel2_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                islevel2_head.setText("Islevel2");
                islevel2_head.setLayoutParams(textViewParam);
                islevel2_head.setGravity(Gravity.CENTER_VERTICAL);
                islevel2_head.setTypeface(null, Typeface.BOLD);
                islevel2_head.setTextColor(Color.parseColor("#FFFFFF"));
                islevel2_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView islevel3_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                islevel3_head.setText("Islevel3");
                islevel3_head.setLayoutParams(textViewParam);
                islevel3_head.setGravity(Gravity.CENTER_VERTICAL);
                islevel3_head.setTypeface(null, Typeface.BOLD);
                islevel3_head.setTextColor(Color.parseColor("#FFFFFF"));
                islevel3_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView isblacklisted_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                isblacklisted_head.setText("IsBlackListed");

                isblacklisted_head.setLayoutParams(textViewParam);
                isblacklisted_head.setGravity(Gravity.CENTER_VERTICAL);
                isblacklisted_head.setTypeface(null, Typeface.BOLD);
                isblacklisted_head.setTextColor(Color.parseColor("#FFFFFF"));
                isblacklisted_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);



                TextView deactpk_head = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                deactpk_head.setText("APPK");

                deactpk_head.setLayoutParams(textViewParam);
                deactpk_head.setGravity(Gravity.CENTER_VERTICAL);
                deactpk_head.setTypeface(null, Typeface.BOLD);
                deactpk_head.setTextColor(Color.parseColor("#FFFFFF"));
                deactpk_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                deactpk_head.setVisibility(View.INVISIBLE);



                head_row.addView(checkall_head);
                head_row.addView(id_head);
                head_row.addView(empid_head);
                head_row.addView(name_head);
                head_row.addView(desig_head);
                head_row.addView(depname_head);
                head_row.addView(dateadded_head);
                head_row.addView(remark_head);
                head_row.addView(islevel1_head);
                head_row.addView(islevel2_head);
                head_row.addView(islevel3_head);
                head_row.addView(isblacklisted_head);
                head_row.addView(deactpk_head);



                //head_row.setBackgroundResource(R.drawable.tableback);
                head_row.setPadding(0, 5, 0, 5);

                //Add head row to the table view
                mTable.addView(head_row);





                //Load data from database in rows
                try {


                    int i = 1;
                    while (load_data.next()) {



                        //Data from the database
                        String empid = load_data.getString("EmpID");
                        String name = load_data.getString("Name");
                        String id = String.valueOf(i);
                        String remark = load_data.getString("Remark");
                        String designation = load_data.getString("Designation");
                        String departmentname = load_data.getString("DepartmentName");
                        String dateadded = load_data.getString("Dateadded");
                        String islevel1 = load_data.getString("Islevel1");
                        String islevel2 = load_data.getString("Islevel2");
                        String islevel3 = load_data.getString("Islevel3");
                        String isblacklisted = load_data.getString("IsBlackListed");
                        String appk = load_data.getString("DeactivationPK");




                        final TableRow data_row = new TableRow(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        TextView id_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        // data_row.setBackgroundColor(Color.parseColor("#2B547E"));
                        id_view.setText(id);

                        id_view.setLayoutParams(textViewParam);
                        id_view.setGravity(Gravity.CENTER_VERTICAL);
                        id_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                        id_view.setTypeface(null, Typeface.BOLD);
                        //id_view.setTextColor(Color.parseColor("#FFFFFF"));


                        TextView empid_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        empid_view.setText(empid);
                        empid_view.setLayoutParams(textViewParam);
                        empid_view.setGravity(Gravity.CENTER_VERTICAL);
                        empid_view.setTypeface(null, Typeface.BOLD);
                        //empid_view.setTextColor(Color.parseColor("#FFFFFF"));
                        empid_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView name_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        name_view.setText(name);
                        name_view.setLayoutParams(textViewParam);
                        name_view.setGravity(Gravity.CENTER_VERTICAL);
                        name_view.setTypeface(null, Typeface.BOLD);
                        // name_view.setTextColor(Color.parseColor("#FFFFFF"));
                        name_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView desig_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        desig_view.setText(designation);
                        desig_view.setLayoutParams(textViewParam);
                        desig_view.setGravity(Gravity.CENTER_VERTICAL);
                        desig_view.setTypeface(null, Typeface.BOLD);
                        desig_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView depname_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        depname_view.setText(departmentname);
                        depname_view.setLayoutParams(textViewParam);
                        depname_view.setGravity(Gravity.CENTER_VERTICAL);
                        depname_view.setTypeface(null, Typeface.BOLD);
                        depname_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView dateadded_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        dateadded_view.setText(dateadded);
                        dateadded_view.setLayoutParams(textViewParam);
                        dateadded_view.setGravity(Gravity.CENTER_VERTICAL);
                        dateadded_view.setTypeface(null, Typeface.BOLD);
                        dateadded_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView remark_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        remark_view.setText(remark);
                        remark_view.setLayoutParams(textViewParam);
                        remark_view.setGravity(Gravity.CENTER_VERTICAL);
                        remark_view.setTypeface(null, Typeface.BOLD);
                        remark_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        final CheckBox datacheck_view = new CheckBox(getApplicationContext());
                        datacheck_view.setLayoutParams(textViewParam);
                        datacheck_view.setGravity(Gravity.CENTER_VERTICAL);
                        datacheck_view.setTypeface(null, Typeface.BOLD);
                        datacheck_view.setTextColor(Color.parseColor("#062101"));

                        datacheck_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView islevel1_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        islevel1_view.setText(islevel1);
                        islevel1_view.setLayoutParams(textViewParam);
                        islevel1_view.setGravity(Gravity.CENTER_VERTICAL);
                        islevel1_view.setTypeface(null, Typeface.BOLD);
                        islevel1_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView islevel2_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        islevel2_view.setText(islevel2);
                        islevel2_view.setLayoutParams(textViewParam);
                        islevel2_view.setGravity(Gravity.CENTER_VERTICAL);
                        islevel2_view.setTypeface(null, Typeface.BOLD);
                        islevel2_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView islevel3_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        islevel3_view.setText(islevel3);
                        islevel3_view.setLayoutParams(textViewParam);
                        islevel3_view.setGravity(Gravity.CENTER_VERTICAL);
                        islevel3_view.setTypeface(null, Typeface.BOLD);
                        islevel3_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView isblacklisted_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        isblacklisted_view.setText(isblacklisted);

                        isblacklisted_view.setLayoutParams(textViewParam);
                        isblacklisted_view.setGravity(Gravity.CENTER_VERTICAL);
                        isblacklisted_view.setTypeface(null, Typeface.BOLD);
                        isblacklisted_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView deactpk_view = new TextView(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        deactpk_view.setText(appk);

                        deactpk_view.setLayoutParams(textViewParam);
                        deactpk_view.setGravity(Gravity.CENTER_VERTICAL);
                        deactpk_view.setTypeface(null, Typeface.BOLD);
                        deactpk_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                        deactpk_view.setVisibility(View.INVISIBLE);


                        data_row.addView(datacheck_view);
                        data_row.addView(id_view);
                        data_row.addView(empid_view);
                        data_row.addView(name_view);
                        data_row.addView(desig_view);
                        data_row.addView(depname_view);
                        data_row.addView(dateadded_view);
                        data_row.addView(remark_view);
                        data_row.addView(islevel1_view);
                        data_row.addView(islevel2_view);
                        data_row.addView(islevel3_view);
                        data_row.addView(isblacklisted_view);
                        data_row.addView(deactpk_view);



                        //data_row.setBackgroundResource(R.drawable.tableback);
                        data_row.setPadding(0, 5, 0, 5);

                        //Adding the data row to the table.
                        mTable.addView(data_row);


                        //Checkbox listener for the selected records
                        datacheck_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                int col_id = data_row.indexOfChild(datacheck_view);
                                int row_id = mTable.indexOfChild(data_row);


                                TableRow row = (TableRow) mTable.getChildAt(row_id);
                                TextView tv = (TextView) row.getChildAt(12);
                                String row_content = tv.getText().toString();


                                if (buttonView.isChecked()) {


                                    dpks.add(Integer.parseInt(row_content));


                                } else
                                {

                                    dpks.remove(dpks.indexOf(Integer.parseInt(row_content)));

                                }
                            }

                        });


                        checkall_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if(compoundButton.isChecked())
                                {

                                    for(int n =1;n<mTable.getChildCount();n++)
                                    {
                                        View child = mTable.getChildAt(n);
                                        TableRow row = (TableRow) child;

                                        View v= row.getChildAt(data_row.indexOfChild(datacheck_view));

                                        CheckBox check = (CheckBox) v;

                                        check.setChecked(true);

                                    }
                                    //Toast.makeText(getApplicationContext(),"Select all checked",Toast.LENGTH_LONG).show();

                                }
                                else {

                                    for (int n = 1; n < mTable.getChildCount(); n++) {
                                        View child = mTable.getChildAt(n);
                                        TableRow row = (TableRow) child;

                                        View v = row.getChildAt(data_row.indexOfChild(datacheck_view));

                                        CheckBox check = (CheckBox) v;

                                        check.setChecked(false);

                                    }
                                }
                            }
                        });


                        i++; //Just an increment for ID
                    }



                } catch (Exception ex) {
                    Log.e("Error caught: ", ex.getMessage());
                }

                loc_sp.setEnabled(false);
                loc_sp.setClickable(false);
            }
            // }

                /*catch (SQLException e) {
                    e.printStackTrace();
                }*/

            //}


        });



        //Approve button listener
        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (dpks.size() != 0)
                {
                    int flag = 0;

                    int co;

                    for (int i = 0; i < dpks.size(); i++)
                    {

                        co= getData.gmDeactivationApprove(dpks.get(i), id);

                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == dpks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        builder.setTitle("Deactivation Approvals");
                        builder.setMessage("Approved!");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                            }
                        });
                    }
                    else if(flag>0 && flag <dpks.size())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        builder.setTitle("Deactivation Approvals");
                        builder.setMessage("Approvals partly done, some failed. Kindly confirm.");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                            }
                        });
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        builder.setTitle("Deactivation Approvals");
                        builder.setMessage("Approval failed. An error occurred.");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                            }
                        });

                    }

                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                    builder.setTitle("Deactivation Approvals");
                    builder.setMessage("Invalid action, no selection made.");
                    AlertDialog alert1 = builder.create();
                    alert1.show();

                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {

                            finish();
                            overridePendingTransition( 0, 0);
                            startActivity(getIntent());
                            overridePendingTransition( 0, 0);

                        }
                    });
                }

            }
        });



        //Reject Button listener
        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (dpks.size() != 0) {
                    int flag = 0;

                    int co;

                    for (int i = 0; i < dpks.size(); i++)
                    {
                        co= getData.gmDeactivationrejecta(dpks.get(i), 2);
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == dpks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        builder.setTitle("Deactivation Approvals");
                        builder.setMessage("Rejected!");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                            }
                        });
                    }
                    else if(flag>0 && flag <dpks.size())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        builder.setTitle("Deactivation Approvals");
                        builder.setMessage("Rejection partly done, some failed. Kindly confirm.");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                            }
                        });
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                        builder.setTitle("Deactivation Approvals");
                        builder.setMessage("Rejection failed. An error occurred.");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                            }
                        });

                    }

                    // Toast.makeText(getApplicationContext(), a , Toast.LENGTH_SHORT).show();

                }



                else
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeDeActivationFactoryGMApproval.this);
                    builder.setTitle("Deactivation Approvals");
                    builder.setMessage("Invalid action, no selection made.");
                    AlertDialog alert1 = builder.create();
                    alert1.show();

                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {

                            finish();
                            overridePendingTransition( 0, 0);
                            startActivity(getIntent());
                            overridePendingTransition( 0, 0);

                        }
                    });
                }
            }

        });



        //Reset button listener
        restbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);

            }
        });

    }
}
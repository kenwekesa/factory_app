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

public class TransactionEmployeeRegistrationContractApproval extends AppCompatActivity {


    final ArrayList<String> location_list = new ArrayList();
    final ArrayList<String> locid_list = new ArrayList();


    final List<Integer> empids = new ArrayList<>();

    int locid;
    String s;

    GetData getData;

    ResultSet loc_set, load_contract;
    int id;

    Spinner loc_sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_approval);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Contract Approval");


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
                load_contract = getData.loadContractpprovals(locid);


               /* try {
                    if (!load_contract.next()) {



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


                TableRow head_row = new TableRow(TransactionEmployeeRegistrationContractApproval.this);
                TextView empid_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                head_row.setBackgroundColor(Color.parseColor("#2B547E"));
                empid_head.setText("EmployeeID");

                empid_head.setLayoutParams(textViewParam);
                empid_head.setGravity(Gravity.CENTER_VERTICAL);
                empid_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                empid_head.setTypeface(null, Typeface.BOLD);
                empid_head.setTextColor(Color.parseColor("#FFFFFF"));


                TextView oldempid_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                oldempid_head.setText("OldEmpID");
                oldempid_head.setLayoutParams(textViewParam);
                oldempid_head.setGravity(Gravity.CENTER_VERTICAL);
                oldempid_head.setTypeface(null, Typeface.BOLD);
                oldempid_head.setTextColor(Color.parseColor("#FFFFFF"));
                oldempid_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView name_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                name_head.setText("Name");
                name_head.setLayoutParams(textViewParam);
                name_head.setGravity(Gravity.CENTER_VERTICAL);
                name_head.setTypeface(null, Typeface.BOLD);
                name_head.setTextColor(Color.parseColor("#FFFFFF"));
                name_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView nid_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                nid_head.setText("NationalID");
                nid_head.setLayoutParams(textViewParam);
                nid_head.setGravity(Gravity.CENTER_VERTICAL);
                nid_head.setTypeface(null, Typeface.BOLD);
                nid_head.setTextColor(Color.parseColor("#FFFFFF"));
                nid_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView gender_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                gender_head.setText("Gender");
                gender_head.setLayoutParams(textViewParam);
                gender_head.setGravity(Gravity.CENTER_VERTICAL);
                gender_head.setTypeface(null, Typeface.BOLD);
                gender_head.setTextColor(Color.parseColor("#FFFFFF"));
                gender_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView category_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                category_head.setText("Category");
                category_head.setLayoutParams(textViewParam);
                category_head.setGravity(Gravity.CENTER_VERTICAL);
                category_head.setTypeface(null, Typeface.BOLD);
                category_head.setTextColor(Color.parseColor("#FFFFFF"));
                category_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView designation_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                designation_head.setText("Designation");
                designation_head.setLayoutParams(textViewParam);
                designation_head.setGravity(Gravity.CENTER_VERTICAL);
                designation_head.setTypeface(null, Typeface.BOLD);
                designation_head.setTextColor(Color.parseColor("#FFFFFF"));
                designation_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                CheckBox check_head = new CheckBox(getApplicationContext());
                check_head.setText("Select all");
                check_head.setLayoutParams(textViewParam);
                check_head.setGravity(Gravity.CENTER_VERTICAL);
                check_head.setTypeface(null, Typeface.BOLD);
                check_head.setTextColor(Color.parseColor("#FFFFFF"));
                check_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView contractstartdate_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                contractstartdate_head.setText("ContractStartDate");
                contractstartdate_head.setLayoutParams(textViewParam);
                contractstartdate_head.setGravity(Gravity.CENTER_VERTICAL);
                contractstartdate_head.setTypeface(null, Typeface.BOLD);
                contractstartdate_head.setTextColor(Color.parseColor("#FFFFFF"));
                contractstartdate_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView contractenddate_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                contractenddate_head.setText("ContractEndDate");
                contractenddate_head.setLayoutParams(textViewParam);
                contractenddate_head.setGravity(Gravity.CENTER_VERTICAL);
                contractenddate_head.setTypeface(null, Typeface.BOLD);
                contractenddate_head.setTextColor(Color.parseColor("#FFFFFF"));
                contractenddate_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView nssf_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                nssf_head.setText("NSSF");
                nssf_head.setLayoutParams(textViewParam);
                nssf_head.setGravity(Gravity.CENTER_VERTICAL);
                nssf_head.setTypeface(null, Typeface.BOLD);
                nssf_head.setTextColor(Color.parseColor("#FFFFFF"));
                nssf_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView nhif_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                nhif_head.setText("NHIF");

                nhif_head.setLayoutParams(textViewParam);
                nhif_head.setGravity(Gravity.CENTER_VERTICAL);
                nhif_head.setTypeface(null, Typeface.BOLD);
                nhif_head.setTextColor(Color.parseColor("#FFFFFF"));
                nhif_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView kra_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                kra_head.setText("KRA PIN");

                kra_head.setLayoutParams(textViewParam);
                kra_head.setGravity(Gravity.CENTER_VERTICAL);
                kra_head.setTypeface(null, Typeface.BOLD);
                kra_head.setTextColor(Color.parseColor("#FFFFFF"));
                kra_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView bankname_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                bankname_head.setText("BANK Name");

                bankname_head.setLayoutParams(textViewParam);
                bankname_head.setGravity(Gravity.CENTER_VERTICAL);
                bankname_head.setTypeface(null, Typeface.BOLD);
                bankname_head.setTextColor(Color.parseColor("#FFFFFF"));
                bankname_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView accountnum_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                accountnum_head.setText("AccountNum");

                accountnum_head.setLayoutParams(textViewParam);
                accountnum_head.setGravity(Gravity.CENTER_VERTICAL);
                accountnum_head.setTypeface(null, Typeface.BOLD);
                accountnum_head.setTextColor(Color.parseColor("#FFFFFF"));
                accountnum_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView basic_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                basic_head.setText("Basic");

                basic_head.setLayoutParams(textViewParam);
                basic_head.setGravity(Gravity.CENTER_VERTICAL);
                basic_head.setTypeface(null, Typeface.BOLD);
                basic_head.setTextColor(Color.parseColor("#FFFFFF"));
                basic_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView hra_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                hra_head.setText("HRA");

                hra_head.setLayoutParams(textViewParam);
                hra_head.setGravity(Gravity.CENTER_VERTICAL);
                hra_head.setTypeface(null, Typeface.BOLD);
                hra_head.setTextColor(Color.parseColor("#FFFFFF"));
                hra_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView special_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                special_head.setText("Special");

                special_head.setLayoutParams(textViewParam);
                special_head.setGravity(Gravity.CENTER_VERTICAL);
                special_head.setTypeface(null, Typeface.BOLD);
                special_head.setTextColor(Color.parseColor("#FFFFFF"));
                special_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView gross_head = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                gross_head.setText("Gross");

                gross_head.setLayoutParams(textViewParam);
                gross_head.setGravity(Gravity.CENTER_VERTICAL);
                gross_head.setTypeface(null, Typeface.BOLD);
                gross_head.setTextColor(Color.parseColor("#FFFFFF"));
                gross_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                head_row.addView(check_head);
                head_row.addView(empid_head);
                head_row.addView(oldempid_head);
                head_row.addView(name_head);
                head_row.addView(nid_head);
                head_row.addView(gender_head);
                head_row.addView(category_head);
                head_row.addView(designation_head);
                head_row.addView(contractstartdate_head);
                head_row.addView(contractenddate_head);
                head_row.addView(nssf_head);
                head_row.addView(nhif_head);
                head_row.addView(kra_head);
                head_row.addView(bankname_head);
                head_row.addView(accountnum_head);
                head_row.addView(basic_head);
                head_row.addView(hra_head);
                head_row.addView(special_head);
                head_row.addView(gross_head);


                //head_row.setBackgroundResource(R.drawable.tableback);
                head_row.setPadding(0, 5, 0, 5);

                mTable.addView(head_row);


                try {


                    while (load_contract.next()) {


                        //Data from the database
                        String empid = load_contract.getString("EmpID");
                        String olempid = load_contract.getString("OldEmpID");
                        String name = load_contract.getString("Name");
                        String nid = load_contract.getString("NationalId");
                        String gender = load_contract.getString("Gender");
                        String category = load_contract.getString("Category");
                        String designation = load_contract.getString("Designation");
                        String departmentname = load_contract.getString("DepartmentName");
                        String contractstartdate = load_contract.getString("ContractStartDate");
                        String contractenddate = load_contract.getString("ContractEndDate");
                        String nssf = load_contract.getString("NSSF");
                        String nhif = load_contract.getString("NHIF");
                        String kra = load_contract.getString("KRAPIN");
                        String bankname = load_contract.getString("BankName");
                        String accountnum = load_contract.getString("AccountNum");
                        String basic = load_contract.getString("Basic");
                        String hra = load_contract.getString("HRA");
                        String special = load_contract.getString("Special");
                        String gross = load_contract.getString("Gross");


                        final TableRow data_row = new TableRow(TransactionEmployeeRegistrationContractApproval.this);
                        final TextView empid_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        // data_row.setBackgroundColor(Color.parseColor("#2B547E"));
                        empid_view.setText(empid);

                        empid_view.setLayoutParams(textViewParam);
                        empid_view.setGravity(Gravity.CENTER_VERTICAL);
                        empid_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                        empid_view.setTypeface(null, Typeface.BOLD);
                        //empid_view.setTextColor(Color.parseColor("#FFFFFF"));


                        TextView olempid_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        olempid_view.setText(olempid);
                        olempid_view.setLayoutParams(textViewParam);
                        olempid_view.setGravity(Gravity.CENTER_VERTICAL);
                        olempid_view.setTypeface(null, Typeface.BOLD);
                        //olempid_view.setTextColor(Color.parseColor("#FFFFFF"));
                        olempid_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView name_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        name_view.setText(name);
                        name_view.setLayoutParams(textViewParam);
                        name_view.setGravity(Gravity.CENTER_VERTICAL);
                        name_view.setTypeface(null, Typeface.BOLD);
                        // name_view.setTextColor(Color.parseColor("#FFFFFF"));
                        name_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView nid_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        nid_view.setText(nid);
                        nid_view.setLayoutParams(textViewParam);
                        nid_view.setGravity(Gravity.CENTER_VERTICAL);
                        nid_view.setTypeface(null, Typeface.BOLD);
                        nid_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView gender_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        gender_view.setText(gender);
                        gender_view.setLayoutParams(textViewParam);
                        gender_view.setGravity(Gravity.CENTER_VERTICAL);
                        gender_view.setTypeface(null, Typeface.BOLD);
                        gender_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView category_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        category_view.setText(category);
                        category_view.setLayoutParams(textViewParam);
                        category_view.setGravity(Gravity.CENTER_VERTICAL);
                        category_view.setTypeface(null, Typeface.BOLD);
                        category_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView designation_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        designation_view.setText(designation);
                        designation_view.setLayoutParams(textViewParam);
                        designation_view.setGravity(Gravity.CENTER_VERTICAL);
                        designation_view.setTypeface(null, Typeface.BOLD);
                        designation_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        final CheckBox datacheckview_view = new CheckBox(getApplicationContext());
                        datacheckview_view.setLayoutParams(textViewParam);
                        datacheckview_view.setGravity(Gravity.CENTER_VERTICAL);
                        datacheckview_view.setTypeface(null, Typeface.BOLD);
                        datacheckview_view.setTextColor(Color.parseColor("#062101"));

                        datacheckview_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView contractstartdate_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        contractstartdate_view.setText(contractstartdate);
                        contractstartdate_view.setLayoutParams(textViewParam);
                        contractstartdate_view.setGravity(Gravity.CENTER_VERTICAL);
                        contractstartdate_view.setTypeface(null, Typeface.BOLD);
                        contractstartdate_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView contractenddate_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        contractenddate_view.setText(contractenddate);
                        contractenddate_view.setLayoutParams(textViewParam);
                        contractenddate_view.setGravity(Gravity.CENTER_VERTICAL);
                        contractenddate_view.setTypeface(null, Typeface.BOLD);
                        contractenddate_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView nssf_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        nssf_view.setText(nssf);
                        nssf_view.setLayoutParams(textViewParam);
                        nssf_view.setGravity(Gravity.CENTER_VERTICAL);
                        nssf_view.setTypeface(null, Typeface.BOLD);
                        nssf_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView nhif_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        nhif_view.setText(nhif);

                        nhif_view.setLayoutParams(textViewParam);
                        nhif_view.setGravity(Gravity.CENTER_VERTICAL);
                        nhif_view.setTypeface(null, Typeface.BOLD);
                        nhif_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView kra_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        kra_view.setText(kra);

                        kra_view.setLayoutParams(textViewParam);
                        kra_view.setGravity(Gravity.CENTER_VERTICAL);
                        kra_view.setTypeface(null, Typeface.BOLD);
                        kra_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView bankname_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        bankname_view.setText(bankname);

                        bankname_view.setLayoutParams(textViewParam);
                        bankname_view.setGravity(Gravity.CENTER_VERTICAL);
                        bankname_view.setTypeface(null, Typeface.BOLD);
                        bankname_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                        TextView accountnum_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        accountnum_view.setText(accountnum);

                        accountnum_view.setLayoutParams(textViewParam);
                        accountnum_view.setGravity(Gravity.CENTER_VERTICAL);
                        accountnum_view.setTypeface(null, Typeface.BOLD);
                        accountnum_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView basic_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        basic_view.setText(basic);

                        basic_view.setLayoutParams(textViewParam);
                        basic_view.setGravity(Gravity.CENTER_VERTICAL);
                        basic_view.setTypeface(null, Typeface.BOLD);
                        basic_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView hra_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        hra_view.setText(hra);

                        hra_view.setLayoutParams(textViewParam);
                        hra_view.setGravity(Gravity.CENTER_VERTICAL);
                        hra_view.setTypeface(null, Typeface.BOLD);
                        hra_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView special_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        special_view.setText(special);

                        special_view.setLayoutParams(textViewParam);
                        special_view.setGravity(Gravity.CENTER_VERTICAL);
                        special_view.setTypeface(null, Typeface.BOLD);
                        special_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        TextView gross_view = new TextView(TransactionEmployeeRegistrationContractApproval.this);
                        gross_view.setText(gross);

                        gross_view.setLayoutParams(textViewParam);
                        gross_view.setGravity(Gravity.CENTER_VERTICAL);
                        gross_view.setTypeface(null, Typeface.BOLD);
                        gross_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                        data_row.addView(datacheckview_view);
                        data_row.addView(empid_view);
                        data_row.addView(olempid_view);
                        data_row.addView(name_view);
                        data_row.addView(nid_view);
                        data_row.addView(gender_view);
                        data_row.addView(category_view);
                        data_row.addView(designation_view);
                        data_row.addView(contractstartdate_view);
                        data_row.addView(contractenddate_view);
                        data_row.addView(nssf_view);
                        data_row.addView(nhif_view);
                        data_row.addView(kra_view);
                        data_row.addView(bankname_view);
                        data_row.addView(accountnum_view);
                        data_row.addView(basic_view);
                        data_row.addView(hra_view);
                        data_row.addView(special_view);
                        data_row.addView(gross_view);


                        //data_row.setBackgroundResource(R.drawable.tableback);
                        data_row.setPadding(0, 5, 0, 5);

                        mTable.addView(data_row);


                        datacheckview_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                int col_id = data_row.indexOfChild(empid_view);
                                int row_id = mTable.indexOfChild(data_row);


                                TableRow row = (TableRow) mTable.getChildAt(row_id);
                                TextView data_tv = (TextView) row.getChildAt(col_id);
                                String row_content = data_tv.getText().toString();


                                if (buttonView.isChecked()) {


                                    empids.add(Integer.parseInt(row_content));

                                } else {

                                    empids.remove(empids.indexOf(Integer.parseInt(row_content)));

                                }
                            }

                        });


                        check_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if(compoundButton.isChecked())
                                {

                                    for(int n =1;n<mTable.getChildCount();n++)
                                    {
                                        View child = mTable.getChildAt(n);
                                        TableRow row = (TableRow) child;

                                        View v= row.getChildAt(data_row.indexOfChild(datacheckview_view));

                                        CheckBox check = (CheckBox) v;

                                        check.setChecked(true);

                                    }
                                    //Toast.makeText(getApplicationContext(),"Select all checked",Toast.LENGTH_LONG).show();

                                }
                                else {

                                    for (int n = 1; n < mTable.getChildCount(); n++) {
                                        View child = mTable.getChildAt(n);
                                        TableRow row = (TableRow) child;

                                        View v = row.getChildAt(data_row.indexOfChild(datacheckview_view));

                                        CheckBox check = (CheckBox) v;

                                        check.setChecked(false);

                                    }
                                }
                            }
                        });
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

        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (empids.size() != 0) {
                    int flag = 0;

                    int co;

                    for (int i = 0; i < empids.size(); i++) {

                        co= getData.contractApprove(empids.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == empids.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                        builder.setTitle("Contract Approvals");
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
                    else if(flag>0 && flag <empids.size())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                        builder.setTitle("Contract Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                        builder.setTitle("Contract Approvals");
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
                    Toast.makeText(getApplicationContext(), "Invalid action, no selection made!", Toast.LENGTH_SHORT).show();

                }

            }
        });





        //Reject Buttonlistener
        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (empids.size() != 0) {
                    int flag = 0;

                    int co;

                    for (int i = 0; i < empids.size(); i++) {

                        co= getData.contractrejecta(empids.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == empids.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                        builder.setTitle("Contract Approvals");
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
                    else if(flag>0 && flag <empids.size())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                        builder.setTitle("Contract Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                        builder.setTitle("Contract Approvals");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRegistrationContractApproval.this);
                    builder.setTitle("Contract Approvals");
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


        restbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);

            }
        }
        );

    }
}
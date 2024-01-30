package com.apparel.myapplication;

import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReinStateEmployee extends AppCompatActivity {

    final List<Integer> empids = new ArrayList<>();
    final List<Integer> dpks = new ArrayList<>();



    TableLayout mTable;
    GetData getData;
    ConnectionVariables con;

    int search_id;

    int login_id;

    EditText id_box;
    TextView error_view;

    Button activate_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rein_state_employee);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.setTitle("ReInstatements");


        login_id = Integer.parseInt(new LoginActivity().pass_EmpID());


        //Classes
        getData = new GetData();

        //Views
        final Button by_nid = findViewById(R.id.search_NID);
        final Button by_empid = findViewById(R.id.search_EID);
        Button reset_btn = findViewById(R.id.reset_btn);
        activate_btn = findViewById(R.id.activate_btn);

        mTable = findViewById(R.id.content_table);


         id_box = findViewById(R.id.id_box);
         error_view = findViewById(R.id.search_error_view);


//Search by  national ID button listener
        by_nid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_box.getText().toString().equals(""))
                {
                    error_view.setText("No ID to search, ensure you enter the ID first.");
                    error_view.setVisibility(View.VISIBLE);
                    mTable.setVisibility(View.GONE);
                    activate_btn.setVisibility(View.GONE);
                }
                else
                {

                    error_view.setVisibility(View.GONE);

                    mTable.setVisibility(View.VISIBLE);
                    activate_btn.setVisibility(View.VISIBLE);

                    search_id = Integer.parseInt(id_box.getText().toString());
                    activate_btn.setVisibility(View.VISIBLE);
                    tableview(search_id, "NID");

                    id_box.setEnabled(false);
                    id_box.setClickable(false);
                    by_empid.setEnabled(false);
                    by_empid.setClickable(false);

                    by_nid.setEnabled(false);
                    by_nid.setClickable(false);
                }
            }
        });


        //Search by Employee ID button listener
        by_empid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_box.getText().toString().equals(""))
                {
                    error_view.setText("No ID to search, ensure you enter the ID first.");
                    error_view.setVisibility(View.VISIBLE);
                    mTable.setVisibility(View.GONE);
                    activate_btn.setVisibility(View.GONE);
                }
                else
                {
                    error_view.setVisibility(View.GONE);
                    mTable.setVisibility(View.VISIBLE);
                    activate_btn.setVisibility(View.VISIBLE);

                    search_id = Integer.parseInt(id_box.getText().toString());
                    activate_btn.setVisibility(View.VISIBLE);
                    tableview(search_id, "EMPID");


                    id_box.setEnabled(false);
                    id_box.setClickable(false);
                    by_empid.setEnabled(false);
                    by_empid.setClickable(false);

                    by_nid.setEnabled(false);
                    by_nid.setClickable(false);
                }

            }
        });

        //The resetButtonListener
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);
            }
        });

//The activate button listener
        activate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (empids.size() > 0) {
                    ResultSet rs;
                    rs = getData.validateReinstatement(empids.get(0));

                    int rows_count = 0;
                    try {
                        while (rs.next()) {
                            rows_count++;
                        }
                    } catch (Exception ex) {

                    }

                    if (rows_count > 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReinStateEmployee.this);
                        builder.setTitle("Reactivation says...");
                        builder.setMessage("This employee cannot be reactivated.");
                        AlertDialog alert1 = builder.create();
                        alert1.show();

                        alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {

                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);

                            }
                        });
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(ReinStateEmployee.this);
                        builder.setTitle("Reactivation says...");
                        builder.setMessage("Employee can be reactivated, do you want to proceed?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                int reist_num;
                                reist_num = getData.reactivateEmployee(empids.get(0), dpks.get(0), login_id);

                                if (reist_num > 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ReinStateEmployee.this);
                                    builder.setTitle("Reactivation says...");
                                    builder.setMessage("Employee successfully reactivated.");
                                    AlertDialog alert1 = builder.create();
                                    alert1.show();

                                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                        @Override
                                        public void onCancel(DialogInterface dialog) {

                                            finish();
                                            overridePendingTransition(0, 0);
                                            startActivity(getIntent());
                                            overridePendingTransition(0, 0);

                                        }
                                    });
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ReinStateEmployee.this);
                                    builder.setTitle("Reactivation says...");
                                    builder.setMessage("Reactivation failed. An error occured!");
                                    AlertDialog alert1 = builder.create();
                                    alert1.show();

                                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                        @Override
                                        public void onCancel(DialogInterface dialog) {

                                            finish();
                                            overridePendingTransition(0, 0);
                                            startActivity(getIntent());
                                            overridePendingTransition(0, 0);

                                        }
                                    });
                                }


                            }
                        });
                        //Option to cancel the activation
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                        });

                        AlertDialog alert1 = builder.create();
                        alert1.show();


                    }

                    //Getting the result size


                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReinStateEmployee.this);
                    builder.setTitle("Reactivation says...");
                    builder.setMessage("Error, ensure you select a least one record!");
                    AlertDialog alert1 = builder.create();
                    alert1.show();

                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {

                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);

                        }
                    });
                }
            }

        });

    }


    public void tableview(int search_id, String search_type) {
        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

        textViewParam.setMargins(8, 0, 8, 0);

        mTable.setStretchAllColumns(true);
        mTable.bringToFront();
        mTable.removeAllViews();


        TableRow head_row = new TableRow(ReinStateEmployee.this);
        TextView id_head = new TextView(ReinStateEmployee.this);
        head_row.setBackgroundColor(Color.parseColor("#2B547E"));
        id_head.setText("ID");

        id_head.setLayoutParams(textViewParam);
        id_head.setGravity(Gravity.CENTER_VERTICAL);
        id_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        id_head.setTypeface(null, Typeface.BOLD);
        id_head.setTextColor(Color.parseColor("#FFFFFF"));


        TextView empid_head = new TextView(ReinStateEmployee.this);
        empid_head.setText("EmployeeID");
        empid_head.setLayoutParams(textViewParam);
        empid_head.setGravity(Gravity.CENTER_VERTICAL);
        empid_head.setTypeface(null, Typeface.BOLD);
        empid_head.setTextColor(Color.parseColor("#FFFFFF"));
        empid_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        TextView name_head = new TextView(ReinStateEmployee.this);
        name_head.setText("Name");
        name_head.setLayoutParams(textViewParam);
        name_head.setGravity(Gravity.CENTER_VERTICAL);
        name_head.setTypeface(null, Typeface.BOLD);
        name_head.setTextColor(Color.parseColor("#FFFFFF"));
        name_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


        TextView dep_head = new TextView(ReinStateEmployee.this);
        dep_head.setText("Department");
        dep_head.setLayoutParams(textViewParam);
        dep_head.setGravity(Gravity.CENTER_VERTICAL);
        dep_head.setTypeface(null, Typeface.BOLD);
        dep_head.setTextColor(Color.parseColor("#FFFFFF"));
        dep_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        TextView desig_head = new TextView(ReinStateEmployee.this);
        desig_head.setText("Designation");
        desig_head.setLayoutParams(textViewParam);
        desig_head.setGravity(Gravity.CENTER_VERTICAL);
        desig_head.setTypeface(null, Typeface.BOLD);
        desig_head.setTextColor(Color.parseColor("#FFFFFF"));
        desig_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


        TextView subdep_head = new TextView(ReinStateEmployee.this);
        subdep_head.setText("SubDepartment");
        subdep_head.setLayoutParams(textViewParam);
        subdep_head.setGravity(Gravity.CENTER_VERTICAL);
        subdep_head.setTypeface(null, Typeface.BOLD);
        subdep_head.setTextColor(Color.parseColor("#FFFFFF"));
        subdep_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


        TextView isreemployable_head = new TextView(ReinStateEmployee.this);
        isreemployable_head.setText("Isreemployable");
        isreemployable_head.setLayoutParams(textViewParam);
        isreemployable_head.setGravity(Gravity.CENTER_VERTICAL);
        isreemployable_head.setTypeface(null, Typeface.BOLD);
        isreemployable_head.setTextColor(Color.parseColor("#FFFFFF"));
        isreemployable_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        CheckBox check_head = new CheckBox(getApplicationContext());
        check_head.setText("Select all");
        check_head.setLayoutParams(textViewParam);
        check_head.setGravity(Gravity.CENTER_VERTICAL);
        check_head.setTypeface(null, Typeface.BOLD);
        check_head.setTextColor(Color.parseColor("#FFFFFF"));
        check_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        TextView remark_head = new TextView(ReinStateEmployee.this);
        remark_head.setText("Remark");
        remark_head.setLayoutParams(textViewParam);
        remark_head.setGravity(Gravity.CENTER_VERTICAL);
        remark_head.setTypeface(null, Typeface.BOLD);
        remark_head.setTextColor(Color.parseColor("#FFFFFF"));
        remark_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        TextView dateadded_head = new TextView(ReinStateEmployee.this);
        dateadded_head.setText("DateAdded");
        dateadded_head.setLayoutParams(textViewParam);
        dateadded_head.setGravity(Gravity.CENTER_VERTICAL);
        dateadded_head.setTypeface(null, Typeface.BOLD);
        dateadded_head.setTextColor(Color.parseColor("#FFFFFF"));
        dateadded_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        TextView level3date_head = new TextView(ReinStateEmployee.this);
        level3date_head.setText("Level3Date");
        level3date_head.setLayoutParams(textViewParam);
        level3date_head.setGravity(Gravity.CENTER_VERTICAL);
        level3date_head.setTypeface(null, Typeface.BOLD);
        level3date_head.setTextColor(Color.parseColor("#FFFFFF"));
        level3date_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        TextView blacklist_head = new TextView(ReinStateEmployee.this);
        blacklist_head.setText("IsBlackListed");

        blacklist_head.setLayoutParams(textViewParam);
        blacklist_head.setGravity(Gravity.CENTER_VERTICAL);
        blacklist_head.setTypeface(null, Typeface.BOLD);
        blacklist_head.setTextColor(Color.parseColor("#FFFFFF"));
        blacklist_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


        TextView deactpk_head = new TextView(ReinStateEmployee.this);
        deactpk_head.setText("APPK");

        deactpk_head.setLayoutParams(textViewParam);
        deactpk_head.setGravity(Gravity.CENTER_VERTICAL);
        deactpk_head.setTypeface(null, Typeface.BOLD);
        deactpk_head.setTextColor(Color.parseColor("#FFFFFF"));
        deactpk_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        deactpk_head.setVisibility(View.INVISIBLE);

        TextView cause_head = new TextView(ReinStateEmployee.this);
        cause_head.setText("Cause");

        cause_head.setLayoutParams(textViewParam);
        cause_head.setGravity(Gravity.CENTER_VERTICAL);
        cause_head.setTypeface(null, Typeface.BOLD);
        cause_head.setTextColor(Color.parseColor("#FFFFFF"));
        cause_head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


        head_row.addView(check_head);
        head_row.addView(id_head);
        head_row.addView(empid_head);
        head_row.addView(name_head);
        head_row.addView(dep_head);
        head_row.addView(desig_head);
        head_row.addView(subdep_head);
        head_row.addView(isreemployable_head);
        head_row.addView(remark_head);
        head_row.addView(dateadded_head);
        head_row.addView(level3date_head);
        head_row.addView(blacklist_head);
        head_row.addView(cause_head);
        head_row.addView(deactpk_head);


        //head_row.setBackgroundResource(R.drawable.tableback);
        head_row.setPadding(0, 5, 0, 5);

        mTable.addView(head_row);


        ResultSet rs = getData.loadEmployeeReinstate(search_id, search_type);

        try {


            int i = 1;
            while (rs.next()) {



                String empid =rs.getString("EmpID");
                String name = rs.getString("Name");
                String id = String.valueOf(i);
                String remark = rs.getString("Remark");
                String designation = rs.getString("Designation");
                String departmentname = rs.getString("DepartmentName");
                String dateadded = rs.getString("Dateadded");
                String isreemployable = rs.getString("Isreemployable");
                String subdep = rs.getString("SubDept");
                String level3date = rs.getString("Level3Date");
                String isblacklisted = rs.getString("IsBlackListed");
                String dpk = rs.getString("DeactivationPK");
                String cause = rs.getString("Cause");


                i++;  //Incremental value for the ID column





                final TableRow data_row = new TableRow(ReinStateEmployee.this);
                final TextView id_view = new TextView(ReinStateEmployee.this);
                id_view.setText(id);

                id_view.setLayoutParams(textViewParam);
                id_view.setGravity(Gravity.CENTER_VERTICAL);
                id_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                id_view.setTypeface(null, Typeface.BOLD);


                final TextView empid_view = new TextView(ReinStateEmployee.this);
                empid_view.setText(empid);
                empid_view.setLayoutParams(textViewParam);
                empid_view.setGravity(Gravity.CENTER_VERTICAL);
                empid_view.setTypeface(null, Typeface.BOLD);
                empid_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView name_view = new TextView(ReinStateEmployee.this);
                name_view.setText(name);
                name_view.setLayoutParams(textViewParam);
                name_view.setGravity(Gravity.CENTER_VERTICAL);
                name_view.setTypeface(null, Typeface.BOLD);
                name_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView dep_view = new TextView(ReinStateEmployee.this);
                dep_view.setText(departmentname);
                dep_view.setLayoutParams(textViewParam);
                dep_view.setGravity(Gravity.CENTER_VERTICAL);
                dep_view.setTypeface(null, Typeface.BOLD);
                dep_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView desig_view = new TextView(ReinStateEmployee.this);
                desig_view.setText(designation);
                desig_view.setLayoutParams(textViewParam);
                desig_view.setGravity(Gravity.CENTER_VERTICAL);
                desig_view.setTypeface(null, Typeface.BOLD);
                desig_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView subdep_view = new TextView(ReinStateEmployee.this);
                subdep_view.setText(subdep);
                subdep_view.setLayoutParams(textViewParam);
                subdep_view.setGravity(Gravity.CENTER_VERTICAL);
                subdep_view.setTypeface(null, Typeface.BOLD);
                subdep_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                TextView isreemployable_view = new TextView(ReinStateEmployee.this);
                isreemployable_view.setText(isreemployable);
                isreemployable_view.setLayoutParams(textViewParam);
                isreemployable_view.setGravity(Gravity.CENTER_VERTICAL);
                isreemployable_view.setTypeface(null, Typeface.BOLD);
                isreemployable_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                CheckBox data_check = new CheckBox(getApplicationContext());
                data_check.setLayoutParams(textViewParam);
                data_check.setGravity(Gravity.CENTER_VERTICAL);
                data_check.setTypeface(null, Typeface.BOLD);
                data_check.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView remark_view = new TextView(ReinStateEmployee.this);
                remark_view.setText(remark);
                remark_view.setLayoutParams(textViewParam);
                remark_view.setGravity(Gravity.CENTER_VERTICAL);
                remark_view.setTypeface(null, Typeface.BOLD);
                remark_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView dateadded_view = new TextView(ReinStateEmployee.this);
                dateadded_view.setText(dateadded);
                dateadded_view.setLayoutParams(textViewParam);
                dateadded_view.setGravity(Gravity.CENTER_VERTICAL);
                dateadded_view.setTypeface(null, Typeface.BOLD);
                dateadded_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView level3date_view = new TextView(ReinStateEmployee.this);
                level3date_view.setText(level3date);
                level3date_view.setLayoutParams(textViewParam);
                level3date_view.setGravity(Gravity.CENTER_VERTICAL);
                level3date_view.setTypeface(null, Typeface.BOLD);
                level3date_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                TextView blacklist_view = new TextView(ReinStateEmployee.this);
                blacklist_view.setText(isblacklisted);

                blacklist_view.setLayoutParams(textViewParam);
                blacklist_view.setGravity(Gravity.CENTER_VERTICAL);
                blacklist_view.setTypeface(null, Typeface.BOLD);
                blacklist_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                final TextView deactpk_view = new TextView(ReinStateEmployee.this);
                deactpk_view.setText(dpk);

                deactpk_view.setLayoutParams(textViewParam);
                deactpk_view.setGravity(Gravity.CENTER_VERTICAL);
                deactpk_view.setTypeface(null, Typeface.BOLD);
                deactpk_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                deactpk_view.setVisibility(View.INVISIBLE);

                TextView cause_view = new TextView(ReinStateEmployee.this);
                cause_view.setText(cause);

                cause_view.setLayoutParams(textViewParam);
                cause_view.setGravity(Gravity.CENTER_VERTICAL);
                cause_view.setTypeface(null, Typeface.BOLD);
                cause_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);


                data_row.addView(data_check);
                data_row.addView(id_view);
                data_row.addView(empid_view);
                data_row.addView(name_view);
                data_row.addView(dep_view);
                data_row.addView(desig_view);
                data_row.addView(subdep_view);
                data_row.addView(isreemployable_view);
                data_row.addView(remark_view);
                data_row.addView(dateadded_view);
                data_row.addView(level3date_view);
                data_row.addView(blacklist_view);
                data_row.addView(cause_view);
                data_row.addView(deactpk_view);


                //data_row.setBackgroundResource(R.drawable.tableback);
                data_row.setPadding(0, 5, 0, 5);

                mTable.addView(data_row);

                data_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        int row_id = mTable.indexOfChild(data_row);
                        int col_empID_id = data_row.indexOfChild(empid_view);
                        int col_dpk_id   = data_row.indexOfChild(deactpk_view);



                        TableRow row = (TableRow) mTable.getChildAt(row_id);
                        TextView tv = (TextView) row.getChildAt(col_empID_id);
                        TextView dpk_tv = (TextView) row.getChildAt(col_dpk_id);
                        String row_content = tv.getText().toString();
                        String dpk_content= dpk_tv.getText().toString();

                        if (buttonView.isChecked()) {

                            empids.add(Integer.parseInt(row_content));
                            dpks.add(Integer.parseInt(dpk_content));

                        } else
                        {

                            empids.remove(empids.indexOf(Integer.parseInt(row_content)));
                            dpks.remove(dpks.indexOf(Integer.parseInt(dpk_content)));

                        }
                    }

                });

            }
        } catch (Exception ex) {
            Log.e("Error caught:", ex.getMessage());
        }

    }
}

package com.apparel.myapplication;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionEmployeeRecruitementFactoryHRApproval extends AppCompatActivity {

    static int location_id, eid;
    static String user_type;
    GetData getData;
    private boolean all_flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_gm__approval);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.setTitle("Factory HR Approval");




        location_id = new MainPageActivity().passLocationID();

        user_type= new MainPageActivity().passUserType();

        eid = new MainPageActivity().PassEID();


        final List<Integer> appks = new ArrayList<>();



        //Initialise other classes
        getData = new GetData();



        final TableLayout hrapprovals= findViewById(R.id.gm_approval_table);

        Button a_btn = findViewById(R.id.approve_btn);
        Button r_btn = findViewById(R.id.reject_btn);


        ResultSet rst = getData.hrApprovals(user_type,location_id);


        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

        hrapprovals.setStretchAllColumns(true);
        hrapprovals.bringToFront();
        hrapprovals.removeAllViews();



        final TableRow head_row = new TableRow(TransactionEmployeeRecruitementFactoryHRApproval.this);
        TextView apppk_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        head_row.setBackgroundColor(Color.parseColor("#2B547E"));
        apppk_head.setText("AppPk");

        apppk_head.setLayoutParams(textViewParam);
        apppk_head.setGravity(Gravity.CENTER);
        apppk_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        apppk_head.setTypeface(null, Typeface.BOLD);
        apppk_head.setTextColor(Color.parseColor("#FFFFFF"));



        TextView appNo_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        appNo_head.setText("AppNo");
        appNo_head.setLayoutParams(textViewParam);
        appNo_head.setGravity(Gravity.CENTER);
        appNo_head.setTypeface(null, Typeface.BOLD);
        appNo_head.setTextColor(Color.parseColor("#FFFFFF"));
        appNo_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView branchlocname_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        branchlocname_head.setText("BranchLocationName");
        branchlocname_head.setLayoutParams(textViewParam);
        branchlocname_head.setGravity(Gravity.CENTER);
        branchlocname_head.setTypeface(null, Typeface.BOLD);
        branchlocname_head.setTextColor(Color.parseColor("#FFFFFF"));
        branchlocname_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView designationname_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        designationname_head.setText("DesignationName");
        designationname_head.setLayoutParams(textViewParam);
        designationname_head.setGravity(Gravity.CENTER);
        designationname_head.setTypeface(null, Typeface.BOLD);
        designationname_head.setTextColor(Color.parseColor("#FFFFFF"));
        designationname_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView type_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        type_head.setText("Type");
        type_head.setLayoutParams(textViewParam);
        type_head.setGravity(Gravity.CENTER);
        type_head.setTypeface(null, Typeface.BOLD);
        type_head.setTextColor(Color.parseColor("#FFFFFF"));
        type_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView vacancies_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        vacancies_head.setText("Vacancies");
        vacancies_head.setLayoutParams(textViewParam);
        vacancies_head.setGravity(Gravity.CENTER);
        vacancies_head.setTypeface(null, Typeface.BOLD);
        vacancies_head.setTextColor(Color.parseColor("#FFFFFF"));
        vacancies_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView expectedjoiningdate_head = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
        expectedjoiningdate_head.setText("ExpectedJoiningDate");
        expectedjoiningdate_head.setLayoutParams(textViewParam);
        expectedjoiningdate_head.setGravity(Gravity.CENTER);
        expectedjoiningdate_head.setTypeface(null, Typeface.BOLD);
        expectedjoiningdate_head.setTextColor(Color.parseColor("#FFFFFF"));
        expectedjoiningdate_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

       final CheckBox selectallchck_head = new CheckBox(this);
        selectallchck_head.setText("Select All");
        selectallchck_head.setLayoutParams(textViewParam);
        selectallchck_head.setGravity(Gravity.CENTER);
        selectallchck_head.setTypeface(null, Typeface.BOLD);
        selectallchck_head.setTextColor(Color.parseColor("#FFFFFF"));
        selectallchck_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        head_row.addView(apppk_head);
        head_row.addView(appNo_head);
        head_row.addView(branchlocname_head);
        head_row.addView(designationname_head);
        head_row.addView(type_head);
        head_row.addView(vacancies_head);
        head_row.addView(expectedjoiningdate_head);
        head_row.addView(selectallchck_head);




        //head_row.setBackgroundResource(R.drawable.tableback);
        head_row.setPadding(0,5,0, 5);

        hrapprovals.addView(head_row);



        try {
            while(rst.next()) {

                int row = rst.getRow();




                //Get the data items from database
                String apppk = rst.getString("AppPk");
                String appNo = rst.getString("AppNo");
                String branch_loc_name = rst.getString("BranchLocationName");
                String desig_name = rst.getString("DesignationName");
                String cont_type = rst.getString("Type");
                String vacancies = rst.getString("Vacancies");
                String exp_join_date = rst.getString("ExpectedJoiningDate");

                final TableRow data_row = new TableRow(TransactionEmployeeRecruitementFactoryHRApproval.this);
                final TextView appk_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                appk_view.setText(apppk);

                appk_view.setLayoutParams(textViewParam);
                appk_view.setGravity(Gravity.CENTER);
                appk_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
                appk_view.setTextColor(Color.parseColor("#062101"));



                TextView appNo_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                appNo_view.setText(appNo);
                appNo_view.setLayoutParams(textViewParam);
                appNo_view.setGravity(Gravity.CENTER);
                appNo_view.setTextColor(Color.parseColor("#062101"));
                appNo_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

                TextView branchlocation_name_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                branchlocation_name_view.setText(branch_loc_name);
                branchlocation_name_view.setLayoutParams(textViewParam);
                branchlocation_name_view.setGravity(Gravity.CENTER);
                branchlocation_name_view.setTextColor(Color.parseColor("#062101"));
                branchlocation_name_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                TextView designame_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                designame_view.setText(desig_name);
                designame_view.setLayoutParams(textViewParam);
                designame_view.setGravity(Gravity.CENTER);
                designame_view.setTextColor(Color.parseColor("#062101"));
                designame_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

                TextView type_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                type_view.setText(cont_type);
                type_view.setLayoutParams(textViewParam);
                type_view.setGravity(Gravity.CENTER);
                type_view.setTextColor(Color.parseColor("#062101"));
                type_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                TextView vac_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                vac_view.setText(vacancies);
                vac_view.setLayoutParams(textViewParam);
                vac_view.setGravity(Gravity.CENTER);
                vac_view.setTextColor(Color.parseColor("#062101"));
                vac_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                TextView expe_join_date_view = new TextView(TransactionEmployeeRecruitementFactoryHRApproval.this);
                expe_join_date_view.setText(exp_join_date);
                expe_join_date_view.setLayoutParams(textViewParam);
                expe_join_date_view.setGravity(Gravity.CENTER);
                expe_join_date_view.setTextColor(Color.parseColor("#062101"));
                expe_join_date_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                final CheckBox data_check_view = new CheckBox(this);
                data_check_view.setLayoutParams(textViewParam);
                data_check_view.setGravity(Gravity.CENTER);
                // data_check_view.setTypeface(null, Typeface.BOLD);
                data_check_view.setTextColor(Color.parseColor("#062101"));
                data_check_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

                data_row.addView(appk_view);
                data_row.addView(appNo_view);
                data_row.addView(branchlocation_name_view);
                data_row.addView(designame_view);
                data_row.addView(type_view);
                data_row.addView(vac_view);
                data_row.addView(expe_join_date_view);
                data_row.addView(data_check_view);

                //head_row.setBackgroundResource(R.drawable.tableback);
                //_row.setPadding(0,5,0, 5);

                hrapprovals.addView(data_row);




                data_check_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        int col_id = data_row.indexOfChild(data_check_view);
                        int row_id = hrapprovals.indexOfChild(data_row);


                        TableRow row = (TableRow) hrapprovals.getChildAt(row_id);
                        TextView tv = (TextView) row.getChildAt(0);
                        String row_content = tv.getText().toString();



                        if (buttonView.isChecked()) {


                            appks.add(Integer.parseInt(row_content));

                            // checked
                        }

                        else
                        {

                            appks.remove(appks.indexOf(Integer.parseInt(row_content)));

                        }
                    }

                });

                selectallchck_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if(compoundButton.isChecked())
                        {

                            for(int n =1;n<hrapprovals.getChildCount();n++)
                            {
                                View child = hrapprovals.getChildAt(n);
                                TableRow row = (TableRow) child;

                                View v= row.getChildAt(data_row.indexOfChild(data_check_view));

                                CheckBox check = (CheckBox) v;

                                check.setChecked(true);

                            }
                            //Toast.makeText(getApplicationContext(),"Select all checked",Toast.LENGTH_LONG).show();

                        }
                        else {

                            for (int n = 1; n < hrapprovals.getChildCount(); n++) {
                                View child = hrapprovals.getChildAt(n);
                                TableRow row = (TableRow) child;

                                View v = row.getChildAt(data_row.indexOfChild(data_check_view));

                                CheckBox check = (CheckBox) v;

                                check.setChecked(false);

                            }
                        }
                    }
                });

            }
        }
        catch (Exception ex)
        {
            Log.e("Error: ", ex.getMessage());
        }





        //Button Listeners
        a_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appks.size() != 0) {
                    int flag = 0;

                    int co;

                    for (int i = 0; i < appks.size(); i++) {

                        co= getData.Approve(eid, appks.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == appks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                        builder.setTitle("Factory HR Approvals");
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
                    else if(flag>0 && flag <appks.size())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                        builder.setTitle("Factory HR Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                        builder.setTitle("Factory HR Approvals");
                        builder.setMessage("Approvals failed. An error occured.");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                    builder.setTitle("Factory HR Approvals");
                    builder.setMessage("Approvals failed. No selection made");
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






        //Reject Buttonlistener
        r_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appks.size() != 0) {
                    int flag = 0;

                    int co;

                    for (int i = 0; i < appks.size(); i++) {

                        co= getData.rejecta(eid, appks.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == appks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                        builder.setTitle("Factory HR Approvals");
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
                    else if(flag>0 && flag <appks.size())
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                        builder.setTitle("Factory HR Approvals");
                        builder.setMessage("Rejections partly done, some failed. Kindly confirm.");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                        builder.setTitle("Factory HR Approvals");
                        builder.setMessage("Rejection failed. An error occured.");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryHRApproval.this);
                    builder.setTitle("Factory HR Approvals");
                    builder.setMessage("Rejection failed. No selection made");
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




    }

}

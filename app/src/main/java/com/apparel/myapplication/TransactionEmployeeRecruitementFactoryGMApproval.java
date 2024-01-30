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

public class TransactionEmployeeRecruitementFactoryGMApproval extends AppCompatActivity {

    static int location_id, eid;
    static String user_type;
    GetData getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_gm__approval);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.setTitle("Factory GM Approval");




        location_id = new MainPageActivity().passLocationID();

        user_type= new MainPageActivity().passUserType();

        eid = new MainPageActivity().PassEID();


        final List<Integer> appks = new ArrayList<>();



        //Initialise other classes
        getData = new GetData();



       final TableLayout gmapprovals= findViewById(R.id.gm_approval_table);

       Button a_btn = findViewById(R.id.approve_btn);

       Button r_btn = findViewById(R.id.reject_btn);

        ResultSet rst = getData.gmApprovals(user_type,location_id);


        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

        gmapprovals.setStretchAllColumns(true);
        gmapprovals.bringToFront();
        gmapprovals.removeAllViews();



        TableRow head_row = new TableRow(TransactionEmployeeRecruitementFactoryGMApproval.this);
        TextView apppk_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        head_row.setBackgroundColor(Color.parseColor("#2B547E"));
        apppk_head.setText("AppPk");

        apppk_head.setLayoutParams(textViewParam);
        apppk_head.setGravity(Gravity.CENTER);
        apppk_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        apppk_head.setTypeface(null, Typeface.BOLD);
        apppk_head.setTextColor(Color.parseColor("#FFFFFF"));



        TextView appNo_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        appNo_head.setText("AppNo");
        appNo_head.setLayoutParams(textViewParam);
        appNo_head.setGravity(Gravity.CENTER);
        appNo_head.setTypeface(null, Typeface.BOLD);
        appNo_head.setTextColor(Color.parseColor("#FFFFFF"));
        appNo_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView branch_loc_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        branch_loc_head.setText("BranchLocationName");
        branch_loc_head.setLayoutParams(textViewParam);
        branch_loc_head.setGravity(Gravity.CENTER);
        branch_loc_head.setTypeface(null, Typeface.BOLD);
        branch_loc_head.setTextColor(Color.parseColor("#FFFFFF"));
        branch_loc_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView designame_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        designame_head.setText("DesignationName");
        designame_head.setLayoutParams(textViewParam);
        designame_head.setGravity(Gravity.CENTER);
        designame_head.setTypeface(null, Typeface.BOLD);
        designame_head.setTextColor(Color.parseColor("#FFFFFF"));
        designame_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView conttype_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        conttype_head.setText("Type");
        conttype_head.setLayoutParams(textViewParam);
        conttype_head.setGravity(Gravity.CENTER);
        conttype_head.setTypeface(null, Typeface.BOLD);
        conttype_head.setTextColor(Color.parseColor("#FFFFFF"));
        conttype_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView vac_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        vac_head.setText("Vacancies");
        vac_head.setLayoutParams(textViewParam);
        vac_head.setGravity(Gravity.CENTER);
        vac_head.setTypeface(null, Typeface.BOLD);
        vac_head.setTextColor(Color.parseColor("#FFFFFF"));
        vac_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView expjoindate_head = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
        expjoindate_head.setText("ExpectedJoiningDate");
        expjoindate_head.setLayoutParams(textViewParam);
        expjoindate_head.setGravity(Gravity.CENTER);
        expjoindate_head.setTypeface(null, Typeface.BOLD);
        expjoindate_head.setTextColor(Color.parseColor("#FFFFFF"));
        expjoindate_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        CheckBox selectall_check_head = new CheckBox(this);
        selectall_check_head.setText("Select All");
        selectall_check_head.setLayoutParams(textViewParam);
        selectall_check_head.setGravity(Gravity.CENTER);
        selectall_check_head.setTypeface(null, Typeface.BOLD);
        selectall_check_head.setTextColor(Color.parseColor("#FFFFFF"));
        selectall_check_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        selectall_check_head.setEnabled(false);

        head_row.addView(apppk_head);
        head_row.addView(appNo_head);
        head_row.addView(branch_loc_head);
        head_row.addView(designame_head);
        head_row.addView(conttype_head);
        head_row.addView(vac_head);
        head_row.addView(expjoindate_head);
        head_row.addView(selectall_check_head);

        //head_row.setBackgroundResource(R.drawable.tableback);
        head_row.setPadding(0,5,0, 5);

        gmapprovals.addView(head_row);



       try {
           while(rst.next()) {

               //Get the data items from database
               String apppk = rst.getString("AppPk");
               String appNo = rst.getString("AppNo");
               String BLN = rst.getString("BranchLocationName");
               String DN = rst.getString("DesignationName");
               String CT = rst.getString("Type");
               String vacancies = rst.getString("Vacancies");
               String expjoindate = rst.getString("ExpectedJoiningDate");

               final TableRow data_row = new TableRow(TransactionEmployeeRecruitementFactoryGMApproval.this);
               final TextView apppk_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               apppk_view.setText(apppk);

               apppk_view.setLayoutParams(textViewParam);
               apppk_view.setGravity(Gravity.CENTER);
               apppk_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
               apppk_view.setTextColor(Color.parseColor("#062101"));



               TextView appNo_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               appNo_view.setText(appNo);
               appNo_view.setLayoutParams(textViewParam);
               appNo_view.setGravity(Gravity.CENTER);
               appNo_view.setTextColor(Color.parseColor("#062101"));
               appNo_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

               TextView branchloc_name_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               branchloc_name_view.setText(BLN);
               branchloc_name_view.setLayoutParams(textViewParam);
               branchloc_name_view.setGravity(Gravity.CENTER);
               branchloc_name_view.setTextColor(Color.parseColor("#062101"));
               branchloc_name_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


               TextView designame_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               designame_view.setText(DN);
               designame_view.setLayoutParams(textViewParam);
               designame_view.setGravity(Gravity.CENTER);
               designame_view.setTextColor(Color.parseColor("#062101"));
               designame_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

               TextView contracttype_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               contracttype_view.setText(CT);
               contracttype_view.setLayoutParams(textViewParam);
               contracttype_view.setGravity(Gravity.CENTER);
               contracttype_view.setTextColor(Color.parseColor("#062101"));
               contracttype_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


               TextView vacancies_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               vacancies_view.setText(vacancies);
               vacancies_view.setLayoutParams(textViewParam);
               vacancies_view.setGravity(Gravity.CENTER);
               vacancies_view.setTextColor(Color.parseColor("#062101"));
               vacancies_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


               TextView expjoindate_view = new TextView(TransactionEmployeeRecruitementFactoryGMApproval.this);
               expjoindate_view.setText(expjoindate);
               expjoindate_view.setLayoutParams(textViewParam);
               expjoindate_view.setGravity(Gravity.CENTER);
               expjoindate_view.setTextColor(Color.parseColor("#062101"));
               expjoindate_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


              final CheckBox select_check_view = new CheckBox(this);
               select_check_view.setLayoutParams(textViewParam);
               select_check_view.setGravity(Gravity.CENTER);
              // select_check_view.setTypeface(null, Typeface.BOLD);
               select_check_view.setTextColor(Color.parseColor("#062101"));
               select_check_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

               data_row.addView(apppk_view);
               data_row.addView(appNo_view);
               data_row.addView(branchloc_name_view);
               data_row.addView(designame_view);
               data_row.addView(contracttype_view);
               data_row.addView(vacancies_view);
               data_row.addView(expjoindate_view);
               data_row.addView(select_check_view);

               //head_row.setBackgroundResource(R.drawable.tableback);
               //_row.setPadding(0,5,0, 5);

               gmapprovals.addView(data_row);


               select_check_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                       int col_id = data_row.indexOfChild(select_check_view);
                       int row_id = gmapprovals.indexOfChild(data_row);


                       TableRow row = (TableRow) gmapprovals.getChildAt(row_id);
                       TextView tv = (TextView) row.getChildAt(0);
                       String row_content = tv.getText().toString();



                       if (buttonView.isChecked()) {


                               appks.add(Integer.parseInt(row_content));

                       }

                       else
                       {

                               appks.remove(appks.indexOf(Integer.parseInt(row_content)));

                       }
                   }

               });

               selectall_check_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                       if(compoundButton.isChecked())
                       {

                           for(int n =1;n<gmapprovals.getChildCount();n++)
                           {
                               View child = gmapprovals.getChildAt(n);
                               TableRow row = (TableRow) child;

                               View v= row.getChildAt(data_row.indexOfChild(select_check_view));
                               CheckBox check = (CheckBox) v;

                               check.setChecked(true);

                           }
                           //Toast.makeText(getApplicationContext(),"Select all checked",Toast.LENGTH_LONG).show();

                       }
                       else {

                           for (int n = 1; n < gmapprovals.getChildCount(); n++) {
                               View child = gmapprovals.getChildAt(n);
                               TableRow row = (TableRow) child;

                               View v = row.getChildAt(data_row.indexOfChild(select_check_view));
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

                        co= getData.gmApprove(eid, appks.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == appks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                        builder.setTitle("Factory GM Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                        builder.setTitle("Factory GM Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                        builder.setTitle("Factory GM Approvals");
                        builder.setMessage("Approvals failed. An error occurred.");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                    builder.setTitle("Factory GM Approvals");
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

                        co= getData.gmrejecta(eid, appks.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == appks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                        builder.setTitle("Factory GM Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                        builder.setTitle("Factory GM Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                        builder.setTitle("Factory GM Approvals");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFactoryGMApproval.this);
                    builder.setTitle("Factory GM Approvals");
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

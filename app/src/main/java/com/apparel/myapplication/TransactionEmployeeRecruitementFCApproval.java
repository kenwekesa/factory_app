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

public class TransactionEmployeeRecruitementFCApproval extends AppCompatActivity {

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


        this.setTitle("FC Approval");




        location_id = new MainPageActivity().passLocationID();

        user_type= new MainPageActivity().passUserType();

        eid = new MainPageActivity().PassEID();

        final List<Integer> appks = new ArrayList<>();
        final List<Integer> vacancys = new ArrayList<>();




        //Initialise other classes
        getData = new GetData();



        final TableLayout hrapprovals= findViewById(R.id.gm_approval_table);

        Button a_btn = findViewById(R.id.approve_btn);
        Button r_btn = findViewById(R.id.reject_btn);


        ResultSet rst = getData.FCApprovals(user_type,location_id);


        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

        hrapprovals.setStretchAllColumns(true);
        hrapprovals.bringToFront();
        hrapprovals.removeAllViews();



        final TableRow head_row = new TableRow(TransactionEmployeeRecruitementFCApproval.this);
        TextView appPK_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        head_row.setBackgroundColor(Color.parseColor("#2B547E"));
        appPK_head.setText("AppPk");

        appPK_head.setLayoutParams(textViewParam);
        appPK_head.setGravity(Gravity.CENTER);
        appPK_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        appPK_head.setTypeface(null, Typeface.BOLD);
        appPK_head.setTextColor(Color.parseColor("#FFFFFF"));



        TextView appNo_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        appNo_head.setText("AppNo");
        appNo_head.setLayoutParams(textViewParam);
        appNo_head.setGravity(Gravity.CENTER);
        appNo_head.setTypeface(null, Typeface.BOLD);
        appNo_head.setTextColor(Color.parseColor("#FFFFFF"));
        appNo_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView branchlocname_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        branchlocname_head.setText("BranchLocationName");
        branchlocname_head.setLayoutParams(textViewParam);
        branchlocname_head.setGravity(Gravity.CENTER);
        branchlocname_head.setTypeface(null, Typeface.BOLD);
        branchlocname_head.setTextColor(Color.parseColor("#FFFFFF"));
        branchlocname_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView designame_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        designame_head.setText("DesignationName");
        designame_head.setLayoutParams(textViewParam);
        designame_head.setGravity(Gravity.CENTER);
        designame_head.setTypeface(null, Typeface.BOLD);
        designame_head.setTextColor(Color.parseColor("#FFFFFF"));
        designame_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView type_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        type_head.setText("Type");
        type_head.setLayoutParams(textViewParam);
        type_head.setGravity(Gravity.CENTER);
        type_head.setTypeface(null, Typeface.BOLD);
        type_head.setTextColor(Color.parseColor("#FFFFFF"));
        type_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView vac_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        vac_head.setText("Vacancies");
        vac_head.setLayoutParams(textViewParam);
        vac_head.setGravity(Gravity.CENTER);
        vac_head.setTypeface(null, Typeface.BOLD);
        vac_head.setTextColor(Color.parseColor("#FFFFFF"));
        vac_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        TextView expjoindate_head = new TextView(TransactionEmployeeRecruitementFCApproval.this);
        expjoindate_head.setText("ExpectedJoiningDate");
        expjoindate_head.setLayoutParams(textViewParam);
        expjoindate_head.setGravity(Gravity.CENTER);
        expjoindate_head.setTypeface(null, Typeface.BOLD);
        expjoindate_head.setTextColor(Color.parseColor("#FFFFFF"));
        expjoindate_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        final CheckBox checkall_head = new CheckBox(this);
        checkall_head.setText("Select All");
        checkall_head.setLayoutParams(textViewParam);
        checkall_head.setGravity(Gravity.CENTER);
        checkall_head.setTypeface(null, Typeface.BOLD);
        checkall_head.setTextColor(Color.parseColor("#FFFFFF"));
        checkall_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        head_row.addView(appPK_head);
        head_row.addView(appNo_head);
        head_row.addView(branchlocname_head);
        head_row.addView(designame_head);
        head_row.addView(type_head);
        head_row.addView(vac_head);
        head_row.addView(expjoindate_head);
        head_row.addView(checkall_head);


        checkall_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int col_id = head_row.indexOfChild(checkall_head);
                int row_id = hrapprovals.indexOfChild(head_row);


                //TableRow row = (TableRow) hrapprovals.getChildAt(row_id);
                //TextView tv = (TextView) row.getChildAt(0);
                //String row_content = tv.getText().toString();

                if (buttonView.isChecked()) {


                    // appks.add(Integer.parseInt(row_content));
                    all_flag = true;

                    // checked
                }

                else
                {

                    all_flag=false;
                    //appks.remove(appks.indexOf(Integer.parseInt(row_content)));

                }
            }

        });

        //head_row.setBackgroundResource(R.drawable.tableback);
        head_row.setPadding(0,5,0, 5);

        hrapprovals.addView(head_row);



        try {
            while(rst.next()) {

                int row = rst.getRow();




                //Get the data items from database
                String apppk = rst.getString("AppPk");
                String appNo = rst.getString("AppNo");
                String BLN = rst.getString("BranchLocationName");
                String DN = rst.getString("DesignationName");
                String CT = rst.getString("Type");
                String vacancies = rst.getString("Vacancies");
                String EJD = rst.getString("ExpectedJoiningDate");

                final TableRow data_row = new TableRow(TransactionEmployeeRecruitementFCApproval.this);
                final TextView appPK_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                appPK_view.setText(apppk);

                appPK_view.setLayoutParams(textViewParam);
                appPK_view.setGravity(Gravity.CENTER);
                appPK_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
                appPK_view.setTextColor(Color.parseColor("#062101"));



                TextView appNo_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                appNo_view.setText(appNo);
                appNo_view.setLayoutParams(textViewParam);
                appNo_view.setGravity(Gravity.CENTER);
                appNo_view.setTextColor(Color.parseColor("#062101"));
                appNo_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

                TextView branchlocname_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                branchlocname_view.setText(BLN);
                branchlocname_view.setLayoutParams(textViewParam);
                branchlocname_view.setGravity(Gravity.CENTER);
                branchlocname_view.setTextColor(Color.parseColor("#062101"));
                branchlocname_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                TextView designame_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                designame_view.setText(DN);
                designame_view.setLayoutParams(textViewParam);
                designame_view.setGravity(Gravity.CENTER);
                designame_view.setTextColor(Color.parseColor("#062101"));
                designame_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

                TextView type_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                type_view.setText(CT);
                type_view.setLayoutParams(textViewParam);
                type_view.setGravity(Gravity.CENTER);
                type_view.setTextColor(Color.parseColor("#062101"));
                type_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                TextView vac_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                vac_view.setText(vacancies);
                vac_view.setLayoutParams(textViewParam);
                vac_view.setGravity(Gravity.CENTER);
                vac_view.setTextColor(Color.parseColor("#062101"));
                vac_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                TextView expjoindate_view = new TextView(TransactionEmployeeRecruitementFCApproval.this);
                expjoindate_view.setText(EJD);
                expjoindate_view.setLayoutParams(textViewParam);
                expjoindate_view.setGravity(Gravity.CENTER);
                expjoindate_view.setTextColor(Color.parseColor("#062101"));
                expjoindate_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);


                final CheckBox data_check = new CheckBox(this);
                data_check.setLayoutParams(textViewParam);
                data_check.setGravity(Gravity.CENTER);
                // data_check.setTypeface(null, Typeface.BOLD);
                data_check.setTextColor(Color.parseColor("#062101"));
                data_check.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);

                data_row.addView(appPK_view);
                data_row.addView(appNo_view);
                data_row.addView(branchlocname_view);
                data_row.addView(designame_view);
                data_row.addView(type_view);
                data_row.addView(vac_view);
                data_row.addView(expjoindate_view);
                data_row.addView(data_check);

                //head_row.setBackgroundResource(R.drawable.tableback);
                //_row.setPadding(0,5,0, 5);

                hrapprovals.addView(data_row);




                data_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        int col_id = data_row.indexOfChild(data_check);
                        int row_id = hrapprovals.indexOfChild(data_row);


                        TableRow row = (TableRow) hrapprovals.getChildAt(row_id);
                        TextView tv = (TextView) row.getChildAt(0);
                        TextView vacancy_view = (TextView) row.getChildAt(5);
                        String row_content = tv.getText().toString();

                        String vac_content = vacancy_view.getText().toString();



                        if (buttonView.isChecked()) {


                            appks.add(Integer.parseInt(row_content));
                            vacancys.add(Integer.parseInt(vac_content));

                            // checked
                        }

                        else
                        {

                            appks.remove(appks.indexOf(Integer.parseInt(row_content)));
                            vacancys.remove(vacancys.indexOf(Integer.parseInt(vac_content)));

                        }
                    }

                });

                checkall_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if(compoundButton.isChecked())
                        {

                            for(int n =1;n<hrapprovals.getChildCount();n++)
                            {
                                View child = hrapprovals.getChildAt(n);
                                TableRow row = (TableRow) child;

                                View v= row.getChildAt(data_row.indexOfChild(data_check));

                                CheckBox check = (CheckBox) v;

                                check.setChecked(true);

                            }
                            //Toast.makeText(getApplicationContext(),"Select all checked",Toast.LENGTH_LONG).show();

                        }
                        else {

                            for (int n = 1; n < hrapprovals.getChildCount(); n++) {
                                View child = hrapprovals.getChildAt(n);
                                TableRow row = (TableRow) child;

                                View v = row.getChildAt(data_row.indexOfChild(data_check));

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

                       co= getData.fcApprove(eid, appks.get(i), vacancys.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == appks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                        builder.setTitle("FC Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                        builder.setTitle("FC Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                        builder.setTitle("FC Approvals");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                    builder.setTitle("FC Approvals");
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

                        co= getData.fcrejecta(eid, appks.get(i));
                        if(co>0)
                        {
                            flag=flag+1;
                        }
                    }

                    if (flag == appks.size()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                        builder.setTitle("FC Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                        builder.setTitle("FC Approvals");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                        builder.setTitle("FC Approvals");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionEmployeeRecruitementFCApproval.this);
                    builder.setTitle("FC Approvals");
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

package com.apparel.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

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

import java.util.ArrayList;

public class HRUtilityUpdateEmployeeStatusEmployeeWhiteListing extends AppCompatActivity {

  ArrayList<Integer> apppks = new ArrayList<>();
  ArrayList<Integer> apNos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrutility_update_employee_status_employee_white_listing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Employee WhiteListing");



      final TableLayout mTable = findViewById(R.id.dem_table);


        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

        mTable.setStretchAllColumns(true);
        mTable.bringToFront();
        mTable.removeAllViews();



        TableRow head_row = new TableRow(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);

        TextView apppk_head = new TextView(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);
        head_row.setBackgroundColor(Color.parseColor("#2B547E"));
        apppk_head.setText("AppPk");

        apppk_head.setLayoutParams(textViewParam);
        apppk_head.setGravity(Gravity.CENTER);
        apppk_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        apppk_head.setTypeface(null, Typeface.BOLD);
        apppk_head.setTextColor(Color.parseColor("#FFFFFF"));



        TextView appNo_head = new TextView(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);
        appNo_head.setText("AppNo");
        appNo_head.setLayoutParams(textViewParam);
        appNo_head.setGravity(Gravity.CENTER);
        appNo_head.setTypeface(null, Typeface.BOLD);
        appNo_head.setTextColor(Color.parseColor("#FFFFFF"));
        appNo_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

        TextView branch_loc_head = new TextView(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);
        branch_loc_head.setText("BranchLocationName");
        branch_loc_head.setLayoutParams(textViewParam);
        branch_loc_head.setGravity(Gravity.CENTER);
        branch_loc_head.setTypeface(null, Typeface.BOLD);
        branch_loc_head.setTextColor(Color.parseColor("#FFFFFF"));
        branch_loc_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);




        CheckBox selectall_check_head = new CheckBox(this);
        selectall_check_head.setText("Select All");
        selectall_check_head.setLayoutParams(textViewParam);
        selectall_check_head.setGravity(Gravity.CENTER);
        selectall_check_head.setTypeface(null, Typeface.BOLD);
        selectall_check_head.setTextColor(Color.parseColor("#FFFFFF"));
        selectall_check_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);


        head_row.addView(apppk_head);
        head_row.addView(appNo_head);
        head_row.addView(branch_loc_head);
        head_row.addView(selectall_check_head);

        //head_row.setBackgroundResource(R.drawable.tableback);
        head_row.setPadding(0,5,0, 5);

        mTable.addView(head_row);


        for(int i=1;i<=4;i++)
        {

          final TableRow data_row = new TableRow(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);

          final TextView apppk_data = new TextView(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);
         // head_row.setBackgroundColor(Color.parseColor("#2B547E"));
          apppk_data.setText(String.valueOf(i*10));

          apppk_head.setLayoutParams(textViewParam);
          apppk_head.setGravity(Gravity.CENTER);
          apppk_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
          apppk_head.setTypeface(null, Typeface.BOLD);
         // apppk_head.setTextColor(Color.parseColor("#FFFFFF"));



          TextView appNo_data = new TextView(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);
          appNo_data.setText(String.valueOf(i*100));
          appNo_data.setLayoutParams(textViewParam);
          appNo_data.setGravity(Gravity.CENTER);
          appNo_data.setTypeface(null, Typeface.BOLD);
         // appNo_head.setTextColor(Color.parseColor("#FFFFFF"));
          appNo_head.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);

          TextView branch_loc_data = new TextView(HRUtilityUpdateEmployeeStatusEmployeeWhiteListing.this);
          branch_loc_data.setText("BranchLocationName");
          branch_loc_data.setLayoutParams(textViewParam);
          branch_loc_data.setGravity(Gravity.CENTER);
          branch_loc_data.setTypeface(null, Typeface.BOLD);
         // branch_loc_head.setTextColor(Color.parseColor("#FFFFFF"));
          branch_loc_data.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);




          final CheckBox selectall_check_data = new CheckBox(this);
          selectall_check_data.setLayoutParams(textViewParam);
          selectall_check_data.setGravity(Gravity.CENTER);
          selectall_check_data.setTypeface(null, Typeface.BOLD);
         // selectall_check_head.setTextColor(Color.parseColor("#FFFFFF"));
          selectall_check_data.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);



          data_row.addView(apppk_data);
          data_row.addView(appNo_data);
          data_row.addView(branch_loc_data);
          data_row.addView(selectall_check_data);

          //head_row.setBackgroundResource(R.drawable.tableback);
          data_row.setPadding(0,5,0, 5);

          mTable.addView(data_row);



          selectall_check_data.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              int col_id = data_row.indexOfChild(apppk_data);
              int row_id = mTable.indexOfChild(data_row);


              TableRow row = (TableRow) mTable.getChildAt(row_id);
              TextView tv = (TextView) row.getChildAt(col_id);
              String row_content = tv.getText().toString();



              if (buttonView.isChecked()) {


                apppks.add(Integer.parseInt(row_content));

              }

              else
              {

                apppks.remove(apppks.indexOf(Integer.parseInt(row_content)));

              }
            }

          });

          selectall_check_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

              if(compoundButton.isChecked())
              {

                  for(int n =1;n<mTable.getChildCount();n++)
                  {
                      View child = mTable.getChildAt(n);
                      TableRow row = (TableRow) child;

                     View v= row.getChildAt(data_row.indexOfChild(selectall_check_data));

                     CheckBox check = (CheckBox) v;

                     check.setChecked(true);

                  }
                selectall_check_data.setChecked(true);
                //Toast.makeText(getApplicationContext(),"Select all checked",Toast.LENGTH_LONG).show();

              }
              else
              {

                for(int n =1;n<mTable.getChildCount();n++)
                {
                  View child = mTable.getChildAt(n);
                  TableRow row = (TableRow) child;

                  View v= row.getChildAt(data_row.indexOfChild(selectall_check_data));

                  CheckBox check = (CheckBox) v;

                  check.setChecked(false);

                }

              }
            }
          });

        }





        Button button = findViewById(R.id.button);



      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String toshow="";

            for(int j =0; j<apppks.size();j++)
            {
              toshow = toshow+" "+String.valueOf(apppks.get(j));
            }
            Toast.makeText(getApplicationContext(), toshow,Toast.LENGTH_LONG).show();
          }
        });



    }

}

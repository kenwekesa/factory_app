package com.apparel.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UnlockUser extends AppCompatActivity {

    GetData getData;
    int selected_emp_id;
    String sempid = " ";

    Spinner user_spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //lists
        ArrayList<String> userNames = new ArrayList<>();
        final ArrayList<String> empIDs = new ArrayList<>();



        //initialising classes
        getData = new GetData();

        this.setTitle("User Unlock");

        user_spinner = findViewById(R.id.user_spinneer);

        ResultSet rs = getData.getUsers();


        try {
            while (rs.next()) {
                String name = rs.getString("name");
                userNames.add(name);

                int id = rs.getInt("empid");

                empIDs.add(String.valueOf(id));
            }
        } catch (Exception ex)
        {
            Log.e("Error", ex.getMessage());
        }

        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        user_spinner.setAdapter(dataAdapter);



        user_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // pos = position;
                if(parent.getItemAtPosition(position)!=null) {
                    sempid = empIDs.get(position);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        Button load_btn= findViewById(R.id.load_btn);
        Button update_btn = findViewById(R.id.update_btn);


        final TableLayout loads = findViewById(R.id.load_table);



        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                   getData.upDate(Integer.parseInt(sempid));
                   loads.setVisibility(View.GONE);
            }
            });

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





        ResultSet set = getData.Load(Integer.parseInt(sempid));


        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,1.0f);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);

        loads.setStretchAllColumns(true);
        loads.bringToFront();
         loads.removeAllViews();
        TableRow head_row = new TableRow(UnlockUser.this);
        TextView head1 = new TextView(UnlockUser.this);
        head_row.setBackgroundColor(Color.parseColor("#2B547E"));
        head1.setText("Employee ID");

                head1.setLayoutParams(textViewParam);
                head1.setGravity(Gravity.CENTER);
                head1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                head1.setTypeface(null, Typeface.BOLD);
                head1.setTextColor(Color.parseColor("#FFFFFF"));



                TextView head2 = new TextView(UnlockUser.this);
                head2.setText("IP Adress");
                head2.setLayoutParams(textViewParam);
                head2.setGravity(Gravity.CENTER);
                head2.setTypeface(null, Typeface.BOLD);
                head2.setTextColor(Color.parseColor("#FFFFFF"));
                head2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

                head_row.addView(head1);
        head_row.addView(head2);
        loads.addView(head_row);

        try {
            while(set.next()) {
                TableRow tr = new TableRow(UnlockUser.this);
                TextView textView1 = new TextView(UnlockUser.this);
                textView1.setText(set.getString("empid"));

                textView1.setLayoutParams(textViewParam);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);

                TextView textView2 = new TextView(UnlockUser.this);
                textView2.setText(set.getString("IPAddress"));

                textView2.setLayoutParams(textViewParam);
                textView2.setGravity(Gravity.CENTER);
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);

                tr.addView(textView1);
                tr.addView(textView2);
                loads.addView(tr);
           }
        }
        catch (Exception ex)
        {
            Log.e("Error:", ex.getMessage());

        }


        loads.setVisibility(View.VISIBLE);
        }


        });


}

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }}

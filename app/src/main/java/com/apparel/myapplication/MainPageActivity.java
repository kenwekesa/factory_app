package com.apparel.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //for navigation drawer list view
    NavigationView navigationView;
    ExpandableListView listView;
    ExpandableListAdapter listAdapter;
    List<String> menuTitle;
    LinkedHashMap<String, List<String>> menuDetail;
    TextView dev_allocation;

    LinkedHashMap<String, List<String>> debuMap;


    static String loggedin_user;
    static int loggedin_user_id,loggedin_emp_id;
    String user_location;

    GetData getData;

    static int locationID;
    static String selectecUserType;



    static ArrayList<String> userNames;


    SharedPreferences pref;

    private Timer timer;
    Runnable r;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        this.setTitle("Activity");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header = navigationView.getHeaderView(0);


        listView = findViewById(R.id.drawer_listview);
        //dev_allocation = findViewById(R.id.deviceallocation);

       // TextView dev= findViewById(R.id.dev);


      /*  dev_allocation.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DeviceAllocation.class);
                startActivity(intent);
            }
        });*/

        /*dev.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TransactionGatePassDepartmentApproval.class);
                startActivity(intent);
            }
        });*/

        pref = getSharedPreferences("userdetails", MODE_PRIVATE);


        loggedin_emp_id = Integer.parseInt(new LoginActivity().pass_EmpID());

        getData = new GetData();

        userNames = new LoginActivity().userNames;


        //GET DATA FROM ADAPTOR
        menuDetail = getData.GetMenus(loggedin_emp_id);
        if (menuDetail == null)
        {


        } else
            {

                if(!menuDetail.keySet().equals(" "))
                {
                    menuTitle = new ArrayList<>(menuDetail.keySet());
                }
                    listAdapter = new ExpandableListAdapter(MainPageActivity.this, menuTitle, menuDetail);
                    listView.setAdapter(listAdapter);

            }
        //END OF GET DATA FROM ADAPTER



        // setting list adapter



        Bundle extras = getIntent().getExtras();

        //String title = extras.getString("data");

        loggedin_user=new LoginActivity().passUsername();
        user_location = extras.getString("location");

        loggedin_user_id = Integer.parseInt(extras.getString("userID"));


        selectecUserType = extras.getString("usertype");



        locationID = extras.getInt("locationID");

       // TextView vieew = findViewById(R.id.data_view);

        //vieew.setText(String.valueOf(loggedin_emp_id));


        getData = new GetData();



        TextView user_view = nav_header.findViewById((R.id.login_identity_textbox));
        user_view.setText("Welcome "+loggedin_user);



        //HANDLES APP DELAY, ON HOLD FOR NOW
       /* handler = new Handler();
        r = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "user is inactive from last 10 minutes",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                startActivity(i);

                finish();
            }
        };
        startHandler();
*/


        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {



                String collapsed_parent = (menuTitle.get(groupPosition)).toString();
                String clicked_child =  menuDetail.get(menuTitle.get(groupPosition)).get(childPosition);



              //-----------------------Getting number of subdetails on clicked submenu---------------------------
              ResultSet rs = getData.getSub_Submenus(collapsed_parent, clicked_child, loggedin_emp_id);
              int subdet_count = 0;   //Seb details in counter

              try {
                  while (rs.next())
                      subdet_count += 1;
              }
              catch (Exception e)
              {
                  Log.e("Exception:", e.getMessage());
              }

              //------------------------------------------------------------------------------------------------


                if(subdet_count<=1)
                {
                    String classpar = clicked_child.replaceAll("\\s+","");
                    try {
                        Intent intent = new Intent(getApplicationContext(), Class.forName("com.apparel.myapplication."+classpar));
                        intent.putExtra("location",user_location);
                        intent.putExtra("locationID", locationID);
                        startActivity(intent);
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(getApplicationContext(), classpar + " does not exist yet", Toast.LENGTH_SHORT).show();

                    }
                }

                else
                    {
                    Intent intent = new Intent(getApplicationContext(), SubDet.class);
                    intent.putExtra("parent", collapsed_parent);
                    intent.putExtra("child", clicked_child);
                    startActivity(intent);
                }


                return false;
            }
        });


        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


                /*
                if(listDataHeader.get(groupPosition).equals("Vehicle Scanning")) {

                    DrawerLayout drawer =  findViewById(R.id.drawer_layout);
                    Intent vehicle_scan_intent = new Intent(getApplicationContext(), VehicleScanningActivity.class);
                    vehicle_scan_intent.putExtra("location",user_location);
                    vehicle_scan_intent.putExtra("locationID",(int)locationID);
                    drawer.closeDrawer(GravityCompat.START);

                    startActivity(vehicle_scan_intent);

                }*/
            }
        });



    }

    public static int passLocationID()
    {
        return locationID;
    }

//HANDLES APP DELAY AS WELL
/*
    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
    }


    public void stopHandler() {
        handler.removeCallbacks(r);
    }
    public void startHandler() {
        handler.postDelayed(r, 10*60*1000); //Delay time, 10 minutes for this matter
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.logout)
        {
//            Intent main_page_intent = new Intent(getApplicationContext(), MainActivity.class);

            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();

            this.finishAffinity();
            System.exit(0);

        }

        else if(id == R.id.logout2)
        {


            Intent main_page_intent = new Intent(getApplicationContext(), MainActivity.class);

            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            this.finishAffinity();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.setClickable(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


/*
    private class GetMenu extends AsyncTask<String, Void, String> {

        String res = "";
        ArrayList<String> data;
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {


            MainPageActivity.this.runOnUiThread(new Runnable() {
                public void run() {


            try {

                menuDetail = getData.GetMenus();
                if (menuDetail == null)
                {


                   res="An error occured";

                                 }
                else {


                            menuTitle = new ArrayList<String>(menuDetail.keySet());
                            listAdapter = new ExpandableListAdapter(MainPageActivity.this, menuTitle, menuDetail);



                }
            } catch (Exception e) {
                res = "error:" + e.getMessage().toString();
            }
                }
            });
            return res;

        }



        @Override
        protected void onPostExecute(String result) {
            if (result.equals("1")) {

                listView.setAdapter(listAdapter);
            } else {

                Toast.makeText(MainPageActivity.this, result, Toast.LENGTH_SHORT).show();

            }
        }*/





    public static int pass_userID()
    {
        return loggedin_user_id;
    }

    static public String passUserType()
    {
        return selectecUserType;
    }

    static public int PassEID()
    {
        return loggedin_emp_id;
    }



}

















//@Kennedy Wekesa
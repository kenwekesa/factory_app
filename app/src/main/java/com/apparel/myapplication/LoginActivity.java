package com.apparel.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    // Declaring connection variables
    Connection con;
    String u_name,pass,db,ip;

    ProgressBar progressBar;
    String location_spinner_selected_value;
    //End Declaring connection variables

    //Login in credentials
    static String selected_username=" ";
    String entered_password=" ", selected_user_id, selected_user_type;

    static String selected_emp_id, user_type;

    static ArrayList<String> userNames;

   SharedPreferences pref;
    //Other used variables
    int selected_location_id, idd;
    int pos=0;


    //Userfields

    EditText user_password_field;

    Spinner location_spinner;
    Spinner username_spinner;


    Statement st;
    ResultSet rs;

    //Variables
    SharedPreferences sharedPreferences;

    String data;//To be changed later



    DBConnection connection= new DBConnection();
    GetData getData =new GetData();
    DBConnection connection_global= new DBConnection();



    public static final String msharedpreferences = "userdetails";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        // Declaring Server ip, username, database name and password
        ConnectionVariables con_variables=new ConnectionVariables();

                db = con_variables.getDb();
                u_name = con_variables.getU_name();
                ip = con_variables.getTest_ip();
                pass = con_variables.getTest_pass();
        // Declaring Server ip, username, database name and password


       // progressBar = (ProgressBar) findViewById(R.id.progressBar);






        //This hides the title bar of the activity
        getSupportActionBar().hide();
        pref = getSharedPreferences(msharedpreferences, Context.MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title")+" LOGIN";

        String con_flag= extras.getString("connection_type");


        //Initialising all views here
        TextView login_title_textview = findViewById(R.id.login_title);
        user_password_field = findViewById((R.id.user_password_field));
        location_spinner = findViewById(R.id.location_spinner);
        username_spinner = findViewById(R.id.username_spinner);

        login_title_textview.setText(title);



        //Array List for spinner data
        ArrayList<String> location_list = new ArrayList();
        final ArrayList<String> location_id_list = new ArrayList();

        final ArrayList<String> user_id_list = new ArrayList();

        final ArrayList<String> EmpID_list = new ArrayList();

        final ArrayList<String> user_names_list = new ArrayList();

        final ArrayList<String> user_types = new ArrayList<>();

         userNames = new ArrayList();

        ArrayList<String> l1,l2;

        con = connection.connectionclass();
        if(con==null)
        {

            final ProgressDialog myProgressDialog;
            myProgressDialog= new ProgressDialog(LoginActivity.this);
            myProgressDialog.setMessage("Loading...");
            myProgressDialog.setCancelable(false);
            myProgressDialog.show();

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
                    {
                    @Override
                    public void run()
                    {
                        //Do something here
                        myProgressDialog.dismiss();

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Could not connect, try again!!");
                        AlertDialog alert1 = builder.create();

                        alert1.show();
                    }
                    }, 10*1000);



        }
        else {


            ResultSet location_rs = getData.getLocation();


                try {



                    while (location_rs.next()) {
                        String Location = location_rs.getString("Location");
                        String Location_ID = location_rs.getString("LocationID");
                        location_list.add(Location);
                        location_id_list.add(Location_ID);

                    }


                } catch (SQLException se) {
                    Log.e("error here 1 : ", se.getMessage());
                }


           //Set data to first Spinner field
            ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, location_list);
            NoCoreAdapter.notifyDataSetChanged();
            location_spinner.setAdapter(NoCoreAdapter);






            location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                         pos = position;
                             user_names_list.clear();
                             EmpID_list.clear();
                             user_id_list.clear();
                             user_types.clear();
                             idd=Integer.parseInt(location_id_list.get(pos));
                             rs = getData.getLocationID(idd);

                             try{

                             while(rs.next())
                             {
                                 String username = rs.getString("Username");
                                 String user_id = rs.getString("Id");
                                 String empID = rs.getString("EmpID");
                                 String UT = rs.getString("UserType");



                                 user_names_list.add(username);
                                 //user_id
                                 user_id_list.add(user_id);
                                 //EmpID
                                 EmpID_list.add(empID);
                                 //User type
                                 user_types.add(UT);

                             }}
                             catch (SQLException se) {
                                 Log.e("error here 1 : ", se.getMessage());
                             }
                             ArrayAdapter dataAdapter = new ArrayAdapter(parent.getContext(), android.R.layout.simple_list_item_1, user_names_list);
                             dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                             dataAdapter.notifyDataSetChanged();
                             username_spinner.setAdapter(dataAdapter);




                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            userNames = user_names_list;

            username_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    pos = position;

                    selected_username = parent.getItemAtPosition(position).toString();

                    selected_user_id = user_id_list.get(position);

                    selected_emp_id = EmpID_list.get(position);

                    selected_user_type = user_types.get(position);



                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView)
                {


                }
            });

            //Set data to first Spinner field
           // String[] array2 = user_names_list.toArray(new String[0]);
            ArrayAdapter NoCoreAdapter22= new ArrayAdapter(this, android.R.layout.simple_list_item_1, user_names_list);
            username_spinner.setAdapter(NoCoreAdapter22);

        }

        //This login button action listener





        //Get user selected credentials

        TextView tv = findViewById(R.id.label_text);
        tv.setText(location_spinner_selected_value);
        Button login_button = findViewById(R.id.login_button);



        login_button.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

              /*  Intent main_page_intent = new Intent(getApplicationContext(), MainPageActivity.class);
                main_page_intent.putExtra("data", data);
                startActivity(main_page_intent);
                */

                //Getting the selected value on first Spinner
               /* while(username_spinner.getSelectedItem()!=null)
                {
                    selected_username = username_spinner.getSelectedItem().toString();

                */



                    entered_password = user_password_field.getText().toString();




                if (selected_username.trim().equals("") || entered_password.trim().equals(""))
                {
                    TextView login_error_textview = findViewById(R.id.login_error_textview);
                    login_error_textview.setText("Login failed, kindly enter your credentials!");
                    login_error_textview.setVisibility(View.VISIBLE);
                }
                else {
                    try {
                        con = connection.connectionclass();        // Connect to database
                        if (con == null) {
                            TextView login_error_textview = findViewById(R.id.login_error_textview);
                            login_error_textview.setText("Connection failure");
                            login_error_textview.setVisibility(View.VISIBLE);
                        }

                        else {


                            ResultSet rs = getData.getLoginCredentials(Integer.parseInt(selected_emp_id), entered_password,idd);

                                if (rs.next()) {
                                  /*  String database_username = rs.getString("Username");
                                    names_list.add(database_username);
                                    String database_password = rs.getString("Password");
                                    password_list.add(database_password);
                                    */

                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("username",selected_username);
                                    editor.putString("locationID",String.valueOf(idd));
                                    editor.putString("location",location_spinner_selected_value);

                                    editor.commit();
                                    Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();


                                  Intent main_page_intent = new Intent(getApplicationContext(), MainPageActivity.class);
                                    main_page_intent.putExtra("data", data);
                                    main_page_intent.putExtra("username",selected_username);
                                    main_page_intent.putExtra("locationID" ,idd);
                                    main_page_intent.putExtra("userID",selected_user_id);
                                    main_page_intent.putExtra("usertype",selected_user_type);
                                    finish();
                                    startActivity(main_page_intent);
                                    con.close();

                                }

                                else {


                                    TextView login_error_textview = findViewById(R.id.login_error_textview);
                                    login_error_textview.setText("Login failed, kindly confirm your credentials! " );
                                    login_error_textview.setVisibility(View.VISIBLE);

                                }

                            }

                        }
                    catch (SQLException se) {
                        Log.e("error here 1 : ", se.getMessage());
                    }




    /*


                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Name, n);
                    editor.putString(Phone, ph);
                    editor.putString(Email, e);
                    editor.commit();
             */




                }


            }


        });


       user_type = selected_user_type;



    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }






    public int passLocationID()
    {
        return idd;
    }

    public String pass_EmpID()
    {
        return selected_emp_id;
    }

    static public String passUsername()
    {
        return selected_username;
    }








}
package com.apparel.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

import static com.apparel.myapplication.DriverScanActivity.activity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
ConnectionVariables con_var = new ConnectionVariables();

    public String global_ip =con_var.getGlobal_ip();
    public String local_ip =con_var.getLocal_ip();

    public String test_ip =con_var.getTest_ip();



    public String pass = con_var.getPass();
    public String test_pass=con_var.getTest_pass();
    private static String ip,passs,port;

    public String connection_flag;

    static ArrayList<String> userNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_main);
       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        this.setTitle(" ");


        userNames = new LoginActivity().userNames;

        //getting buttons from views by their ids
       CardView hrm_button = findViewById(R.id.atc_hrm_local_card);
       CardView hrm_global_button = findViewById(R.id.atc_hrm_global_card);
       CardView world_global = findViewById(R.id.atc_world_global_card);
        pref = getSharedPreferences("userdetails",MODE_PRIVATE);
        //Action listeners to the button

        //hrm button listeners
        hrm_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                connection_flag = "local";

                ip=local_ip;
                passs=pass;
                port = "";
                // TODO Auto-generated method stub
               Intent login_page_intent=new Intent(getApplicationContext(),LoginActivity.class);
                login_page_intent.putExtra("title","ATCHRM");
                login_page_intent.putExtra("connection_type","local");

                startActivity(login_page_intent);


            }
        });


        //world button listener
        hrm_global_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                // TODO Auto-generated method stub
                ip=global_ip;
                 passs=pass;
                 port = con_var.getPortNo();
                Intent login_page_intent=new Intent(getApplicationContext(),LoginActivity.class);
                login_page_intent.putExtra("title","ATCHRM");
                login_page_intent.putExtra("connection_type","local");
                startActivity(login_page_intent);

                //TODO Auto-generated method stub
               /* if(pref.contains("username") && pref.contains("locationID")){

                    String name_in_sharedpreferences=pref.getString("username", "");

                    Intent main_page_intent = new Intent(getApplicationContext(), MainPageActivity.class);
                    main_page_intent.putExtra("username", name_in_sharedpreferences);
                    startActivity(main_page_intent);

                }
                else {
                    Intent login_page_intent = new Intent(getApplicationContext(), LoginActivity.class);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    login_page_intent.putExtra("title", "ATCWORLD");
                    startActivity(login_page_intent);
                }*/
            }
        });

        world_global.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                // TODO Auto-generated method stub
              ip=test_ip;
                passs=test_pass;
                Intent login_page_intent=new Intent(getApplicationContext(),LoginActivity.class);
                login_page_intent.putExtra("title","ATCHRM");
                login_page_intent.putExtra("connection_type","local");
                startActivity(login_page_intent);


                //TODO Auto-generated method stub
               /* if(pref.contains("username") && pref.contains("locationID")){

                    String name_in_sharedpreferences=pref.getString("username", "");

                    Intent main_page_intent = new Intent(getApplicationContext(), MainPageActivity.class);
                    main_page_intent.putExtra("username", name_in_sharedpreferences);
                    startActivity(main_page_intent);

                }
                else {
                    Intent login_page_intent = new Intent(getApplicationContext(), LoginActivity.class);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    login_page_intent.putExtra("title", "ATCWORLD");
                    startActivity(login_page_intent);
                }*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    public static String ip()
    {
        return ip;
    }
    public static String pass()
    {
        return passs;
    }
    public  static String getPort()
    {
        return port;
    }
}

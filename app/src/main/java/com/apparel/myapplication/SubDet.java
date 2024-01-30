package com.apparel.myapplication;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class SubDet extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter menuAdapter;
    private RecyclerView.LayoutManager layoutManager;

    static String clicked_child;
    static String collapsed_parent;

    GetData getData;

    ResultSet rs;
    int EmpID;


    List<MenuDetails> menuDetailsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_det);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        collapsed_parent = extras.getString("parent");
        clicked_child = extras.getString("child");

        this.setTitle(clicked_child);
        EmpID = Integer.parseInt(new LoginActivity().pass_EmpID());


        TextView parent = findViewById(R.id.parent);
       // TextView child = findViewById(R.id.child);
        recyclerView = findViewById(R.id.subs_recycler_view);

        getData = new GetData();


        rs = getData.getSub_Submenus(collapsed_parent, clicked_child, EmpID);

        menuDetailsList = new ArrayList<>();


        try {


            while (rs.next()) {

                MenuDetails menuDetails = new MenuDetails();
                menuDetails.setMenuName(rs.getString("MnuSubDetName"));

               if(!menuDetailsList.contains(menuDetails)) {
                   menuDetailsList.add(menuDetails);
               }


           }

            List<MenuDetails> sieved_list = new ArrayList<>(new LinkedHashSet<>(menuDetailsList));

            //Chi menu Khu recyclerview adapter
            layoutManager = new LinearLayoutManager(this);
            menuAdapter = new MenuViewAdapter(sieved_list);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(menuAdapter);


       }
       catch (Exception ex)
       {
           Log.e("Error:", ex.getMessage());
       }

      // TextView textview = findViewById(R.id.child_text);
        //child.setText(clicked_child);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public static String passParent()
    {
        return collapsed_parent;
    }

    public static String passChild()
    {
        return clicked_child;
    }

}

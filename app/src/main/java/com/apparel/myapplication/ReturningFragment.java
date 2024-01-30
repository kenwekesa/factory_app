package com.apparel.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReturningFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private int SCAN_REQUESTCODE = 98;
    int empID,userPK, employee_designationPK, locationPK;
   // String empid;

    MainPageActivity mainPageActivity;
    GetData getData;

    private static final String ARG_PARAM2 = "param2";

    String reasons;

    CardView scan_cardview;
    TextView empid_view, dateview,textclock;
    Spinner time_sp, reasons_sp;
    EditText narationview;

    Button submitbtn;

    ArrayList<String> reasonslist = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();



    LinearLayout linlayout0,linlayout1,linlayout2,linlayout3,linearlayoutclock;

    // TODO: Rename and change types of parameters
    private String mParam1,mParam2,dateee;
    String dateraw;

    private OnFragmentInteractionListener mListener;

    public ReturningFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReturningFragment newInstance(String param1, String param2) {
        ReturningFragment fragment = new ReturningFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_returning, container, false);

//Finding the current time
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss a");

        Date date = Calendar.getInstance().getTime();
        dateee = (dateFormat.format(date));

        dateraw = (dateFormat2.format(date));



        for(int i=0;i<=480;i+=30)
        {
            time.add(i+" mins");
        }

        reasonslist.add("Due to sickness");
        reasonslist.add("Others");

        //Initialising classes
        mainPageActivity = new MainPageActivity();
        getData = new GetData();



        linlayout0 = rootview.findViewById(R.id.linearLayout0);
        linlayout1=rootview.findViewById(R.id.linearLayout);
        linlayout2 = rootview.findViewById(R.id.linearLayout2);
        linlayout3 = rootview.findViewById(R.id.linearLayout3);
        linearlayoutclock = rootview.findViewById(R.id.clockk);

        submitbtn = rootview.findViewById(R.id.submitbtn);
        scan_cardview = rootview.findViewById(R.id.scan_id);
        empid_view = rootview.findViewById(R.id.empid_view);
        narationview = rootview.findViewById(R.id.narationbox);

        reasons_sp = rootview.findViewById(R.id.reason_sp);
        time_sp= rootview.findViewById(R.id.time_sp);
        dateview = rootview.findViewById(R.id.date_view);








        scan_cardview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(getContext(),QrScanner.class), SCAN_REQUESTCODE);

            }
        });
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SCAN_REQUESTCODE)
        {
            if(resultCode == RESULT_OK)
            {
                scan_cardview.setVisibility(View.INVISIBLE);
                linearlayoutclock.setVisibility(View.VISIBLE);

                userPK = mainPageActivity.PassEID();
                empID = Integer.parseInt(data.getStringExtra("empID"));
                locationPK = mainPageActivity.locationID;
                empid_view.setText(String.valueOf(empID));


                ResultSet rs =getData.getEmployeeDetails(empID);
                try
                {
                    if(rs.next())
                    {
                        employee_designationPK= Integer.parseInt(rs.getString("DesignationPk"));
                    }
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }

                dateview.setText(dateee);
                linlayout0.setVisibility(View.VISIBLE);
                linlayout1.setVisibility(View.VISIBLE);
                linlayout2.setVisibility(View.VISIBLE);
                linlayout3.setVisibility(View.VISIBLE);


                submitbtn.setVisibility(View.VISIBLE);



                ArrayAdapter ReasonsAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, reasonslist);
                ReasonsAdapter.notifyDataSetChanged();
                reasons_sp.setAdapter(ReasonsAdapter);

                ArrayAdapter TimeAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, time);
                ReasonsAdapter.notifyDataSetChanged();
                time_sp.setAdapter(TimeAdapter);


                submitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        reasons = reasons_sp.getSelectedItem().toString() + " - " + narationview.getText().toString();

                       final int insertflag = 0;
                        AlertDialog.Builder outer_builder = new AlertDialog.Builder(getContext());
                        outer_builder.setTitle("Confirm submission");
                        outer_builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //int insertflag =  getData.insertGatepassData(employee_designationPK,userPK,empID,locationPK,dateraw,reasons);
                                if (insertflag > 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Gatepass");
                                    builder.setMessage("Successfully submitted");
                                    AlertDialog alert1 = builder.create();
                                    alert1.show();
                                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                        @Override
                                        public void onCancel(DialogInterface dialog) {

                                            ((Activity) getContext()).finish();
                                            ((Activity) getContext()).overridePendingTransition(0, 0);
                                            startActivity(((Activity) getContext()).getIntent());
                                            ((Activity) getContext()).overridePendingTransition(0, 0);

                                        }
                                    });
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Gatepass");
                                    builder.setMessage("Failed, try again...");
                                    AlertDialog alert1 = builder.create();
                                    alert1.show();

                                    alert1.setOnCancelListener(new DialogInterface.OnCancelListener() {

                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                            ((Activity) getContext()).finish();
                                            ((Activity) getContext()).overridePendingTransition(0, 0);
                                            startActivity(((Activity) getContext()).getIntent());
                                            ((Activity) getContext()).overridePendingTransition(0, 0);

                                        }
                                    });
                                }
                            }
                        });

                        outer_builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((Activity)getContext()).finish();
                                ((Activity)getContext()).overridePendingTransition(0, 0);
                                startActivity(((Activity)getContext()).getIntent());
                                ((Activity)getContext()).overridePendingTransition(0, 0);
                            }
                        });

                        AlertDialog alert1 = outer_builder.create();
                        alert1.show();
                    }

                });

            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

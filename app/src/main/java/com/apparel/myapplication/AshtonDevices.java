package com.apparel.myapplication;

import android.util.Log;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AshtonDevices {

   GetData getData = new GetData();
   ResultSet rs;

    ArrayList<String> type_list = new ArrayList<>();
    ArrayList<String> id_list = new ArrayList<>();
    ArrayList<Integer> empidneeded_list = new ArrayList<>();




    public AshtonDevices()
    {
        setDeviceList();
    }

    public void setDeviceList()
    {
        rs = getData.getDevices();

        try {
            while (rs.next())
            {
                type_list.add(rs.getString("AssetType"));
                id_list.add(rs.getString("AssetID"));
                empidneeded_list.add(rs.getInt("IsEmpidRequired"));
            }
        }
        catch (Exception e)
        {
            Log.e("Error:",e.getMessage());
        }
        {

        }

    }

    public ArrayList<String> getDevices()
    {
        return type_list;

    }
    public ArrayList getDeviceID()
    {
        return id_list;
    }
    public ArrayList getDeviceNeededstatus()
    {
        return empidneeded_list;
    }

}

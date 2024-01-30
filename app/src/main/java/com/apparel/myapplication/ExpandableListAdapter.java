package com.apparel.myapplication;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private LinkedHashMap<String, List<String>> _listDataChild;


    private static class groupHolder{ static ImageView img; TextView textView; }

    ImageView drawer_menu_icon;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 LinkedHashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.drawerchild_items, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.child_item_lbl);



        convertView.setPadding(20,0,0,0); // left, top, right, bottom


        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childsize=0;

       // if(groupPosition!=0) {
          childsize=  this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();

        return  childsize;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.drawerlistview, null);
        }
        drawer_menu_icon = convertView.findViewById(R.id.imgicon);

        if(groupPosition!=0)
        {
            if (isExpanded) {
                drawer_menu_icon.setImageResource(R.drawable.arrow_dropped);
            } else
                {

                drawer_menu_icon.setImageResource(R.drawable.drop_down_arrow);
            }
        }

        TextView lblListHeader =  convertView
                .findViewById(R.id.parent_list_lbl);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
package com.apparel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MenuViewAdapter extends RecyclerView.Adapter<MenuViewAdapter.UserViewHolder>
{
    List<MenuDetails> menuDetailsList;
    Context context;

    SubDet det;

    String parent, child;

    GetData getData;

    public MenuViewAdapter(List<MenuDetails> menuDetailsList) {
        this.menuDetailsList = menuDetailsList;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.card_item, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(iteView);
        return viewHolder;


    }


    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        MenuDetails menuDetails = menuDetailsList.get(position);
        holder.mmenu_text.setText(menuDetails.getMenuName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                det = new SubDet();

                parent= det.passParent();
                child= det.passChild();

                String classpar = parent+child+holder.mmenu_text.getText().toString();
               String  classparr =classpar.replaceAll("\\s+","");

                try {
                    Intent intent = new Intent(context, Class.forName("com.apparel.myapplication."+classparr));
                    context.startActivity(intent);
                }
                catch (Exception ex)
                {
                    Toast.makeText(context, classparr + " does not exist yet", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return menuDetailsList.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mmenu_text;
        ImageView ivMenu;
        public UserViewHolder(View itemView) {
            super(itemView);
            mmenu_text =  itemView.findViewById(R.id.menu_text);

            ivMenu = (ImageView) itemView.findViewById(R.id.menu_image);
        }
    }
}
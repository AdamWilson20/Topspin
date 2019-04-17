package com.example.topspin;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerListAdapater extends ArrayAdapter<Player> {
        private static final String TAG = "PlayerListAdapter";
        private Context mContext;
        int mResource;

       public PlayerListAdapater(Context context, int resource, ArrayList<Player> objects) {
           super(context, resource, objects);
           mContext = context;
           mResource = resource;

       }


       @NonNull
       @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        //get the player information
           String name = getItem(position).getName();
           String standing = getItem(position).getStanding();
           String hometown = getItem(position).getHometown();
           String height = getItem(position).getHeight();
           String weight = getItem(position).getWeight();

           //Create the player object with the information
          Player player = new Player(name,standing,hometown,height,weight);

           LayoutInflater inflater = LayoutInflater.from(mContext);
           convertView = inflater.inflate(mResource,parent,false);

           TextView tvName = (TextView) convertView.findViewById(R.id.textView15);
           TextView tvStanding = (TextView) convertView.findViewById(R.id.textView16);
           TextView tvHometown = (TextView) convertView.findViewById(R.id.textView17);
           TextView tvHeight = (TextView) convertView.findViewById(R.id.textView18);
           TextView tvWeight = (TextView) convertView.findViewById(R.id.textView19);

           tvName.setText(name);
           tvStanding.setText(standing);
           tvHometown.setText(hometown);
           tvHeight.setText(height);
           tvWeight.setText(weight);

           return convertView;

       }
}

package com.example.topspin;


import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerListAdapater extends ArrayAdapter<Player> {
       // private static final String TAG = "PlayerListAdapter";
        private ArrayList<Player> players;
        private Context mContext;
        int mResource;
    /*
       public PlayerListAdapater(Context context, int resource, ArrayList<Player> objects) {
           super(context, resource, objects);
           mContext = context;
           mResource = resource;

       }*/
       public PlayerListAdapater(ArrayList<Player> P, Context c) {
           super(c, R.layout.activity_player_roster,P);
           this.players = P;
           this.mContext = c;
       }



       @NonNull
       @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           LayoutInflater inflater = LayoutInflater.from(mContext);
           View view = inflater.inflate(R.layout.activity_player_roster,null,true);

           TextView name = (TextView) view.findViewById(R.id.textView15);

           Player player = players.get(position);
           name.setText(player.getFirstName());

            return view;

       }
}

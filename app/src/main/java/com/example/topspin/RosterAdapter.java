package com.example.topspin;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

public class RosterAdapter extends ArrayAdapter<Player>{
    private final List<Player> list;
    private final Activity context;
    ImageLoader imageLoader;


    // View lookup cache
    static class ViewHolder {
        protected ImageView playerImage;
        protected TextView fullName;
        protected TextView height;
        protected TextView weight;
        protected TextView year;
        protected TextView hometown;

        //protected CheckBox checkbox;
    }


    public RosterAdapter(Activity context, List<Player>list) {
        super(context, R.layout.adapter_player, list);
        this.context = context;
        this.list = list;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        RosterAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.adapter_player, null);
            viewHolder = new RosterAdapter.ViewHolder();
            viewHolder.fullName = (TextView) convertView.findViewById(R.id.textView15);
            viewHolder.height = (TextView) convertView.findViewById(R.id.textView18);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.textView19);
            viewHolder.year = (TextView) convertView.findViewById(R.id.textView16);
            viewHolder.hometown = (TextView) convertView.findViewById(R.id.textView17);
           /* viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.check);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    list.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                }
            });*/
            convertView.setTag(viewHolder);



            convertView.setTag(R.id.textView15, viewHolder.fullName);
            convertView.setTag(R.id.textView16, viewHolder.height);
            convertView.setTag(R.id.textView17, viewHolder.weight);
            convertView.setTag(R.id.textView18, viewHolder.year);
            convertView.setTag(R.id.textView19, viewHolder.hometown);
            // convertView.setTag(R.id.check, viewHolder.checkbox);

        }else{
            viewHolder = (RosterAdapter.ViewHolder) convertView.getTag();

        }
        if(imageLoader == null)
        {
            imageLoader = RequestHandler.getInstance(context).getImageLoader();
        }
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.imageView5);
        Player p = list.get(position);
        thumbNail.setImageUrl(p.getPlayerImage(), imageLoader);
        viewHolder.fullName.setText(list.get(position).getFullName());
        viewHolder.height.setText(list.get(position).getHeight());
        viewHolder.weight.setText(list.get(position).getWeight());
        viewHolder.year.setText(list.get(position).getYear());
        viewHolder.hometown.setText(list.get(position).getHometown());
        // viewHolder.checkbox.setChecked(list.get(position).isSelected());

        return convertView;
    }
}

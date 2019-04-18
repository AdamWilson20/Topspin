package com.example.topspin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<Tournament> {
    private final List<Tournament> list;
    private final Activity context;



    // View lookup cache
    static class ViewHolder {
        protected TextView date;
        protected TextView time;

        protected TextView vsOrAt;
        protected TextView oppTeam;
        protected TextView location;

        //protected CheckBox checkbox;
    }


    public ScheduleAdapter(Activity context, List<Tournament>list) {
        super(context, R.layout.scheduleadapter, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.scheduleadapter, null);
            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.TournamentDate);
            viewHolder.time = (TextView) convertView.findViewById(R.id.TournamentTime);
            viewHolder.vsOrAt = (TextView) convertView.findViewById(R.id.VSorAT);
            viewHolder.oppTeam = (TextView) convertView.findViewById(R.id.TournamentOppTeam);
            viewHolder.location = (TextView) convertView.findViewById(R.id.TournamentLocation);
           /* viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.check);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    list.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                }
            });*/
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.TournamentDate, viewHolder.date);
            convertView.setTag(R.id.TournamentTime, viewHolder.time);
            convertView.setTag(R.id.VSorAT, viewHolder.vsOrAt);
            convertView.setTag(R.id.TournamentOppTeam, viewHolder.oppTeam);
            convertView.setTag(R.id.TournamentLocation, viewHolder.location);
            // convertView.setTag(R.id.check, viewHolder.checkbox);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        // viewHolder.checkbox.setTag(position); // This line is important.
        viewHolder.date.setText(list.get(position).getDate());
        viewHolder.time.setText(list.get(position).getTime());
        viewHolder.oppTeam.setText(list.get(position).getOppTeam());
        viewHolder.location.setText(list.get(position).getLocation());

        if(list.get(position).getHome()) {
            viewHolder.vsOrAt.setText("VS");
        }else{viewHolder.vsOrAt.setText("AT");}
        // viewHolder.checkbox.setChecked(list.get(position).isSelected());

        return convertView;
    }
}


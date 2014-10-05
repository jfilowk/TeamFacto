package com.jfilowk.teamfactory.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 01/10/14.
 */
public class ListEventsAdapter extends BaseAdapter {

    private List<Event> eventList;
    private Context context;

    public ListEventsAdapter() {
    }

    public ListEventsAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_list_events, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int randomColor = randInt(1, 3);

        if(randomColor == 1) {
            convertView.setBackgroundColor(Color.parseColor("#66E066"));
        } else if (randomColor == 2){
            convertView.setBackgroundColor(Color.parseColor("#FF4747"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFAD33"));
        }
        holder.listDayEvent.setText(shortNameDay(eventList.get(position).getCreated_at()));
        holder.listEventMonth.setText(shortNameMonth(eventList.get(position).getCreated_at()));
        holder.listEventNumUsers.setText(String.valueOf(eventList.get(position).getNumUser()));
        holder.listEventTeam.setText(String.valueOf(eventList.get(position).getNumTeams()));

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.textDayEvent)
        TextView listDayEvent;
        @InjectView(R.id.textNumUsers)
        TextView listEventNumUsers;
        @InjectView(R.id.textMonthEvent) TextView listEventMonth;
        @InjectView(R.id.textTeamsEvent) TextView listEventTeam;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    private String shortNameMonth (String date) {
        String months [] = new DateFormatSymbols().getShortMonths();
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int numMonth = 0;
        try {
            Date dateFromString = format.parse(date);
             numMonth = dateFromString.getMonth()-1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return months[numMonth];
    }

    private String shortNameDay (String date) {
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int numDay = 0;
        try {
            Date dateFromString = format.parse(date);
            numDay = dateFromString.getDay()-1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(numDay);
    }

    public static int randInt(int min, int max) {


        Random rand = new Random();


        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

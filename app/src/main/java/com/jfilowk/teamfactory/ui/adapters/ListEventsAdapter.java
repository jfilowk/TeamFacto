package com.jfilowk.teamfactory.ui.adapters;

import android.content.Context;
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
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 01/10/14.
 */
public class ListEventsAdapter extends BaseAdapter {

    private static final String KEY_SCHOOL = "School";
    private static final String KEY_BUSINESS = "Business";
    private static final String KEY_SPORT = "Sport";

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

        if (eventList.get(position).getType().equals(KEY_SCHOOL)) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.orange));
        } else if (eventList.get(position).getType().equals(KEY_BUSINESS)) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.red));
        } else if (eventList.get(position).getType().equals(KEY_SPORT)) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.green));
        } else {

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
        @InjectView(R.id.textMonthEvent)
        TextView listEventMonth;
        @InjectView(R.id.textTeamsEvent)
        TextView listEventTeam;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    private String shortNameMonth(String date) {
        String months[] = new DateFormatSymbols().getShortMonths();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int numMonth = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            numMonth = calendar.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return months[numMonth];
    }

    private String shortNameDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int numDay = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            numDay = calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(numDay);
    }
}

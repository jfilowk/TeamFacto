package com.jfilowk.teamfactory.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;

import java.util.List;

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

        holder.listEventDate.setText(eventList.get(position).getCreated_at().toString());
        holder.listEventNumUsers.setText(eventList.get(position).getNumUser());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.textDateEvent)
        TextView listEventDate;

        @InjectView(R.id.textNumUsers)
        TextView listEventNumUsers;


        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

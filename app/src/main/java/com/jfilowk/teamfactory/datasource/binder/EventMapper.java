package com.jfilowk.teamfactory.datasource.binder;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 22/09/14.
 */
public class EventMapper {

    public EventMapper() {
    }

    public EventCollection transformEventCursorToEventCollection(Cursor cursor) {

        EventCollection eventList = new EventCollection();

        int id = cursor.getColumnIndex("id");
        int type = cursor.getColumnIndex("type");
        int num_users = cursor.getColumnIndex("num_users");
        int num_teams = cursor.getColumnIndex("num_teams");
        int created_at = cursor.getColumnIndex("created_at");

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Event event = new Event();
            event.setId(cursor.getInt(id));
            event.setType(cursor.getString(type));
            event.setNumUser(cursor.getInt(num_users));
            event.setNumTeams(cursor.getInt(num_teams));
            String stringDate = cursor.getString(created_at);
//            Date date = new Date(stringDate);
            event.setCreated_at(stringDate); //TODO: Change this!!!
            eventList.add(event);
        }

        return eventList;
    }

}

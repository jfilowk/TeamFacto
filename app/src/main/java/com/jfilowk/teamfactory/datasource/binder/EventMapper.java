package com.jfilowk.teamfactory.datasource.binder;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 22/09/14.
 */
public class EventMapper {


    private static String KEY_ID = "id";
    private static String KEY_TYPE = "type";
    private static String KEY_NUM_USERS = "num_users";
    private static String KEY_NUM_TEAMS = "num_teams";
    private static String KEY_CREATED = "created_at";

    public EventMapper() {
    }

    public EventCollection transformEventCursorToEventCollection(Cursor cursor) {

        EventCollection eventList = new EventCollection();

        int id = cursor.getColumnIndex(KEY_ID);
        int type = cursor.getColumnIndex(KEY_TYPE);
        int num_users = cursor.getColumnIndex(KEY_NUM_USERS);
        int num_teams = cursor.getColumnIndex(KEY_NUM_TEAMS);
        int created_at = cursor.getColumnIndex(KEY_CREATED);

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

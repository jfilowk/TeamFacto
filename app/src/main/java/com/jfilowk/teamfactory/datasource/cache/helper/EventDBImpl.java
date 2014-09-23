package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDBImpl extends DatabaseHelper implements EventDB {

    public EventDBImpl(Context context) {
        super(context);
    }


    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Cursor getAllEvents() {
        return null;
    }

    private ContentValues bindEvent (Event event){



        return null;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

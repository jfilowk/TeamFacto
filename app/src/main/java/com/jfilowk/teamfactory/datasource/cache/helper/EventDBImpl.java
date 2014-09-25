package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public long createEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        long lEvent = db.insert(DBConstants.CREATE_TABLE_EVENT, null, bindEvent(event));
        return lEvent;
    }

    @Override
    public Cursor getAllEvents() {
        String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_EVENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUsers = db.rawQuery(selectQuery, null);

        return cursorUsers;
    }

    private ContentValues bindEvent(Event event) {

        ContentValues values = new ContentValues();

        values.put("type", event.getType());
        values.put("type", event.getNumUser());
        values.put("type", event.getNumTeams());
        values.put("type", event.getCreated_at().toString());

        return values;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

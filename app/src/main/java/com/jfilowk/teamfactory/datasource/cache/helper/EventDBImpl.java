package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jfilowk.teamfactory.datasource.entities.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public Cursor getEvent(long id) {
        String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_EVENT + " WHERE " + DBConstants.KEY_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUsers = db.rawQuery(selectQuery, null);

        return cursorUsers;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        values.put("type", event.getType());
        values.put("num_users", event.getNumUser());
        values.put("num_teams", event.getNumTeams());
        values.put("created_at", dateFormat.format(date));

        return values;
    }
}

package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Javi on 22/09/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "randomUser";
    private static final String TABLE_RANDOM = "random";
    private static final String TABLE_EVENT = "event";
    private static final String TABLE_RANDOM_EVENT = "random_event";
    private static final String KEY_CREATED_AT = "created_at";

    private static final String CREATE_TABLE_RANDOM = "CREATE TABLE " + TABLE_RANDOM + "( seed TEXT PRIMARY KEY, name TEXT, picture TEXT, gender TEXT)";
    private static final String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE_EVENT + "(id TEXT PRIMARY KEY, type TEXT, num_users INTEGER, num_teams INTEGER, "+ KEY_CREATED_AT +" DATETIME)";
    private static final String CREATE_TABLE_RANDOM_EVENT = "CREATE TABLE " + TABLE_RANDOM_EVENT + "(id INTEGER PRIMARY KEY, seed TEXT, id_event TEXT)";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_RANDOM);
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_RANDOM_EVENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_RANDOM);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_RANDOM_EVENT);

        onCreate(db);
    }

    public long createRandomUser(RandomUser user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("seed", user.getSeed());
        values.put("name", user.getName());
        values.put("picture", user.getPicture());
        values.put("gender", user.getGender());

        // insert row
         long random_id = db.insert(TABLE_RANDOM, null, values);

        // assigning tags to todo
        //for (long tag_id : tag_ids) {
        //    createTodoTag(todo_id, tag_id);
        //}

        return random_id;
    }

    public List<RandomUser> getAllUsers() {
        List<RandomUser> users = new ArrayList<RandomUser>();
        String selectQuery = "SELECT  * FROM " + TABLE_RANDOM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                RandomUser randomUser = new RandomUser();
                randomUser.setSeed(c.getString((c.getColumnIndex("seec"))));
                randomUser.setName((c.getString(c.getColumnIndex("name"))));
                randomUser.setPicture(c.getString(c.getColumnIndex("picture")));
                randomUser.setGender(c.getString(c.getColumnIndex("gender")));

                // adding to todo list
                users.add(randomUser);
            } while (c.moveToNext());
        }

        return users;
    }

    public long createEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", event.getId());
        values.put("type", event.getType());
        values.put("num_users", event.getNumberPlayers());
        values.put("num_teams", event.getNumberTeams());
        values.put("created_at", getDateTime());

        long event_id = db.insert(TABLE_EVENT, null, values);

        return event_id;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

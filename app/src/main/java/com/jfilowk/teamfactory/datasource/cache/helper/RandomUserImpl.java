package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jfilowk.teamfactory.datasource.entities.RandomUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Javi on 22/09/14.
 */
public class RandomUserImpl extends DatabaseHelper implements RandomUserDB {

    public RandomUserImpl(Context context) {
        super(context);
    }

    public long createRandomUser(RandomUser user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert row
        long random_id = db.insert(DBConstants.TABLE_RANDOM, null, bindRandomUser(user));

        // assigning tags to todo
        //for (long tag_id : tag_ids) {
        //    createTodoTag(todo_id, tag_id);
        //}

        return random_id;
    }

    public Cursor getRandomUser(int id) {
        String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_RANDOM + " WHERE id = " + DBConstants.KEY_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUser = db.rawQuery(selectQuery, null);

        return cursorUser;
    }

    public Cursor getAllRandomUser() {
        String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_RANDOM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUsers = db.rawQuery(selectQuery, null);

        return cursorUsers;
    }

    private ContentValues bindRandomUser(RandomUser user) {

        ContentValues values = new ContentValues();
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("gender", user.getGender());
        values.put("picture", user.getPicture());
        values.put("seed", user.getSeed());
        values.put("created_at", user.getCreated_at().toString());

        return values;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

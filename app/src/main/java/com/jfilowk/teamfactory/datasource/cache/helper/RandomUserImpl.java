package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.Context;
import android.database.Cursor;

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

    @Override
    public Cursor getAllRandomUser() {
        return null;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

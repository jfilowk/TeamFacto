package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Javi on 22/09/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBConstants.CREATE_TABLE_RANDOM);
        db.execSQL(DBConstants.CREATE_TABLE_EVENT);
        db.execSQL(DBConstants.CREATE_TABLE_TEAM);
        db.execSQL(DBConstants.CREATE_TABLE_RANDOM_EVENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_RANDOM_EVENT);

        onCreate(db);
    }
}
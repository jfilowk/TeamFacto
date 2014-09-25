package com.jfilowk.teamfactory.datasource.cache.helper;

/**
 * Created by Javi on 23/09/14.
 */
public class DBConstants {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "randomUser";
    public static final String TABLE_RANDOM = "random";
    public static final String TABLE_EVENT = "event";
    public static final String TABLE_RANDOM_EVENT = "random_event";
    public static final String KEY_CREATED_AT = "created_at";

    public static final String KEY_ID = "id";

    public static final String CREATE_TABLE_RANDOM = "CREATE TABLE " + TABLE_RANDOM + "(id INTEGER PRIMARY KEY AUTOINCREMENT, seed TEXT, name TEXT, picture TEXT, gender TEXT)";
    public static final String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE_EVENT + "(id TEXT PRIMARY KEY AUTOINCREMENT, type TEXT, num_users INTEGER, num_teams INTEGER, " + KEY_CREATED_AT + " DATETIME)";
    public static final String CREATE_TABLE_RANDOM_EVENT = "CREATE TABLE " + TABLE_RANDOM_EVENT + "(id INTEGER PRIMARY KEY AUTOINCREMENT, seed TEXT, id_event TEXT)";
}

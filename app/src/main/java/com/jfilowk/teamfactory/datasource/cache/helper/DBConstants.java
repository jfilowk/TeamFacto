package com.jfilowk.teamfactory.datasource.cache.helper;

/**
 * Created by Javi on 23/09/14.
 */
public class DBConstants {

    public static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "randomUser";
    public static final String TABLE_USER = "user";
    public static final String TABLE_EVENT = "event";
    public static final String TABLE_TEAM = "team";
    public static final String TABLE_RANDOM_EVENT = "user_event";
    public static final String TABLE_EVENT_TEAM = "event_team";
    public static final String KEY_CREATED_AT = "created_at";

    public static final String KEY_ID = "id";

    public static final String CREATE_TABLE_RANDOM = "CREATE TABLE " + TABLE_USER + "(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT, picture TEXT, gender TEXT, seed TEXT, team_id INTEGER, " + KEY_CREATED_AT + " DATETIME)";
    public static final String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE_EVENT + "(id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, num_users INTEGER, num_teams INTEGER, " + KEY_CREATED_AT + " DATETIME)";
    public static final String CREATE_TABLE_TEAM = "CREATE TABLE " + TABLE_TEAM + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, " + KEY_CREATED_AT + " DATETIME)";
    public static final String CREATE_TABLE_RANDOM_EVENT = "CREATE TABLE " + TABLE_RANDOM_EVENT + "(id INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, id_event INTEGER, " + KEY_CREATED_AT + " DATETIME)";
    public static final String CREATE_TABLE_TEAM_EVENT = "CREATE TABLE " + TABLE_EVENT_TEAM + "(id INTEGER PRIMARY KEY AUTOINCREMENT, id_team INTEGER, id_event INTEGER, " + KEY_CREATED_AT + " DATETIME)";
}

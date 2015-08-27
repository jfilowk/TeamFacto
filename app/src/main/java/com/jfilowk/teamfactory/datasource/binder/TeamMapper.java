package com.jfilowk.teamfactory.datasource.binder;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Team;

/**
 * Created by Javi on 02/10/14.
 */
public class TeamMapper {

    private static String KEY_ID = "id";
    private static String KEY_FIRST_NAME = "name";

    public TeamMapper() {
    }

    public Team transformCursorToTeam(Cursor cursor) {
        Team team = new Team();

        int id = cursor.getColumnIndex(KEY_ID);
        int name = cursor.getColumnIndex(KEY_FIRST_NAME);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            team.setId(cursor.getInt(id));
            team.setName(cursor.getString(name));

        }


        return team;
    }
}
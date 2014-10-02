package com.jfilowk.teamfactory.datasource.cache.helper;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Team;

/**
 * Created by Javi on 30/09/14.
 */
public interface TeamDB {
    public long createTeam (Team team);
    public Cursor getTeam (long idTeam);
    public Cursor getTeams ();

}

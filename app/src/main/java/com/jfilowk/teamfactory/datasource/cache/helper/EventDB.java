package com.jfilowk.teamfactory.datasource.cache.helper;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventDB {
    public long createEvent (Event event);
    public long createEventUser (long idEvent, long idUser);
    public long createEventTeam (long idEvent, long idTeam);
    public Cursor getIdUserEvent (long idEvent);
    public Cursor getIdTeamsEvent (long idEvent);
    public Cursor getEvent(long id);
    public int deleteEvent(long id);
    public Cursor getAllEvents ();
    public void closeDb ();
}

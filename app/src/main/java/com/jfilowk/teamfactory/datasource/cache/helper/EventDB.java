package com.jfilowk.teamfactory.datasource.cache.helper;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventDB {
    public long createEvent (Event event);
    public Cursor getAllEvents ();
}

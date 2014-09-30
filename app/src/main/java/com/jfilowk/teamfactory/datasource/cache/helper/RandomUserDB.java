package com.jfilowk.teamfactory.datasource.cache.helper;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.RandomUser;

/**
 * Created by Javi on 22/09/14.
 */
public interface RandomUserDB {
    public long createRandomUser(RandomUser user, long teamId);
    public Cursor getAllRandomUser ();
}


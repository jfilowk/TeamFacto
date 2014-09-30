package com.jfilowk.teamfactory.datasource.callbacks;

import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 22/09/14.
 */

public interface EventCallback {
    public void onSuccess (EventCollection collection);
    public void onError ();

}

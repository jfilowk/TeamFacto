package com.jfilowk.teamfactory.datasource.callbacks;

import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;

/**
 * Created by Javi on 22/09/14.
 */

public interface EventCallback {
    public void onSuccess (RandomUserCollection collection);
    public void onError ();

}

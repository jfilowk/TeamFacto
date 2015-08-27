package com.jfilowk.teamfactory.datasource.callbacks;

import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;

/**
 * Created by Javi on 19/09/14.
 */
public interface RandomUserCallback {
  public void onSuccess (RandomUserCollection collection);
  public void onError ();
}

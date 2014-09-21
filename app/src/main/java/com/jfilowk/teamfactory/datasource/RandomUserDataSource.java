package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;

/**
 * Created by Jose Luis on 19/09/14.
 */
public interface RandomUserDataSource {
  public void getRandomUser(RandomUserCallback callback);
}

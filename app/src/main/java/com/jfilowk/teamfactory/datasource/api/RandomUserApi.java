package com.jfilowk.teamfactory.datasource.api;

import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;

/**
 * Created by Javi on 19/09/14.
 */
public interface RandomUserApi {
  public void getRandomUserApi(int numUsers, RandomUserApiCallback callback);

  public void getRandomUserApiUser(RandomUserApiCallback callback);
}

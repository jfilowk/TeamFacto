package com.jfilowk.teamfactory.datasource.api;

import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;

/**
 * Created by Javi on 19/09/14.
 */
public interface RandomUserApiAsync {
  void getRandomUserApi(int numUsers, RandomUserApiCallback callback);

  void getRandomUserApiUser(RandomUserApiCallback callback);
}

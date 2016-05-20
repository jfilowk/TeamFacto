package com.jfilowk.teamfactory.datasource.api.callback;

import com.terro.entities.UserRandomResponse;

/**
 * Created by Javi on 19/09/14.
 */
public interface RandomUserApiCallback {
  void onSuccess(UserRandomResponse response);

  void onError();
}

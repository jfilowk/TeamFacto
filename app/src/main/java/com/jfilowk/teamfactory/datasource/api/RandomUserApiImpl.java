package com.jfilowk.teamfactory.datasource.api;

import com.terro.entities.UserRandomResponse;
import com.terro.services.UserService;
import javax.inject.Inject;
import retrofit.RetrofitError;

public class RandomUserApiImpl implements RandomUserApi {

  private UserService userService;

  @Inject
  public RandomUserApiImpl(UserService userService) {
    this.userService = userService;
  }

  @Override public UserRandomResponse getRandomUser(int numberPlayer) {
    UserRandomResponse userRandomResponse;
    try {
      userRandomResponse = userService.listUsers(numberPlayer);
    } catch (RetrofitError error) {
      userRandomResponse = null;
    }

    return userRandomResponse;
  }
}

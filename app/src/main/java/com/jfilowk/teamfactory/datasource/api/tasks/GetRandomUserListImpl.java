package com.jfilowk.teamfactory.datasource.api.tasks;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.terro.entities.UserRandomResponse;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class GetRandomUserListImpl implements GetRandomUserList, Callable {

  private RandomUserApi randomUserApi;
  private int numberPlayers;

  @Inject public GetRandomUserListImpl(RandomUserApi randomUserApi, int numberPlayers) {
    this.randomUserApi = randomUserApi;
    this.numberPlayers = numberPlayers;
  }

  @Override public UserRandomResponse call() throws Exception {
    return getRandomUserList(numberPlayers);
  }

  @Override public UserRandomResponse getRandomUserList(int numberUsers) {
    return randomUserApi.getRandomUser(numberUsers);
  }
}

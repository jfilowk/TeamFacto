package com.jfilowk.teamfactory.datasource.api;

import com.terro.entities.UserRandomResponse;

public interface RandomUserApi {
  UserRandomResponse getRandomUser(int numberPlayer);
}

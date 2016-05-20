package com.jfilowk.teamfactory.datasource.api.tasks;

import com.terro.entities.UserRandomResponse;
import java.util.concurrent.Callable;

public interface GetRandomUserList extends Callable {

  UserRandomResponse getRandomUserList(int numberUsers);
}

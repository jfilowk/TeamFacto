package com.jfilowk.teamfactory.datasource.api;

import com.jfilowk.teamfactory.BuildConfig;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.terro.RandomUser;
import com.terro.entities.UserRandomResponse;
import com.terro.services.UserServiceAsync;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by Javi on 19/09/14.
 */
public class RandomUserApiImpl implements RandomUserApi {

  private UserServiceAsync userAsync;

  public RandomUserApiImpl() {
    RandomUser randomUser = new RandomUser();
    if (BuildConfig.DEBUG) randomUser.setIsDebug(true);

    this.userAsync = randomUser.userServicesAsync();
  }

  @Override public void getRandomUserApi(int numUsers, final RandomUserApiCallback callback) {
    userAsync.listUserAsync(numUsers, new Callback<UserRandomResponse>() {
      @Override public void success(UserRandomResponse userRandomResponse, Response response) {
        callback.onSuccess(userRandomResponse);
      }

      @Override public void failure(RetrofitError error) {
        Timber.e(error.toString());
        callback.onError();
      }
    });
  }

  @Override public void getRandomUserApiUser(final RandomUserApiCallback callback) {
    userAsync.listUserAsync(20, new Callback<UserRandomResponse>() {
      @Override public void success(UserRandomResponse userRandomResponse, Response response) {
        callback.onSuccess(userRandomResponse);
      }

      @Override public void failure(RetrofitError error) {
        callback.onError();
      }
    });
  }
}

package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImp;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.cache.RandomUserCache;
import com.jfilowk.teamfactory.datasource.cache.RandomUserCacheImp;
import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCacheCallback;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class RandomUserDataSourceImp implements RandomUserDataSource {

  private RandomUserCache cache;
  private RandomUserApi api;

  public RandomUserDataSourceImp() {
    this.cache = new RandomUserCacheImp();
    this.api = new RandomUserApiImp();
  }

  @Override public void getRandonUser(final RandomUserCallback callback) {

    this.cache.getRandomUserCache(new RandomUserCacheCallback() {
      @Override public void onSuccess() {
          callback.onSuccess(null);

        api.getRandomUserApi(new RandomUserApiCallback() {
          @Override public void onSuccess(Object object) {
            callback.onSuccess(object);
          }

          @Override public void onError() {

          }
        });
      }

      @Override public void onError() {
      }
    });

  }
}

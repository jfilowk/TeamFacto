package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImpl;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.cache.RandomUserCache;
import com.jfilowk.teamfactory.datasource.cache.RandomUserCacheImp;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;
import com.terro.entities.UserRandomResponse;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class RandomUserDataSourceImp implements RandomUserDataSource {

    private RandomUserCache cache;
    private RandomUserApi api;

    public RandomUserDataSourceImp() {
        this.cache = new RandomUserCacheImp();
        this.api = new RandomUserApiImpl();
    }

    @Override
    public void getRandomUser(final RandomUserCallback callback) {

        this.api.getRandomUserApi(new RandomUserApiCallback() {
            @Override
            public void onSuccess(UserRandomResponse response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError() {

            }
        });

    }
}

package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImpl;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.cache.RandomUserCache;
import com.jfilowk.teamfactory.datasource.cache.RandomUserCacheImp;
import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.terro.entities.UserRandomResponse;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class RandomUserDataSourceImpl implements RandomUserDataSource {

    private RandomUserCache cache;
    private RandomUserApi api;
    private RandomUserMapper mapper;

    public RandomUserDataSourceImpl() {
        this.cache = new RandomUserCacheImp();
        this.api = new RandomUserApiImpl();
        this.mapper = new RandomUserMapper();
    }

    @Override
    public void createRandomUser(RandomUser user,RandomUserCallbackBase callback) {
        boolean response = cache.createUser(user, 1);
        if (response) {
            callback.onSuccess();
        } else {
            callback.onError();
        }
    }

    @Override
    public void getRandomUser(final RandomUserCallback callback) {

        /*this.cache.getRandomUserCache(new RandomUserCacheCallback() {
            @Override
            public void onSuccess(List<RandomUser> response) {
                RandomUserCollection collection = mapper.transformCacheToRandomUserCollection(response);
                callback.onSuccess(collection);
            }

            @Override
            public void onError() {

            }
        });*/

        this.api.getRandomUserApi(new RandomUserApiCallback() {
            @Override
            public void onSuccess(UserRandomResponse response) {
                RandomUserCollection collection = mapper.transformResultToRandomUserCollection(response);
                callback.onSuccess(collection);
            }

            @Override
            public void onError() {

            }
        });

    }
}

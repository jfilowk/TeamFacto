package com.jfilowk.teamfactory.datasource.api;

import com.jfilowk.teamfactory.BuildConfig;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiEventCallback;
import com.terro.RandomUser;
import com.terro.entities.UserRandomResponse;
import com.terro.services.UserServiceAsync;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Javi on 25/09/14.
 */
public class RandomUserApiEventImpl implements RandomUserApiEvent {

    private UserServiceAsync userAsync;

    public RandomUserApiEventImpl() {
        RandomUser randomUser = new RandomUser();
        if (BuildConfig.DEBUG) randomUser.setIsDebug(true);

        this.userAsync = randomUser.userServicesAsync();
    }

    @Override
    public void getRandomUsers(final RandomUserApiEventCallback callback) {
        this.userAsync.listUserAsync(40, new Callback<UserRandomResponse>() {
            @Override
            public void success(UserRandomResponse userRandomResponse, Response response) {
                callback.onSuccess(userRandomResponse);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    };
}

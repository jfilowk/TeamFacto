package com.jfilowk.teamfactory.datasource.api;

import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiEventCallback;

/**
 * Created by Javi on 25/09/14.
 */
public interface RandomUserApiEvent {
    public void getRandomUsers(RandomUserApiEventCallback callback);
}

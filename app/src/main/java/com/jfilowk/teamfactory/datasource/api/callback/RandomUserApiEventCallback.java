package com.jfilowk.teamfactory.datasource.api.callback;

import com.terro.entities.UserRandomResponse;

/**
 * Created by Javi on 25/09/14.
 */
public interface RandomUserApiEventCallback {
    public void onSuccess(UserRandomResponse response);

    public void onError();
}

package com.jfilowk.teamfactory.datasource.api.callback;

import com.terro.entities.UserRandomResponse;

/**
 * Created by Javi on 19/09/14.
 */
public interface RandomUserApiCallback {
    public void onSuccess(UserRandomResponse response);

    public void onError();
}

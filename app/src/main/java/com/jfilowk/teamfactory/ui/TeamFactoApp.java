package com.jfilowk.teamfactory.ui;

import android.app.Application;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class TeamFactoApp extends Application {

    private static TeamFactoApp instance;
    public static TeamFactoApp get() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}

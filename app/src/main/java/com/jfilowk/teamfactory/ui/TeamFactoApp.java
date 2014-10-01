package com.jfilowk.teamfactory.ui;

import android.app.Application;
import android.util.Log;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class TeamFactoApp extends Application {

    private static TeamFactoApp instance;
    private JobManager jobManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        configureJobManager();
    }

    public static TeamFactoApp get() { return instance; }

    private void configureJobManager(){
        Configuration configuration = new Configuration.Builder(this).customLogger(new CustomLogger() {
            private static final String TAG = "JOBS";
            @Override
            public boolean isDebugEnabled() {

                return true;
            }

            @Override
            public void d(String text, Object... args) {
                Log.d(TAG, String.format(text, args));
            }

            @Override
            public void e(Throwable t, String text, Object... args) {
                Log.e(TAG, String.format(text, args), t);
            }

            @Override
            public void e(String text, Object... args) {
                Log.e(TAG, String.format(text, args));
            }
        })
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .consumerKeepAlive(120)
                .build();
        jobManager = new JobManager(this, configuration);
    }

    public JobManager getJobManager(){
        return jobManager;
    }
}

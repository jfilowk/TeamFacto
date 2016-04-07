package com.jfilowk.teamfactory.ui;

import android.app.Application;
import android.util.Log;
import com.jfilowk.teamfactory.BuildConfig;
import com.jfilowk.teamfactory.internal.di.component.ApplicationComponent;
import com.jfilowk.teamfactory.internal.di.component.DaggerApplicationComponent;
import com.jfilowk.teamfactory.internal.di.module.ApplicationModule;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;
import timber.log.Timber;

/**
 * Created by Javi on 19/09/14.
 */
public class TeamFactoApp extends Application {

  private static TeamFactoApp instance;
  private JobManager jobManager;
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeDagger();
    instance = this;
    configureJobManager();
    configureTimber();
  }

  public static TeamFactoApp get() {
    return instance;
  }

  private void configureTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Timber.plant(new CrashReportingTree());
    }
  }

  private void initializeDagger() {
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  private void configureJobManager() {
    Configuration configuration = new Configuration.Builder(this).customLogger(new CustomLogger() {
      private static final String TAG = "JOBS";

      @Override public boolean isDebugEnabled() {

        return true;
      }

      @Override public void d(String text, Object... args) {
        Log.d(TAG, String.format(text, args));
      }

      @Override public void e(Throwable t, String text, Object... args) {
        Log.e(TAG, String.format(text, args), t);
      }

      @Override public void e(String text, Object... args) {
        Log.e(TAG, String.format(text, args));
      }
    }).minConsumerCount(1).maxConsumerCount(3).loadFactor(3).consumerKeepAlive(120).build();
    jobManager = new JobManager(this, configuration);
  }

  public JobManager getJobManager() {
    return jobManager;
  }

  private static class CrashReportingTree extends Timber.HollowTree {
    @Override public void i(String message, Object... args) {
    }

    @Override public void i(Throwable t, String message, Object... args) {
      i(message, args); // Just add to the log.
    }

    @Override public void e(String message, Object... args) {
      i("ERROR: " + message, args); // Just add to the log.
    }

    @Override public void e(Throwable t, String message, Object... args) {
      e(message, args);

    }
  }
}

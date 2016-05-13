package com.jfilowk.teamfactory.ui;

import android.app.Application;
import com.jfilowk.teamfactory.BuildConfig;
import com.jfilowk.teamfactory.internal.di.component.ApplicationComponent;
import com.jfilowk.teamfactory.internal.di.component.DaggerApplicationComponent;
import com.jfilowk.teamfactory.internal.di.module.ApplicationModule;
import timber.log.Timber;

/**
 * Created by Javi on 19/09/14.
 */
public class TeamFactoApp extends Application {

  private static TeamFactoApp instance;
  private ApplicationComponent applicationComponent;

  public static TeamFactoApp get() {
    return instance;
  }

  @Override public void onCreate() {
    super.onCreate();
    initializeDagger();
    instance = this;
    configureTimber();
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

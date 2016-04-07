package com.jfilowk.teamfactory.internal.di.module;

import android.app.Activity;
import com.jfilowk.teamfactory.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
  private Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity Activity provideActivity() {
    return this.activity;
  }
}

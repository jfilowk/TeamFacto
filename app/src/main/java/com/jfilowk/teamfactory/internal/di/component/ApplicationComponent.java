package com.jfilowk.teamfactory.internal.di.component;

import android.content.Context;
import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.internal.di.module.ApplicationModule;
import com.jfilowk.teamfactory.ui.TeamFactoApp;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  void inject(TeamFactoApp teamFactoApp);

  Context applicationContext();

  EventDataSource eventDataSource();
}

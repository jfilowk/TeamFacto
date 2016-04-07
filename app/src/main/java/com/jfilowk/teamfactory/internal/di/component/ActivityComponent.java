package com.jfilowk.teamfactory.internal.di.component;

import android.app.Activity;
import com.jfilowk.teamfactory.internal.di.PerActivity;
import com.jfilowk.teamfactory.internal.di.module.ActivityModule;
import com.jfilowk.teamfactory.ui.activity.BaseActivity;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  void inject(BaseActivity baseActivity);

  Activity getActivity();
}

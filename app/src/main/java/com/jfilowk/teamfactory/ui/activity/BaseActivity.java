package com.jfilowk.teamfactory.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.jfilowk.teamfactory.internal.di.component.ApplicationComponent;
import com.jfilowk.teamfactory.internal.di.module.ActivityModule;
import com.jfilowk.teamfactory.ui.TeamFactoApp;

public abstract class BaseActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public ApplicationComponent getApplicationComponent() {
    return ((TeamFactoApp) getApplication()).getApplicationComponent();
  }

  public ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}

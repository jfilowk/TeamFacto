package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.jfilowk.teamfactory.internal.di.component.ApplicationComponent;
import com.jfilowk.teamfactory.ui.TeamFactoApp;

public abstract class BaseFragment extends Fragment {

  public ApplicationComponent getApplicationComponent(Activity activity) {
    return ((TeamFactoApp) activity.getApplication()).getApplicationComponent();
  }
}

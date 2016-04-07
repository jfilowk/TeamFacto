package com.jfilowk.teamfactory.internal.di.module;

import android.app.Activity;
import com.jfilowk.teamfactory.internal.di.PerActivity;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenterImpl;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.presenter.GenerateTeamPresenterImpl;
import com.jfilowk.teamfactory.ui.presenter.HomeActivityPresenter;
import com.jfilowk.teamfactory.ui.presenter.HomeActivityPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {
  private Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity Activity provideActivity() {
    return this.activity;
  }

  @Provides @PerActivity FragmentGenerateTeamPresenter provideFragmentGenerateTeamPresenter(
      FragmentGenerateTeamPresenterImpl fragmentGenerateTeamPresenter) {
    return fragmentGenerateTeamPresenter;
  }

  @Provides @PerActivity GenerateTeamPresenter provideGenerateTeamPresenter(
      GenerateTeamPresenterImpl generateTeamPresenter) {
    return generateTeamPresenter;
  }

  @Provides @PerActivity HomeActivityPresenter provideHomeActivityPresenter(
      HomeActivityPresenterImpl homeActivityPresenter) {
    return homeActivityPresenter;
  }
}

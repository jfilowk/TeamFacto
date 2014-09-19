package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.RandomUserDataSource;
import com.jfilowk.teamfactory.datasource.RandomUserDataSourceImp;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;
import com.jfilowk.teamfactory.ui.views.HomeActivityView;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class HomeActivityPresenterImp implements HomeActivityPresenter {

  private RandomUserDataSource dataSource;
  private HomeActivityView view;

  public HomeActivityPresenterImp(HomeActivityView view) {
    this.dataSource = new RandomUserDataSourceImp();
    this.view = view;
  }

  @Override public void onResume() {
    this.view.initProgressFragment();
    this.dataSource.getRandonUser(new RandomUserCallback() {
      @Override public void onSuccess(Object object) {
        view.initMainFragment(object);
      }

      @Override public void onError() {
        // view.errorMessage ();
      }
    });
  }
}

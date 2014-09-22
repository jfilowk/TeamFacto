package com.jfilowk.teamfactory.ui.presenter;

import android.text.TextUtils;

import com.jfilowk.teamfactory.datasource.RandomUserDataSource;
import com.jfilowk.teamfactory.datasource.RandomUserDataSourceImp;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
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

    @Override
    public void onResume() {
        this.view.initProgressFragment();
        this.dataSource.getRandomUser(new RandomUserCallback() {
            @Override
            public void onSuccess(RandomUserCollection collection) {
                view.initMainFragment(collection);
                System.out.println("Hola. Vengo a imprimir el primer elemento " + TextUtils.getCapsMode(collection.get(1).getName(), 0, TextUtils.CAP_MODE_WORDS));
            }

            @Override
            public void onError() {
                // view.errorMessage ();
            }
        });
    }

    @Override
    public void selectTeam() {
        this.view.initSelectTeamFragment();


    }
}

package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.RandomUserDataSource;
import com.jfilowk.teamfactory.datasource.RandomUserDataSourceImpl;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.ui.views.HomeActivityView;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class HomeActivityPresenterImpl implements HomeActivityPresenter {

    private RandomUserDataSource randomdataSource;
    private EventDataSource eventDataSource;
    private HomeActivityView view;

    public HomeActivityPresenterImpl(HomeActivityView view) {
        this.randomdataSource = new RandomUserDataSourceImpl();
        this.eventDataSource = new EventDataSourceImpl();
        this.view = view;
    }

    @Override
    public void onResume() {
        this.view.initProgressFragment();

        this.eventDataSource.getAllEvents(new EventCallback() {
            @Override
            public void onSuccess(EventCollection collection) {
                view.initMainFragment(collection);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public void selectTeam() {
        this.view.initSelectTeamFragment();
    }
}

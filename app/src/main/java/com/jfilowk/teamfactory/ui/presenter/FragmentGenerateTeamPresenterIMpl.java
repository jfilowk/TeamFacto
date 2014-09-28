package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.ui.views.FragmentGenerateTeamView;

/**
 * Created by Javi on 26/09/14.
 */
public class FragmentGenerateTeamPresenterImpl implements FragmentGenerateTeamPresenter{

    FragmentGenerateTeamView view;
    private EventDataSource eventDataSource;

    public FragmentGenerateTeamPresenterImpl(FragmentGenerateTeamView view) {
        this.view = view;
        eventDataSource = new EventDataSourceImpl();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showTeams() {
        eventDataSource.showEvent(new EventCallback() {
            @Override
            public void onSuccess(EventCollection collection) {
                view.initListView(collection);
            }

            @Override
            public void onError() {

            }
        });
    }
}

package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.RandomUserDataSource;
import com.jfilowk.teamfactory.datasource.RandomUserDataSourceImpl;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.datasource.jobs.CreateEventJob;
import com.jfilowk.teamfactory.ui.TeamFactoApp;
import com.jfilowk.teamfactory.ui.adapters.ListTeamsAdapter;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.views.FragmentGenerateTeamView;
import com.path.android.jobqueue.JobManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 25/09/14.
 */
public class FragmentGenerateTeam extends Fragment implements FragmentGenerateTeamView {

    @InjectView(R.id.listViewTeams)
    ListView listViewTeams;
    Activity activity;
    FragmentGenerateTeamPresenter presenter;
    EventCollection eventCollection;
    EventDataSource eventDataSource;
    RandomUserDataSource randomUserDataSource;
    Event event;
    JobManager jobManager;

    public FragmentGenerateTeam() {
        this.eventDataSource = new EventDataSourceImpl();
        this.randomUserDataSource = new RandomUserDataSourceImpl();
        this.event = new Event();
        jobManager = TeamFactoApp.get().getJobManager();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventCollection = new EventCollection();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.generate_team_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.accept_event) {
            jobManager.addJobInBackground(new CreateEventJob(this.event));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generate_team, null);
        ButterKnife.inject(this, root);
        this.eventDataSource.showEvent(new EventCallback() {
            @Override
            public void onSuccess(EventCollection collection) {
                eventCollection = collection;
                initListView(eventCollection);
            }

            @Override
            public void onError() {

            }
        });
        return root;
    }

    @Override
    public void initListView(EventCollection collection) {
        List<Object> objectList = new ArrayList<Object>();
        List<Event> eventList = collection.getCollection();
        for (Event event : eventList) {
            this.event = event;
            List<Team> teamList = event.getListTeams().getCollection();
            for (Team team : teamList) {
                objectList.add(team);
                List<RandomUser> users = team.getUserCollection().getCollection();
                for (RandomUser user : users) {
                    objectList.add(user);
                }
            }
        }
        System.out.println(objectList.size());
        ListTeamsAdapter adapter = new ListTeamsAdapter(activity.getApplicationContext(), objectList);
        listViewTeams.setAdapter(adapter);
    }
}

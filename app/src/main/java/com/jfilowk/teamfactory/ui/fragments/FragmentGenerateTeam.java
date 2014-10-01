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
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.ui.adapters.ListTeamsAdapter;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenterImpl;
import com.jfilowk.teamfactory.ui.views.FragmentGenerateTeamView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 25/09/14.
 */
public class FragmentGenerateTeam extends Fragment implements FragmentGenerateTeamView {

    private static final String KEY_EVENT = "key_event";

    @InjectView(R.id.listViewTeams) ListView listViewTeams;
    private Activity activity;
    private FragmentGenerateTeamPresenter presenter;
    private EventCollection eventCollection;
    private Event event;

    public static FragmentGenerateTeam newInstance (EventCollection collection) {
        FragmentGenerateTeam fragmentGenerateTeam = new FragmentGenerateTeam();

        Bundle data = new Bundle();
        data.putSerializable(KEY_EVENT, collection);
        fragmentGenerateTeam.setArguments(data);

        return fragmentGenerateTeam;
    }

    public FragmentGenerateTeam() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        this.event = new Event();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventCollection = new EventCollection();
        presenter = new FragmentGenerateTeamPresenterImpl(this);
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
            presenter.createEvent(event);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generate_team, null);
        ButterKnife.inject(this, root);
        this.eventCollection = (EventCollection) getArguments().getSerializable(KEY_EVENT);
        presenter.showTeams(this.eventCollection);
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
        ListTeamsAdapter adapter = new ListTeamsAdapter(activity.getApplicationContext(), objectList);
        listViewTeams.setAdapter(adapter);

    }
}

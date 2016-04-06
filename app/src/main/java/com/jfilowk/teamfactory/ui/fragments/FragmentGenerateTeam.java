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
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.ui.adapters.ListTeamsAdapter;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Javi on 25/09/14.
 */
public class FragmentGenerateTeam extends Fragment
    implements FragmentGenerateTeamPresenter.FragmentGenerateTeamView {

  private static final String KEY_EVENT = "key_event";

  @InjectView(R.id.listViewTeams) DynamicListView listViewTeams;
  private Activity activity;
  @Inject FragmentGenerateTeamPresenter presenter;
  private Event event;

  public FragmentGenerateTeam() {
  }

  public static FragmentGenerateTeam newInstance(Event collection) {
    FragmentGenerateTeam fragmentGenerateTeam = new FragmentGenerateTeam();
    Bundle data = new Bundle();
    data.putSerializable(KEY_EVENT, collection);
    fragmentGenerateTeam.setArguments(data);

    return fragmentGenerateTeam;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    this.activity = activity;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.event = new Event();
    presenter.attachView(this);
    setHasOptionsMenu(true);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    if (!event.getListTeams().getCollection().isEmpty()) {
      inflater.inflate(R.menu.generate_team_menu, menu);
    }
  }

  @Override public void onPrepareOptionsMenu(Menu menu) {
    MenuItem save = menu.findItem(R.id.accept_event);
    if (event.getListTeams().getCollection().get(0).getId() != 0) save.setVisible(false);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.accept_event) {
      presenter.createEvent(event);
      activity.finish();
      return true;
    } else if (id == android.R.id.home) {
      activity.onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_generate_team, null);
    ButterKnife.inject(this, root);
    this.event = (Event) getArguments().getSerializable(KEY_EVENT);
    presenter.showTeams(this.event);
    return root;
  }

  @Override public void initListView(Event event) {
    final List<Object> objectList = new ArrayList<Object>();
    this.event = event;
    List<Team> teamList = event.getListTeams().getCollection();
    for (Team team : teamList) {
      objectList.add(team);
      List<RandomUser> users = team.getUserCollection().getCollection();
      for (RandomUser user : users) {
        objectList.add(user);
      }
    }

    ListTeamsAdapter adapter = new ListTeamsAdapter(activity.getApplicationContext(), objectList);
    AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
    animationAdapter.setAbsListView(listViewTeams);
    listViewTeams.setAdapter(animationAdapter);
  }

  @Override public void initFragmentError() {
    Toast.makeText(activity.getApplicationContext(), "Error, Check your connection",
        Toast.LENGTH_SHORT).show();
    getActivity().finish();
  }
}

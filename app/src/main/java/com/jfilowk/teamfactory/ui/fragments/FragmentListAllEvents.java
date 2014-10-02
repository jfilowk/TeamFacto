package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.ui.activity.GenerateTeam;
import com.jfilowk.teamfactory.ui.adapters.ListEventsAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 01/10/14.
 */
public class FragmentListAllEvents extends Fragment {

    private static String KEY_EVENT = "event";

    @InjectView(R.id.listAllEvents)
    ListView listAllEvents;

    private Activity activity;
    private EventDataSource eventDataSource;

    public static FragmentListAllEvents newInstance(EventCollection collection) {
        FragmentListAllEvents fragmentListAllEvents = new FragmentListAllEvents();
        Bundle data = new Bundle();
        data.putSerializable(KEY_EVENT, collection);
        fragmentListAllEvents.setArguments(data);

        return fragmentListAllEvents;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.eventDataSource = new EventDataSourceImpl();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_all_events, null);
        ButterKnife.inject(this, viewRoot);
        final EventCollection eventCollection = (EventCollection) getArguments().getSerializable(KEY_EVENT);
        final List<Event> eventList = eventCollection.getCollection();
        ListEventsAdapter adapter = new ListEventsAdapter(eventList, activity.getApplicationContext());
        listAllEvents.setAdapter(adapter);

        listAllEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eventDataSource.getEvent(eventCollection.get(position), new AnEventCacheCallback() {
                    @Override
                    public void onSuccess(final Event event) {
                        Intent i = new Intent(activity.getApplicationContext(), GenerateTeam.class);
                        i.putExtra(KEY_EVENT, event);
                        startActivity(i);
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });

        return viewRoot;
    }
}

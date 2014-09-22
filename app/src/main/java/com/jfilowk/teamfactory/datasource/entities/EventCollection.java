package com.jfilowk.teamfactory.datasource.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 22/09/14.
 */
public class EventCollection {

    private List<Event> list;

    public EventCollection() {
        list = new ArrayList<Event>();
    }

    public Event get(int position) {
        return this.list.get(position);
    }

    public void add(Event event) {
        list.add(event);
    }

    public List<Event> getCollection() {
        return this.list;
    }
}

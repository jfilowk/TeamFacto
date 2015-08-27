package com.jfilowk.teamfactory.datasource.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 26/09/14.
 */
public class TeamCollection implements Serializable {

    private List<Team> list;

    public TeamCollection() {
        this.list = new ArrayList<Team>();
    }
    public Team get (int position) {
        return list.get(position);
    }

    public void add (Team team){
        list.add(team);
    }

    public List<Team> getCollection () {
        return this.list;
    }

}

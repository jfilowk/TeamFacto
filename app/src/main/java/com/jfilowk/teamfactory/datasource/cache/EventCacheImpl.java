package com.jfilowk.teamfactory.datasource.cache;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.binder.EventMapper;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.binder.TeamMapper;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDB;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDBImpl;
import com.jfilowk.teamfactory.datasource.cache.helper.RandomUserDB;
import com.jfilowk.teamfactory.datasource.cache.helper.RandomUserDBImpl;
import com.jfilowk.teamfactory.datasource.cache.helper.TeamDB;
import com.jfilowk.teamfactory.datasource.cache.helper.TeamDBImpl;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.datasource.entities.TeamCollection;
import com.jfilowk.teamfactory.ui.TeamFactoApp;

/**
 * Created by Javi on 22/09/14.
 */
public class EventCacheImpl implements EventCache {

    private static String KEY_ID_USER = "id_user";

    private EventDB eventDB;
    private TeamDB teamDB;
    private RandomUserDB randomUserDB;
    private EventMapper mapper;

    public EventCacheImpl() {
        init();
    }

    @Override
    public boolean createEvent(Event event) {
        long idEvent = eventDB.createEvent(event);
        if (idEvent != -1) {
            TeamCollection teams = event.getListTeams();
            for (int i = 0; i < teams.getCollection().size(); i++) {
                Team team = teams.get(i);
                long idTeam = teamDB.createTeam(team);
                if (idTeam != -1) {
                    RandomUserCollection users = team.getUserCollection();
                    for (int j = 0; j < users.getCollection().size(); j++) {
                        RandomUser user = users.get(j);
                        long idUser = randomUserDB.createRandomUser(user, idTeam);
                        if (idUser != -1) {
                            long idEventUser = eventDB.createEventUser(idEvent, idUser);
                            if (idEventUser != -1) {
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void getEvents(EventCacheCallback callback) {
        Cursor cursor = eventDB.getAllEvents();
        EventCollection eventCollection = mapper.transformEventCursorToEventCollection(cursor);
        if (eventCollection.getCollection().size() > 0) {
            callback.onSuccess(eventCollection);
        } else {
            callback.onError();
        }

    }

    @Override
    public void getEvent(Event eventSend, EventCallbackBase callback) {
        RandomUserMapper userMapper = new RandomUserMapper();
        TeamMapper teamMapper = new TeamMapper();
        RandomUserCollection collection = new RandomUserCollection();
        TeamCollection teamCollection = new TeamCollection();
        Event event = eventSend;


        Cursor idUsers = eventDB.getIdUserEvent(event.getId());
        int idUser = idUsers.getColumnIndex(KEY_ID_USER);
        for (idUsers.moveToFirst(); !idUsers.isAfterLast(); idUsers.moveToNext()) {
            idUsers.getLong(idUser);
            Cursor user = randomUserDB.getRandomUser(idUser);
            RandomUser randomUser = userMapper.transformCursorToRandomUser(user);
            collection.add(randomUser);
            int idTeamColumn = user.getColumnIndex("team_id");
            int idTeam = user.getInt(idTeamColumn);
            Cursor teamDB = this.teamDB.getTeam(idTeam);
            if(!teamCollection.getCollection().isEmpty()) {
                for (Team team : teamCollection.getCollection()){
                    if (team.getId() == idTeam){
                        team.getUserCollection().add(randomUser);
                    } else {
                        Team teamMap = teamMapper.transformCursorToTeam(teamDB);
                        teamMap.getUserCollection().add(randomUser);
                        teamCollection.add(teamMap);
                    }
                }
            } else {
                Team team = teamMapper.transformCursorToTeam(teamDB);
                team.getUserCollection().add(randomUser);
                teamCollection.add(team);
            }
        }

        event.setListTeams(teamCollection);


        if(event.getListTeams().getCollection().size() > 0) {
            callback.onSuccess();
        } else {
            callback.onError();
        }
    }

    public void init() {
        this.eventDB = new EventDBImpl(TeamFactoApp.get());
        this.teamDB = new TeamDBImpl(TeamFactoApp.get());
        this.randomUserDB = new RandomUserDBImpl(TeamFactoApp.get());
        this.mapper = new EventMapper();
    }
}

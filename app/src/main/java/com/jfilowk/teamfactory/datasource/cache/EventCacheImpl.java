package com.jfilowk.teamfactory.datasource.cache;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.binder.EventMapper;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.binder.TeamMapper;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.helper.DBConstants;
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

import timber.log.Timber;

/**
 * Created by Javi on 22/09/14.
 */
public class EventCacheImpl implements EventCache {

    private static String KEY_ID_TEAM = "id_team";

    private EventDB eventDB;
    private TeamDB teamDB;
    private RandomUserDB randomUserDB;
    private EventMapper eventMapper;
    private TeamMapper teamMapper;
    private RandomUserMapper randomUserMapper;

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
                    long idEventTeam = eventDB.createEventTeam(idEvent, idTeam);
                    if (idEventTeam != -1) {
                        RandomUserCollection users = team.getUserCollection();
                        for (int j = 0; j < users.getCollection().size(); j++) {
                            RandomUser user = users.get(j);
                            long idUser = randomUserDB.createRandomUser(user, idTeam);
                            if (idUser != -1) {
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            eventDB.closeDb();
            teamDB.closeDB();
            randomUserDB.closeDB();
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void getEvents(EventCacheCallback callback) {
        Cursor cursor = eventDB.getAllEvents();
        EventCollection eventCollection = eventMapper.transformEventCursorToEventCollection(cursor);
        eventDB.closeDb();
        if (eventCollection.getCollection().size() > 0) {
            callback.onSuccess(eventCollection);
        } else {
            callback.onError();
        }

    }

    @Override
    public void getEvent(Event eventSend, AnEventCacheCallback callback) {
        TeamCollection teamCollection = new TeamCollection();
        Event event = eventSend;

        Cursor idTeams = eventDB.getIdTeamsEvent(event.getId()); // fetch all users id from an event
        int idTeam = idTeams.getColumnIndex(KEY_ID_TEAM); // set column id
        for (idTeams.moveToFirst(); !idTeams.isAfterLast(); idTeams.moveToNext()) { // iterate to the users id cursor
            int idDB = idTeams.getInt(idTeam); // get id
            Cursor teamCursor = teamDB.getTeam(idDB);
            Team team = teamMapper.transformCursorToTeam(teamCursor);
            Cursor cursorUsersTeam = randomUserDB.getUsersTeam(team.getId());
            int userColumn = cursorUsersTeam.getColumnIndex(DBConstants.KEY_ID);
            for (cursorUsersTeam.moveToFirst(); !cursorUsersTeam.isAfterLast(); cursorUsersTeam.moveToNext()) {
                int userId = cursorUsersTeam.getInt(userColumn);
                Timber.d("la id del jugador " + userId);
                Cursor userCursor = randomUserDB.getRandomUser(userId);
                RandomUser user = randomUserMapper.transformCursorToRandomUser(userCursor);
                if (team.getId() == user.getTeam_id()){
                    team.getUserCollection().add(user);
                }
            }
            teamCollection.add(team);
        }
        event.setListTeams(teamCollection);
        eventDB.closeDb();
        teamDB.closeDB();
        randomUserDB.closeDB();
        if (event.getListTeams().getCollection().size() > 0) {
            callback.onSuccess(event);
        } else {
            callback.onError();
        }
    }

    @Override
    public boolean deleteEvent(long id) {
        if(this.eventDB.deleteEvent(id) > 0){
            return true;
        } else {
            return false;
        }
    }

    public void init() {
        this.eventDB = new EventDBImpl(TeamFactoApp.get());
        this.teamDB = new TeamDBImpl(TeamFactoApp.get());
        this.randomUserDB = new RandomUserDBImpl(TeamFactoApp.get());
        this.eventMapper = new EventMapper();
        this.teamMapper = new TeamMapper();
        this.randomUserMapper = new RandomUserMapper();

        Timber.tag(EventCacheImpl.class.getSimpleName());
    }
}

package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDBImpl extends DatabaseHelper implements EventDB {

  @Inject
  public EventDBImpl(Context context) {
    super(context);
  }

  @Override public long createEvent(Event event) {
    SQLiteDatabase db = this.getWritableDatabase();
    long lEvent = db.insert(DBConstants.TABLE_EVENT, null, bindEvent(event));
    return lEvent;
  }

  @Override public long createEventUser(long idEvent, long idUser) {
    SQLiteDatabase db = this.getWritableDatabase();
    long lEventUser =
        db.insert(DBConstants.TABLE_RANDOM_EVENT, null, bindEventUser(idEvent, idUser));
    return lEventUser;
  }

  @Override public long createEventTeam(long idEvent, long idTeam) {
    SQLiteDatabase db = this.getWritableDatabase();
    long lEventTeam = db.insert(DBConstants.TABLE_EVENT_TEAM, null, bindEventTeam(idEvent, idTeam));
    return lEventTeam;
  }

  @Override public Cursor getIdUserEvent(long idEvent) {
    SQLiteDatabase db = this.getReadableDatabase();
    String selectQuery =
        "SELECT id_user FROM " + DBConstants.TABLE_RANDOM_EVENT + " WHERE id_event = " + idEvent;
    Cursor cursorIdUsers = db.rawQuery(selectQuery, null);

    return cursorIdUsers;
  }

  @Override public Cursor getIdTeamsEvent(long idEvent) {
    SQLiteDatabase db = this.getReadableDatabase();
    String selectQuery =
        "SELECT id_team FROM " + DBConstants.TABLE_EVENT_TEAM + " WHERE id_event = " + idEvent;
    Cursor cursorIdTeams = db.rawQuery(selectQuery, null);

    return cursorIdTeams;
  }

  @Override public Cursor getEvent(long id) {
    String selectQuery =
        "SELECT  * FROM " + DBConstants.TABLE_EVENT + " WHERE " + DBConstants.KEY_ID + " = " + id;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorUsers = db.rawQuery(selectQuery, null);

    return cursorUsers;
  }

  @Override public Cursor getAllEvents() {
    String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_EVENT;

    return this.getReadableDatabase().rawQuery(selectQuery, null);
  }

  @Override public int deleteEvent(long id) {
    SQLiteDatabase db = this.getWritableDatabase();
    int numRows = db.delete(DBConstants.TABLE_EVENT, "id = " + String.valueOf(id), null);
    return numRows;
  }

  @Override public void closeDb() {
    this.close();
  }

  private ContentValues bindEvent(Event event) {

    ContentValues values = new ContentValues();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    values.put("type", event.getType());
    values.put("num_users", event.getNumUser());
    values.put("num_teams", event.getNumTeams());
    values.put("created_at", dateFormat.format(date));

    return values;
  }

  private ContentValues bindEventUser(long idEvent, long idUser) {
    ContentValues values = new ContentValues();
    values.put("id_user", idUser);
    values.put("id_event", idEvent);
    return values;
  }

  private ContentValues bindEventTeam(long idEvent, long idTeam) {
    ContentValues values = new ContentValues();
    values.put("id_event", idEvent);
    values.put("id_team", idTeam);
    return values;
  }
}

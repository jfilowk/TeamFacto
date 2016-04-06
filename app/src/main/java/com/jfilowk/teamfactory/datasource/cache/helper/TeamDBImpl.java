package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jfilowk.teamfactory.datasource.entities.Team;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

/**
 * Created by Javi on 30/09/14.
 */
public class TeamDBImpl extends DatabaseHelper implements TeamDB {

  @Inject
  public TeamDBImpl(Context context) {
    super(context);
  }

  @Override public long createTeam(Team team) {
    SQLiteDatabase db = this.getWritableDatabase();
    long lTeam = db.insert(DBConstants.TABLE_TEAM, null, bindTeam(team));
    return lTeam;
  }

  @Override public Cursor getTeams() {
    String selectQuery = "SELECT * FROM " + DBConstants.TABLE_TEAM;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorTeam = db.rawQuery(selectQuery, null);

    return cursorTeam;
  }

  @Override public Cursor getTeam(long idTeam) {
    String selectQuery =
        "SELECT * FROM " + DBConstants.TABLE_TEAM + " WHERE " + DBConstants.KEY_ID + " = " + idTeam;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorTeam = db.rawQuery(selectQuery, null);

    return cursorTeam;
  }

  private ContentValues bindTeam(Team team) {
    ContentValues values = new ContentValues();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    values.put("name", team.getName());
    values.put("created_at", dateFormat.format(date));

    return values;
  }

  @Override public void closeDB() {
    this.close();
  }
}

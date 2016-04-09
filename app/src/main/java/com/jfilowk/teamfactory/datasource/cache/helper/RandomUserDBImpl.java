package com.jfilowk.teamfactory.datasource.cache.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

/**
 * Created by Javi on 22/09/14.
 */
public class RandomUserDBImpl extends DatabaseHelper implements RandomUserDB {

  @Inject
  public RandomUserDBImpl(Context context) {
    super(context);
  }

  public long createRandomUser(RandomUser user, long teamId) {
    SQLiteDatabase db = this.getWritableDatabase();
    long random_id = db.insert(DBConstants.TABLE_USER, null, bindRandomUser(user, teamId));
    return random_id;
  }

  public Cursor getRandomUser(int id) {
    String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_USER + " WHERE id = " + id;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorUser = db.rawQuery(selectQuery, null);

    return cursorUser;
  }

  public Cursor getAllRandomUser() {
    String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_USER;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorUsers = db.rawQuery(selectQuery, null);

    return cursorUsers;
  }

  @Override public Cursor getUsersTeam(long teamId) {
    String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_USER + " WHERE team_id = " + teamId;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursorUsers = db.rawQuery(selectQuery, null);

    return cursorUsers;
  }

  private ContentValues bindRandomUser(RandomUser user, long teamId) {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    ContentValues values = new ContentValues();
    values.put("first_name", user.getFirstName());
    values.put("last_name", user.getLastName());
    values.put("gender", user.getGender());
    values.put("picture", user.getPicture());
    values.put("seed", user.getSeed());
    values.put("team_id", teamId);
    values.put("created_at", dateFormat.format(date));

    return values;
  }

  public void closeDB() {
    this.close();
  }
}

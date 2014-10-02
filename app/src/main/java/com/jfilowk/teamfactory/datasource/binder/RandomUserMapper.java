package com.jfilowk.teamfactory.datasource.binder;

import android.database.Cursor;

import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.terro.entities.Result;
import com.terro.entities.UserRandomResponse;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUserMapper {

    private static String KEY_ID = "id";
    private static String KEY_FIRST_NAME = "first_name";
    private static String KEY_LAST_NAME = "last_name";
    private static String KEY_PICTURE = "picture";
    private static String KEY_GENDER = "gender";
    private static String KEY_SEED = "seed";
    private static String KEY_CREATED = "created_at";

    public RandomUser transformResultToRandomUser(Result result) {

        RandomUser randomUser = new RandomUser();

        randomUser.setFirstName(WordUtils.capitalizeFully(result.getUser().getName().getFirst()));
        randomUser.setLastName(WordUtils.capitalizeFully(result.getUser().getName().getLast()));
        randomUser.setGender(result.getUser().getGender());
        randomUser.setPicture(result.getUser().getPicture());
        randomUser.setSeed(result.getSeed());

        return randomUser;
    }

    public RandomUser transformCursorToRandomUser(Cursor user) {
        RandomUser randomUser = new RandomUser();

        int id = user.getColumnIndex(KEY_ID);
        int first_name = user.getColumnIndex(KEY_FIRST_NAME);
        int last_name = user.getColumnIndex(KEY_LAST_NAME);
        int gender = user.getColumnIndex(KEY_GENDER);
        int picture = user.getColumnIndex(KEY_PICTURE);
        int seed = user.getColumnIndex(KEY_SEED);
        int created_at = user.getColumnIndex(KEY_CREATED);

        for (user.moveToFirst(); !user.isAfterLast(); user.moveToNext()) {

            randomUser.setId(user.getInt(id));
            randomUser.setFirstName(user.getString(first_name));
            randomUser.setLastName(user.getString(last_name));
            randomUser.setPicture(user.getString(picture));
            randomUser.setGender(user.getString(gender));
            randomUser.setSeed(user.getString(seed));
            randomUser.setCreated_at(user.getString(created_at));
        }

        return randomUser;
    }

    public RandomUserCollection transformResultToRandomUserCollection(UserRandomResponse response) {
        RandomUserCollection collection = new RandomUserCollection();

        for (Result result : response.getResults()) {
            RandomUser user = transformResultToRandomUser(result);
            collection.add(user);
        }

        return collection;
    }

    public RandomUserCollection transformCacheToRandomUserCollection(List<RandomUser> list) {
        RandomUserCollection collection = new RandomUserCollection();
        for (RandomUser user : list) {
            collection.add(user);
        }
        return collection;
    }

}

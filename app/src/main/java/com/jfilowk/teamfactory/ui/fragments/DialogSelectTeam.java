package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.ui.activity.GenerateTeam;
import com.jfilowk.teamfactory.ui.adapters.GridSelectionTeamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 21/09/14.
 */
public class DialogSelectTeam extends DialogFragment {

    @InjectView(R.id.gridSelectTeam)
    GridView gridSelectTeam;

    String[] nameType;

    private Activity mActivity;

    public DialogSelectTeam() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Choose teams:");
        View root = inflater.inflate(R.layout.layout_create_team_fragment, container);
        // contexto, vista
        ButterKnife.inject(this, root);
        createStringArrayTypes();
        GridSelectionTeamAdapter gridSelectionTeamAdapter = new GridSelectionTeamAdapter(mActivity, nameType);
        gridSelectTeam.setAdapter(gridSelectionTeamAdapter);
        gridSelectTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mActivity, "Has pulsado el tipo "+ nameType[position], Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mActivity, GenerateTeam.class);
                startActivity(i);
            }
        });
        return root;
    }

    public String getNameTypeString() {
        String json = null;
        try {

            InputStream is = mActivity.getAssets().open("gridTypes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void createStringArrayTypes (){
        String json = getNameTypeString();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray types = jsonObject.getJSONArray("types");
            nameType = new String[types.length()];
            for (int i = 0; i < types.length(); i++) {
                JSONObject object = types.getJSONObject(i);
                nameType[i] = object.getString("name");
            }
          System.out.println(Arrays.toString(nameType));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

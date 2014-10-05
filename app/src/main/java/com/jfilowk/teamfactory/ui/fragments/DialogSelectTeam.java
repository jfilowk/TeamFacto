package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jfilowk.teamfactory.R;

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


    @InjectView(R.id.rgTypeEvent)    RadioGroup rgTypeEvent;
    @InjectView(R.id.npUsers)
    NumberPicker npUsers;
    @InjectView(R.id.npTeams) NumberPicker npTeams;
    String[] nameType;

    private Activity mActivity;

    public static DialogSelectTeam newInstance (){
        DialogSelectTeam dialog = new DialogSelectTeam();
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return dialog;
    }

    public DialogSelectTeam() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // do something...
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View root = inflater.inflate(R.layout.dialog_create_event, null);
        ButterKnife.inject(this, root);

        rgTypeEvent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbSport:
                        Toast.makeText(mActivity.getApplicationContext(), "Sport", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbSchool:
                        Toast.makeText(mActivity.getApplicationContext(), "School", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbBusiness:
                        Toast.makeText(mActivity.getApplicationContext(), "Business", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        


        b.setView(root);
        return b.create();
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

    public void createStringArrayTypes() {
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

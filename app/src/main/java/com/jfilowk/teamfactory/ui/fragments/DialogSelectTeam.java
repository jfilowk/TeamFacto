package com.jfilowk.teamfactory.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.ui.activity.GenerateTeamActivity;

/**
 * Created by Javi on 21/09/14.
 */
public class DialogSelectTeam extends DialogFragment {

  private static final String KEY_OK_CAPS = "OK";
  private static final String KEY_CANCEL = "Cancel";
  private static final String KEY_EVENT = "event";

  @InjectView(R.id.rgTypeEvent) RadioGroup rgTypeEvent;
  @InjectView(R.id.npUsers) NumberPicker npUsers;
  @InjectView(R.id.npTeams) NumberPicker npTeams;
  RadioButton selected;

  private Activity mActivity;
  String typeEvent;
  int numTeams = 2;

  public DialogSelectTeam() {

  }

  public static DialogSelectTeam newInstance() {
    DialogSelectTeam dialog = new DialogSelectTeam();
    dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    return dialog;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    this.mActivity = activity;
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {

    LayoutInflater inflater = mActivity.getLayoutInflater();
    View root = inflater.inflate(R.layout.dialog_create_event, null);
    ButterKnife.inject(this, root);
    
    AlertDialog.Builder b = new AlertDialog.Builder(getActivity()).setPositiveButton(KEY_OK_CAPS,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            Event event = new Event();
            event.setType(selected.getText().toString());
            event.setNumUser(npUsers.getValue());
            event.setNumTeams(npTeams.getValue());
            Intent i = new Intent(mActivity.getApplicationContext(), GenerateTeamActivity.class);
            i.putExtra(KEY_EVENT, event);
            startActivity(i);
            getDialog().dismiss();
          }
        }).setNegativeButton(KEY_CANCEL, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        dialog.dismiss();
      }
    });
    selected = (RadioButton) rgTypeEvent.findViewById(R.id.rbSport);
    rgTypeEvent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
          case R.id.rbSport:
            selected = (RadioButton) rgTypeEvent.findViewById(R.id.rbSport);
            typeEvent = selected.getText().toString();
            break;
          case R.id.rbSchool:
            selected = (RadioButton) rgTypeEvent.findViewById(R.id.rbSchool);
            typeEvent = selected.getText().toString();

            break;
          case R.id.rbBusiness:
            selected = (RadioButton) rgTypeEvent.findViewById(R.id.rbBusiness);
            typeEvent = selected.getText().toString();
            break;
        }
      }
    });

    npTeams.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        numTeams = picker.getValue();
        npUsers.setMinValue(numTeams * 2);
      }
    });

    npUsers.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        picker.setMinValue(numTeams * 2);
      }
    });
    // TODO: 18/05/2016 never odd
    npUsers.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    npTeams.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    npUsers.setMaxValue(40);
    npUsers.setMinValue(4);
    npTeams.setMaxValue(5);
    npTeams.setMinValue(2);
    b.setView(root);
    return b.create();
  }
}

package com.jfilowk.teamfactory.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by Javi on 28/09/14.
 */
public class ListTeamsAdapter extends BaseAdapter {

    private static final int ITEM_VIEW_TYPE_TEAM = 0;
    private static final int ITEM_VIEW_TYPE_USER = 1;
    private static final int ITEM_VIEW_TYPE_COUNT = 2;

    private List<Object> teams;
    private Context context;
    private HashMap<String, String> colorsTeam;
    private int color = 1;
    private String[] colors = {"#FFFFFF" ,"#66E066", "#33B5E5", "#FFBB33", "#AA66CC", "#33B5E5"};

    public ListTeamsAdapter(Context context, List<Object> teams) {
        this.context = context;
        this.teams = teams;
        this.colorsTeam = new HashMap<String, String>();
    }

    @Override
    public int getItemViewType(int position) {

        if (teams.get(position) instanceof Team) {
            return ITEM_VIEW_TYPE_TEAM;

        } else {
            return ITEM_VIEW_TYPE_USER;

        }
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder itemHolder = null;
        SectionViewHolder sectionHolder = null;
        int type = getItemViewType(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (type) {
            case ITEM_VIEW_TYPE_TEAM:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.row_list_team_section, null);
                    sectionHolder = new SectionViewHolder(convertView);
                    convertView.setTag(sectionHolder);
                } else {
                    sectionHolder = (SectionViewHolder) convertView.getTag();
                }
                Team team = (Team) teams.get(position);
                if (colorsTeam.keySet().isEmpty()) {
                    this.colorsTeam.put(String.valueOf(team.getId()), colors[color]);
                    color++;
                } else {
                    if (!checkIdTeam(team)) {
                        this.colorsTeam.put(String.valueOf(team.getId()), colors[color]);
                        color++;
                    }
                }

                convertView.setBackgroundColor(Color.parseColor(colorsTeam.get(String.valueOf(team.getId()))));
                sectionHolder.teamName.setText(team.getName());
                break;
            case ITEM_VIEW_TYPE_USER:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.row_list_team_item, null);
                    itemHolder = new ItemViewHolder(convertView);
                    convertView.setTag(itemHolder);
                } else {
                    itemHolder = (ItemViewHolder) convertView.getTag();
                }
                RandomUser user = (RandomUser) teams.get(position);
                String colorString = "";
                if(user.getTeam_id() == 0){
                    colorString = "#FFFFFF";
                    itemHolder.firstNameUser.setTextColor(Color.BLACK);
                    itemHolder.lastNameUser.setTextColor(Color.BLACK);
                }else{
                   colorString = colorsTeam.get(String.valueOf(user.getTeam_id()));
                }
                convertView.setBackgroundColor(Color.parseColor(colorString));
                itemHolder.firstNameUser.setText(WordUtils.capitalizeFully(user.getFirstName()));
                itemHolder.lastNameUser.setText(WordUtils.capitalizeFully(user.getLastName()));
                Picasso.with(context).load(user.getPicture()).into(itemHolder.imageUser);
                break;
        }
        return convertView;
    }

    private boolean checkIdTeam(Team team) {
        int i = 0;
        for (String key : colorsTeam.keySet()) {
            if (String.valueOf(team.getId()).equals(key)) {
                i++;
            }
        }
        Timber.e("Valor de la I" + i);
        if (i >= 1) return true;
        else return false;

    }

    static class ItemViewHolder {

        @InjectView(R.id.textFirstNameUserItem)
        TextView firstNameUser;
        @InjectView(R.id.textLastNameUserItem)
        TextView lastNameUser;
        @InjectView(R.id.imageUserItem)
        ImageView imageUser;

        public ItemViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class SectionViewHolder {
        @InjectView(R.id.textTeamName)
        TextView teamName;

        SectionViewHolder(View view) {

            ButterKnife.inject(this, view);
        }

    }
}

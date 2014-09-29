package com.jfilowk.teamfactory.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jfilowk.teamfactory.R;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.Team;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 28/09/14.
 */
public class ListTeamsAdapter extends BaseAdapter {

    // View Type for TEAMS
    private static final int ITEM_VIEW_TYPE_TEAM = 0;
    // View Type for USER
    private static final int ITEM_VIEW_TYPE_USER = 1;
    // Types of Views that need to be handled
    // -- TEAMS and USERS rows --
    private static final int ITEM_VIEW_TYPE_COUNT = 2;

    private List<Object> teams;
    private Context context;

    public ListTeamsAdapter(Context context, List<Object> teams) {
        this.context = context;
        this.teams = teams;
    }

    @Override
    public int getItemViewType(int position) {
        if (this.teams.get(position).getClass().getSimpleName().equals("Team")) {
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
        System.out.println(this.teams.get(position).getClass().getSimpleName()+"tipo"+getItemViewType(position));
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
                itemHolder.firstNameUser.setText(WordUtils.capitalizeFully(user.getFirstName()));
                itemHolder.lastNameUser.setText(WordUtils.capitalizeFully(user.getLastName()));
                itemHolder.imageUser.setImageResource(android.R.drawable.ic_dialog_alert);
                break;
        }

        return convertView;
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
        @InjectView(R.id.textTeamName) TextView teamName;

        SectionViewHolder(View view) {

            ButterKnife.inject(this, view);
        }

    }
}

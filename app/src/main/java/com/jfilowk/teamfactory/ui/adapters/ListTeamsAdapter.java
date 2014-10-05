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

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 28/09/14.
 */
public class ListTeamsAdapter extends BaseAdapter {

    private static final int ITEM_VIEW_TYPE_TEAM = 0;
    private static final int ITEM_VIEW_TYPE_USER = 1;
    private static final int ITEM_VIEW_TYPE_COUNT = 2;

    private List<Object> teams;
    private Context context;

    public ListTeamsAdapter(Context context, List<Object> teams) {
        this.context = context;
        this.teams = teams;
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
                convertView.setBackgroundColor(Color.parseColor("#66E066"));
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
                convertView.setBackgroundColor(Color.parseColor("#FF4747"));
                itemHolder.firstNameUser.setText(WordUtils.capitalizeFully(user.getFirstName()));
                itemHolder.lastNameUser.setText(WordUtils.capitalizeFully(user.getLastName()));
                Picasso.with(context).load(user.getPicture()).into(itemHolder.imageUser);
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
        @InjectView(R.id.textTeamName)
        TextView teamName;

        SectionViewHolder(View view) {

            ButterKnife.inject(this, view);
        }

    }
}

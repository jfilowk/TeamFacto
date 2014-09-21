package com.jfilowk.teamfactory.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfilowk.teamfactory.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 21/09/14.
 */
public class GridSelectionTeamAdapter extends BaseAdapter {

    private Context context;
    private String[] teamName;

    public GridSelectionTeamAdapter(Context context, String[] teamName) {
        this.context = context;
        this.teamName = teamName;
    }

    @Override
    public int getCount() {
        return this.teamName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.grid_single_text, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.nameTeam.setText(teamName[position]);
        return null;
    }

    static class ViewHolder {
        @InjectView(R.id.grid_text)
        TextView nameTeam;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

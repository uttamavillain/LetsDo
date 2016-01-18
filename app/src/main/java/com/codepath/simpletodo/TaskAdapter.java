package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uttamavillain on 1/17/16.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_task, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        ImageView ivPriority = (ImageView) convertView.findViewById(R.id.ivPriority);
        // Populate the data into the template view using the data object
        tvName.setText(task.name);
        tvPriority.setText(task.priority);
        ivPriority.setImageResource(Utility.getArtResourceForPriority(task.priority));
        // Return the completed view to render on screen
        return convertView;
    }
}
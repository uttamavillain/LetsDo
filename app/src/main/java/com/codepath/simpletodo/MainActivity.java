package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private TaskAdapter itemsAdapter;
    public static final int EDIT_REQUEST = 1;
    public static final String TASK_ID = "task_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsAdapter = new TaskAdapter(this, new ArrayList<Task>());
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        readItems();
    }

    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        i.putExtra(TASK_ID, itemsAdapter.getItem(position).getId());
                        startActivityForResult(i, EDIT_REQUEST);
                    }
                }
        );
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Task task = itemsAdapter.getItem(position);
                        Task.delete(Task.class, task.getId());
                        for(int i=0; i<itemsAdapter.getCount(); i++) {
                            if(itemsAdapter.getItem(i).getId()==task.getId()) {
                                itemsAdapter.remove(task);
                                break;
                            }
                        }
                        return true;
                    }
                }
        );
    }

    public void onAddItem(View v) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        startActivityForResult(i, EDIT_REQUEST);
    }

    private void readItems() {
        itemsAdapter.clear();
        itemsAdapter.addAll(Task.getTasks());
    }

    private void writeItems(String taskName, String taskPriority) {
        Task task = new Task();
        task.name = taskName;
        task.priority = taskPriority;
        task.save();
        itemsAdapter.add(task);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if(requestCode == EDIT_REQUEST) {
                readItems();
            }
        }
    }
}

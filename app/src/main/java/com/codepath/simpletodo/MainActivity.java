package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private List<Task> items;
    private ArrayAdapter<Task> itemsAdapter;
    public static final int EDIT_REQUEST = 1;
    public static final String TASK_ID = "task_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        i.putExtra(TASK_ID, items.get(position).getId());
                        startActivityForResult(i, EDIT_REQUEST);
                    }
                }
        );
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Task task = items.get(position);
                        Task.delete(Task.class, task.getId());
                        for(int i=0; i<items.size(); i++) {
                            if(items.get(i).getId()==task.getId()) {
                                items.remove(i);
                                break;
                            }
                        }
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if(itemText.length()==0)
            return;
        etNewItem.setText("");
        writeItems(itemText);
        itemsAdapter.notifyDataSetChanged();
    }

    private void readItems() {
        items = Task.getTasks();
    }

    private void writeItems(String itemText) {
        Task task = new Task();
        task.name=itemText;
        items.add(task);
        task.save();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == EDIT_REQUEST) {
                //items.set(data.getIntExtra(ITEM_POS, 0), data.getStringExtra(TASK_NAME));
                long task_id = data.getLongExtra(TASK_ID,0);
                for(int i=0; i<items.size(); i++) {
                    if(items.get(i).getId()==task_id) {
                        items.set(i,Task.load(Task.class,task_id));
                        break;
                    }
                }
                itemsAdapter.notifyDataSetChanged();
            }
        }
    }
}

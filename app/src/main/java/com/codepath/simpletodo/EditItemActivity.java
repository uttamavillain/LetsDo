package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private EditText etTaskTitle;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etTaskTitle = (EditText) findViewById(R.id.etTaskTitle);
        final long task_id = getIntent().getLongExtra(MainActivity.TASK_ID,0);
        final Task task = Task.load(Task.class,task_id);
        etTaskTitle.setText(task.name);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newVal = etTaskTitle.getText().toString();
                task.name = newVal;
                task.save();
                Intent data = new Intent();
                data.putExtra(MainActivity.TASK_ID, task_id);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}

package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditItemActivity extends AppCompatActivity {
    private EditText etTaskTitle;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etTaskTitle = (EditText) findViewById(R.id.etTaskTitle);
        final long task_id = getIntent().getLongExtra(MainActivity.TASK_ID, -1);
        final Task task;
        if(task_id != -1) {
            task = Task.load(Task.class, task_id);
            etTaskTitle.setText(task.name);
            RadioButton rbpriority = (RadioButton) findViewById(Utility.getPriorityId(task.priority));
            rbpriority.setChecked(true);
        }
        else
            task = new Task();
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etTaskTitle.getText().toString();
                if(taskName.length()>0) {
                    task.name = etTaskTitle.getText().toString();
                    RadioGroup rgpriority = (RadioGroup) findViewById(R.id.rgprioirty);
                    RadioButton rbpriority = (RadioButton) findViewById(rgpriority.getCheckedRadioButtonId());
                    task.priority = Utility.getPriority(rbpriority.getText().toString());
                    task.save();
                    setResult(RESULT_OK);
                }
                else {
                    setResult(RESULT_CANCELED);
                }
                finish();
            }
        });
    }
}

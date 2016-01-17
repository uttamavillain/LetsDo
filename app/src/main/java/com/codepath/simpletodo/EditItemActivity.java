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
        etTaskTitle.setText(getIntent().getStringExtra(MainActivity.TASK_NAME));
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.ITEM_POS,getIntent().getIntExtra(MainActivity.ITEM_POS,0));
                data.putExtra(MainActivity.TASK_NAME, etTaskTitle.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}

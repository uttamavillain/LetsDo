package com.codepath.simpletodo;

import android.database.sqlite.SQLiteException;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uttamavillain on 1/17/16.
 */
@Table(name = "Tasks")
public class Task extends Model {
    // If name is omitted, then the field name is used.
    @Column(name = "Name")
    public String name;

    public Task() {
        super();
    }

    public Task(String name){
        super();
        this.name = name;
    }

    public static List<Task> getTasks() {
        List<Task> list;
        try {
            list = new Select()
                    .from(Task.class)
                    .execute();
        } catch(SQLiteException e) {
            list = new ArrayList<Task>();
        }
        return list;
    }

    @Override
    public String toString() {
        return name;
    }
}
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

    @Column(name = "priority")
    public String priority;

    public Task() {
        super();
    }

    public Task(String name, String priority){
        super();
        this.name = name;
        this.priority = priority;
    }

    public static ArrayList<Task> getTasks() {
        List<Task> list;
        try {
            list = new Select()
                    .from(Task.class)
                    .execute();
        } catch(SQLiteException e) {
            list = new ArrayList<Task>();
        }
        return new ArrayList<Task>(list);
    }

    @Override
    public String toString() {
        return name+" - "+priority;
    }
}
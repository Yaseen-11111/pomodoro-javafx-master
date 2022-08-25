package com.github.yaseen;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDo implements Serializable {
    private final ArrayList<Task> tasks;
    private ArrayList<Task> taskComp;

    public ToDo() {
        tasks = new ArrayList<>();
        taskComp = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTaskComp(Task task) {
        taskComp.add(task);
    }

    public void removeTaskComp(Task task) {
        taskComp.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public ArrayList<Task> getTasksComp() {
        return taskComp;
    }

}
package com.github.yaseen;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
    private String taskName;
    private String createdDate;
    private String dueDate;
    private String additionalInfo;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Date date = new Date();

    public Task (String taskName, String dueDate, String additionalInfo) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.additionalInfo = additionalInfo;
        createdDate = dateFormat.format(date);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}

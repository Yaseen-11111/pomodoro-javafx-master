package com.github.yaseen;

import java.io.IOException;


public class TitleController{
    public void btnClickPomodoroClock() throws IOException {
        App.setRoot("pomodoro");
    }

    public void btnClickTodo() throws IOException {
        App.setRoot("todo");
    }

    public void btnClickTimer() throws IOException {
        App.setRoot("timer");
    }

    public void btnClickStopwatch() throws IOException {
        App.setRoot("stopwatch");
    }
}

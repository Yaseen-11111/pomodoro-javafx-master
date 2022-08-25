package com.github.yaseen.model;

public interface CountDownObserver {
    void update(int seconds);
    void timeIsUp();
}

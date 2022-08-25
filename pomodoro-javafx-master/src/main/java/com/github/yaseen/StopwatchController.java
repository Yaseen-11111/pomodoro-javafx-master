package com.github.yaseen;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StopwatchController {
    Timeline timeline;
    LocalTime time = LocalTime.parse("00:00:00");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private Button toggleBtn;
    @FXML
    private Label timerLabel;
    @FXML
    private Button startTimerBtn;


    @FXML
    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void incrementTime() {
        time = time.plusSeconds(1);
        timerLabel.setText(time.format(dtf));
    }
    public void toggleBtnClicked() {
        if (timeline.getStatus().equals(Animation.Status.PAUSED)) {
            timeline.play();
            toggleBtn.setText("PAUSE");
        } else if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
            timeline.pause();
            toggleBtn.setText("RESUME");
        }
    }

    public void startTimer() {
        timeline.play();
        startTimerBtn.setDisable(true);
    }

    public void resetTimer() {
        if (startTimerBtn.isDisabled()) {
            timeline.stop();
            startTimerBtn.setDisable(false);
            time = LocalTime.parse("00:00:00");
            timerLabel.setText(time.format(dtf));
            toggleBtn.setText("PAUSE");
        }
    }

    public void homeBtnClicked() throws IOException {
        timeline.stop();
        App.setRoot("start");
    }
}

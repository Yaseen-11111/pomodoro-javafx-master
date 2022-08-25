package com.github.yaseen;

import com.github.yaseen.model.CountDown;
import com.github.yaseen.model.TimeMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.Objects;

public class TimerController {
    @FXML private VBox container;
    @FXML private Label clockLabel;
    @FXML private ProgressBar clockProgressBar;
    @FXML private Button toggleBtn;

    @FXML private Spinner<Integer> timerSpinner;


    private CountDown countdown;
    private TimerClock clock;

    @FXML
    public void initialize() {
        clock = new TimerClock(
                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
        countdown = new CountDown(TimeMode.POMODORO, clock);
        setSpinnerValues();
    }

    private void setSpinnerValues() {
        timerSpinner.getValueFactory()
                .setValue(TimeMode.POMODORO.getMinutes());
    }

    public void saveBtnClicked() throws IOException {
        TimeMode.POMODORO.setMinutes(
                timerSpinner.getValue());
        App.setRoot("timer");
    }

    public void toggleBtnClicked() {
        if (countdown.isRunning())
            stop();
        else
            activate();
    }

    private void stop() {
        countdown.stop();
        updateToggleBtn("RESUME");
    }

    private void updateToggleBtn(String text) {
        toggleBtn.setText(text);
    }

    private void activate() {
        if (countdown.isTimeUp())
            reset();
        start();
    }

    private void start() {
        countdown.start();
        updateToggleBtn("STOP");
    }

    private void reset() {
        removeTimeIsUpStyles();
        countdown.reset();
    }

    private void removeTimeIsUpStyles() {
        container.getStyleClass().remove("time-is-up-background");
        toggleBtn.getStyleClass().remove("time-is-up-color");
    }

    public void timeIsUp() {
        addTimeIsUpStyles();
        playSound();
        updateToggleBtn("RESET");
    }

    private void addTimeIsUpStyles() {
        container.getStyleClass().add("time-is-up-background");
        toggleBtn.getStyleClass().add("time-is-up-color");
    }

    private void playSound() {
        Media sound = new Media(Objects.requireNonNull(this.getClass().getResource("sound.wav")).toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.setVolume(Settings.volume);
        player.play();
    }

    public void homeBtnClicked() throws IOException {
        stop();
        App.setRoot("start");
    }
}

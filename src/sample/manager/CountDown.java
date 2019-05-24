package sample.manager;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDown {

    public static int secondsToWait = 10;

    public void startCountdown(Label label){

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            secondsToWait--;
            Platform.runLater(() -> label.setText(" Timer » " + secondsToWait));

            if (secondsToWait == 0) {
                exec.shutdown();
            }
        };

        label.setText(" Timer » " + secondsToWait);
        exec.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

    }

    public void resetTime(Button button){
        secondsToWait = 10;
        button.setText("Start");
    }


}

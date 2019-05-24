package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.data.ResourceManager;
import sample.data.SaveData;
import sample.manager.CountDown;
import sample.manager.ProgressManager;

public class Controller {

    @FXML
    public Button reset, load, cpsButton, restartButton;
    @FXML
    public Label recordText, text, timer;
    @FXML
    public TextArea info;
    @FXML
    public Separator progress1, progress2;
    @FXML
    public TextFlow textFlow;

    private int i, max;
    private boolean isClicked;

    public void click(ActionEvent actionEvent){

        CountDown countDown = new CountDown();

        if(!isClicked){

            isClicked = true;
            countDown.startCountdown(timer);

        } else {
            if (CountDown.secondsToWait != 0) {
                i++;
                text.setText(" CPS » " + i);
                cpsButton.setText("CLICCAAA !");
            } else {
                if (i > max) {
                    max = i;
                    recordText.setText(" Record » " + max);
                    cpsButton.setText("Ben fatto.");

                    ProgressManager progressManager = new ProgressManager();
                    progressManager.saveProgress(max);
                }
            }
        }
    }

    public void clickReset(ActionEvent actionEvent) {

        CountDown countDown = new CountDown();
        countDown.resetTime(cpsButton);

        i = 0;
        isClicked = false;
        timer.setText(" Timer » ");
        text.setText(" CPS »");
    }

    public void clickLoad(ActionEvent actionEvent) {
        try {
            SaveData data = (SaveData) ResourceManager.load("progress.save");

            if(data != null)
                recordText.setText( " Record » " + data.cps);

        } catch (Exception e) {

            System.out.println("Errore: " + e.getMessage());
        }


    }

    public void clickRestart(ActionEvent actionEvent) {
        SaveData saveData = new SaveData();
        try {
            ResourceManager.reset(saveData, "progress.save");
        } catch (Exception e) {
            System.out.println("Errore: problemi con il reset del file - " + e.getMessage());
        }
    }
}

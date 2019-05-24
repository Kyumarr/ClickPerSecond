package sample.manager;

import sample.data.ResourceManager;
import sample.data.SaveData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProgressManager {

    public void saveProgress(int max){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable runnable = () -> {
            SaveData saveData = new SaveData();
            saveData.cps = max;

            try {
                ResourceManager.save(saveData, "progress.save");
            } catch (Exception e) {
                System.out.println("Errore nel salvataggio: " + e.getMessage());
            }
        };

        executorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}

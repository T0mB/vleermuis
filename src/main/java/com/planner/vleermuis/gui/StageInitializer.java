package com.planner.vleermuis.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<PlannerApplication.StageReadyEvent> {

    @Override
    public void onApplicationEvent(PlannerApplication.StageReadyEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/userview.fxml"));
            Stage stage = event.getStage();
            stage.setTitle("VLEERMUIS");

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

package com.planner.vleermuis.gui;

import com.planner.vleermuis.PlannerApplication;
import com.planner.vleermuis.businesslogic.AgendaLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

@Component
public class StageInitializer implements ApplicationListener<PlannerApplication.StageReadyEvent> {

    @Autowired
    private ConfigurableApplicationContext springContext;

    @Autowired
    private AgendaLogic agendaLogic;

    @Override
    public void onApplicationEvent(PlannerApplication.StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/userview.fxml")));
            Parent root = fxmlLoader.load();
            Stage stage = event.getStage();
            stage.setTitle("VLEERMUIS");

            /* NOTE:
             *  Activities need to have an agenda, for now I only use one agenda for all activities,
             *  so I just create it here so activities can be added in the future I'd like to be able to
             *  have multiple agenda's and this making of the "main" agenda can be deleted/moved somewhere more logical
             * */
            if(agendaLogic.findAgendaById(1L).isEmpty()) {
                agendaLogic.createAgendaIfNotPresent(1L, "main", Collections.emptyList());
            }

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

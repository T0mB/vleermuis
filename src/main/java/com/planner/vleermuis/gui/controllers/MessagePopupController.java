package com.planner.vleermuis.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class MessagePopupController implements Initializable {

    @Autowired
    private ConfigurableApplicationContext springContext;

    @FXML
    Label labelSeverity;

    @FXML
    TextArea textAreaMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void createPopupMessage(String severity, String message){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/messagePopup.fxml")));
            fxmlLoader.getController();
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            labelSeverity.setText(severity);
            textAreaMessage.setText(message);
            stage.show();
        }
        catch (IOException e) {
            //TODO: better error handling
            e.printStackTrace();
        }
    }
}

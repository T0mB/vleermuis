package com.planner.vleermuis.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class AddActivityViewController implements Initializable {


    @FXML
    TextField activityName;

    @FXML
    TextField activityLocation;

    @FXML
    TextField activityDescription;

    @FXML
    TextField activityTime;

    @FXML
    public DatePicker activityDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void saveActivity(ActionEvent actionEvent) {

        LocalDate date = activityDate.getValue();

    }

    @FXML
    public void cancelActivity(ActionEvent actionEvent) {
    }
}

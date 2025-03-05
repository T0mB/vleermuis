package com.planner.vleermuis.gui.controllers;

import com.planner.vleermuis.businesslogic.ActivityLogic;
import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.util.PlannerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    DatePicker activityDate;

    @FXML
    Button activityAddButton;

    @FXML
    Label activityId;

    @Autowired
    ActivityLogic activityLogic;

    @Autowired
    public UserviewController userviewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void saveActivity(ActionEvent actionEvent) {

        String time = activityTime.getText();
        if(PlannerUtil.isValidTime(time)) {
            Activity activity = new Activity();
            activity.setName(activityName.getText());
            activity.setLocation(activityLocation.getText());
            activity.setDescription(activityDescription.getText());
            activity.setAtDate(LocalDateTime.of(activityDate.getValue(), LocalTime.parse(time)));
            if(activityId.getText() != null){
                activity.setId(Long.parseLong(activityId.getText()));
            }
            else {
                activity.setId(null);
            }
            activityLogic.createActivity(activity);

            userviewController.refreshActivitiesListView();
            Stage stage = (Stage) activityAddButton.getScene().getWindow();
            stage.close();

        }

    }
}

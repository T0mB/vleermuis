package com.planner.vleermuis.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class UserviewController implements Initializable {

    ZonedDateTime today;
    ZonedDateTime pickedDate;

    @FXML
    private ListView<String> calendarView;

    @FXML
    private Text monthText;

    @FXML
    private Text yearText;

    String[] test = {"xyz", "abc"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        today = ZonedDateTime.now();
        pickedDate = ZonedDateTime.now();

        calendarView.getItems().addAll(test);
        calendarView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                //TODO make listener :)
            }
        });

        monthText.setText(today.getMonth().name());
        yearText.setText(String.valueOf(today.getYear()));
    }


    @FXML
    void backOneMonth(ActionEvent event) {
        pickedDate = pickedDate.minusMonths(1);
         drawCalendarView();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        pickedDate = pickedDate.plusMonths(1);
        drawCalendarView();
    }

    private void drawCalendarView() {
        monthText.setText(pickedDate.getMonth().name());
        yearText.setText(String.valueOf(pickedDate.getYear()));
    }


}

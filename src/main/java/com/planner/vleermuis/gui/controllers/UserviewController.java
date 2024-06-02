package com.planner.vleermuis.gui.controllers;

import com.planner.vleermuis.businesslogic.SourceSiteLogic;
import com.planner.vleermuis.data.SourceSite;
import com.planner.vleermuis.gui.cells.SourceSiteListCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.ResourceBundle;


@Component
public class UserviewController implements Initializable {

    ZonedDateTime today;
    ZonedDateTime pickedDate;

    @Autowired
    private ConfigurableApplicationContext springContext;

    @Autowired
    private SourceSiteLogic sourceSiteLogic;

    @FXML
    private ListView<String> calendarView;

    @FXML
    private Text monthText;

    @FXML
    private Text yearText;

    @FXML
    private ListView<SourceSite> sourceSiteListView;

    String[] test = {"xyz", "abc"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        today = ZonedDateTime.now();
        pickedDate = ZonedDateTime.now();

        sourceSiteListView.setCellFactory(param -> new SourceSiteListCell());
        sourceSiteListView.getItems().addAll(sourceSiteLogic.getAllSites());

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

    @FXML
    void addLinkClicked(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/addLinkView.fxml")));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add link");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editLinkClicked(){
        //TODO
    }

    @FXML
    void removeLinkClicked(){
        //TODO
    }

    private void drawCalendarView() {
        monthText.setText(pickedDate.getMonth().name());
        yearText.setText(String.valueOf(pickedDate.getYear()));
    }



}

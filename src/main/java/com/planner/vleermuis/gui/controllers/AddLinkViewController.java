package com.planner.vleermuis.gui.controllers;

import com.planner.vleermuis.businesslogic.SourceSiteLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AddLinkViewController implements Initializable {

    @FXML
    TextField linkNameTextField;

    @FXML
    TextField linkToWebsiteTextField;

    @FXML
    Button linkConfirmButton;

    @Autowired
    public SourceSiteLogic sourceSiteLogic;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void addLinkConfirm(){
        String name = linkNameTextField.getText();
        String link = linkToWebsiteTextField.getText();
        sourceSiteLogic.createLink(name, link);
        //TODO: add refresh to the links list in main view
        Stage stage = (Stage) linkConfirmButton.getScene().getWindow();
        stage.close();
    }


}

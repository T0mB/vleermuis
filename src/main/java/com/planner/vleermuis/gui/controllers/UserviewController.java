package com.planner.vleermuis.gui.controllers;

import com.planner.vleermuis.businesslogic.ActivityLogic;
import com.planner.vleermuis.businesslogic.SourceSiteLogic;
import com.planner.vleermuis.common.Severity;
import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.data.SourceSite;
import com.planner.vleermuis.gui.cells.ActivityListCell;
import com.planner.vleermuis.gui.cells.SourceSiteListCell;
import com.planner.vleermuis.util.PlannerUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


@Component
public class UserviewController implements Initializable {

    ZonedDateTime today;
    ZonedDateTime pickedDate;
    WebEngine webEngine;

    @Autowired
    private ConfigurableApplicationContext springContext;

    @Autowired
    private SourceSiteLogic sourceSiteLogic;

    @Autowired
    private ActivityLogic activityLogic;

    @Autowired
    private MessagePopupController messagePopupController;

    @FXML
    private ListView<Activity> calendarView;

    @FXML
    private Text monthText;

    @FXML
    private Text yearText;

    @FXML
    private ListView<SourceSite> sourceSiteListView;

    @FXML
    private WebView webViewWidget;

    @FXML
    private TextField textFieldWebsite;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        today = ZonedDateTime.now();
        pickedDate = ZonedDateTime.now();

        sourceSiteListView.setCellFactory(param -> new SourceSiteListCell());
        sourceSiteListView.getItems().addAll(sourceSiteLogic.getAllSites());

        monthText.setText(today.getMonth().name());
        yearText.setText(String.valueOf(today.getYear()));
        List<SourceSite> sites = sourceSiteLogic.getAllSites();
        if(!sites.isEmpty()) {
            setUpWebView(sourceSiteLogic.getAllSites().getFirst());
        }
        else {
            webEngine = webViewWidget.getEngine();
        }

        calendarView.setCellFactory(param -> new ActivityListCell());
        calendarView.getItems().addAll(activityLogic.getAllActivitiesForMonthAndYear(Month.valueOf(monthText.getText()), Integer.valueOf(yearText.getText())));


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
            Scene scene = new Scene(root);
            Label idLabel = (Label) scene.lookup("#sourceSiteId");
            idLabel.setText(null);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            messagePopupController.createPopupMessage(Severity.ERROR.text, e.getMessage());
        }
    }

    @FXML
    void editLinkClicked(){
        SourceSite site = sourceSiteListView.getSelectionModel().getSelectedItem();
        if(site != null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(springContext::getBean);
                fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/addLinkView.fxml")));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Edit link");
                Scene scene = new Scene(root);
                TextField tfName = (TextField) scene.lookup("#linkNameTextField");
                tfName.setText(site.getName());
                TextField tfLink = (TextField) scene.lookup("#linkToWebsiteTextField");
                tfLink.setText(site.getLink());
                Label idLabel = (Label) scene.lookup("#sourceSiteId");
                idLabel.setText(String.valueOf(site.getId()));
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                messagePopupController.createPopupMessage(Severity.ERROR.text, e.getMessage());
            }
        }
        else{
            messagePopupController.createPopupMessage(Severity.INFO.text, "please select a site to load");
        }
    }

    @FXML
    void removeLinkClicked() {
        List<SourceSite> selectedLinks = sourceSiteListView.getSelectionModel().getSelectedItems();
        if(selectedLinks != null && !selectedLinks.isEmpty()){
            sourceSiteLogic.deleteLinks(selectedLinks);
            refreshSourceSiteListView();
        }
        else {
            messagePopupController.createPopupMessage(Severity.INFO.text, "please select at least one link");
        }
    }

    @FXML
    void loadLinkClicked(){
        SourceSite site = sourceSiteListView.getSelectionModel().getSelectedItem();
        if(site != null){
            webViewWidget.getEngine().load(site.getLink());
        }
        else {
            messagePopupController.createPopupMessage(Severity.INFO.text, "please select a site to load");
        }
    }

    @FXML
    void goToWebsite(){
        String url = PlannerUtil.sanitizeUrl(textFieldWebsite.getText());
        if(url != null){
            webViewWidget.getEngine().load(url);
        }
        else {
            messagePopupController.createPopupMessage(Severity.INFO.text, "please fill in a correct link");
        }

    }

    @FXML
    void addActivityClicked(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/addActivityView.fxml")));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add activity");
            Scene scene = new Scene(root);
            Label idLabel = (Label) scene.lookup("#activityId");
            idLabel.setText(null);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            messagePopupController.createPopupMessage(Severity.ERROR.text, e.getMessage());
        }
    }

    @FXML
    void editActivityClicked(ActionEvent event){
        Activity activity = calendarView.getSelectionModel().getSelectedItem();
        if(activity != null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(springContext::getBean);
                fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/addActivityView.fxml")));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Edit activity");
                Scene scene = new Scene(root);
                TextField tfName = (TextField) scene.lookup("#activityName");
                tfName.setText(activity.getName());

                TextField tfLocation = (TextField) scene.lookup("#activityLocation");
                tfLocation.setText(activity.getLocation());

                TextField tfDescription = (TextField) scene.lookup("#activityDescription");
                tfDescription.setText(activity.getDescription());

                TextField tfTime = (TextField) scene.lookup("#activityTime");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                tfTime.setText(activity.getAtDate().format(formatter));

                DatePicker dpDateAt = (DatePicker) scene.lookup("#activityDate");
                dpDateAt.setValue(activity.getAtDate().toLocalDate());

                Label idLabel = (Label) scene.lookup("#activityId");
                idLabel.setText(String.valueOf(activity.getId()));
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                messagePopupController.createPopupMessage(Severity.ERROR.text, e.getMessage());
            }
        }
        else{
            messagePopupController.createPopupMessage(Severity.INFO.text, "please select a site to load");
        }
    }

    @FXML
    void deleteActivityClicked(ActionEvent event){

        List<Activity> selectedActivities = calendarView.getSelectionModel().getSelectedItems();
        if(selectedActivities != null && !selectedActivities.isEmpty()){
            activityLogic.deleteActivities(selectedActivities);
            refreshActivitiesListView();
        }
        else {
            messagePopupController.createPopupMessage(Severity.INFO.text, "please select at least one activity");
        }

    }

    private void drawCalendarView() {
        monthText.setText(pickedDate.getMonth().name());
        yearText.setText(String.valueOf(pickedDate.getYear()));
        calendarView.getItems().clear();
        List<Activity> activityList = activityLogic.getAllActivitiesForMonthAndYear(Month.valueOf(monthText.getText()), Integer.valueOf(yearText.getText()));
        calendarView.getItems().addAll(activityList);
    }

    private void setUpWebView(SourceSite site){
        webEngine = webViewWidget.getEngine();
        if(site != null) {
            webEngine.load(site.getLink());
        }
    }

    protected void refreshSourceSiteListView(){
        sourceSiteListView.getItems().clear();
        sourceSiteListView.getItems().addAll(sourceSiteLogic.getAllSites());
    }

    protected void refreshActivitiesListView(){
        drawCalendarView();
    }




}

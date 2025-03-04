package com.planner.vleermuis.gui.cells;

import com.planner.vleermuis.data.Activity;
import com.planner.vleermuis.data.SourceSite;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

public class ActivityListCell extends ListCell<Activity> {

    private final Label name = new Label();
    private final Label location = new Label();
    private final Label description = new Label();
    private final Label date = new Label();
    private final VBox layout = new VBox(name, location, description, date);

    public ActivityListCell(){
        super();
        name.setStyle("-fx-font-size: 20px;");
    }

    @Override
    protected void updateItem(Activity item, boolean empty){
        super.updateItem(item, empty);
        setText(null);

        if (empty || item == null ) {
            name.setText(null);
            location.setText(null);
            description.setText(null);
            date.setText(null);
            setGraphic(null);
        } else if(StringUtils.isNotEmpty(item.getName()) &&
                StringUtils.isNotEmpty(item.getLocation()) &&
                StringUtils.isNotEmpty(item.getDescription()) &&
                item.getAtDate() != null){

            name.setText(item.getName());
            location.setText(item.getLocation());
            description.setText(item.getDescription());
            date.setText(item.getAtDate().toString());
            setGraphic(layout);
        }
    }

}

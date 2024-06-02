package com.planner.vleermuis.gui.cells;

import com.planner.vleermuis.data.SourceSite;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

public class SourceSiteListCell extends ListCell<SourceSite> {

    private final Label title = new Label();
    private final Label detail = new Label();
    private final VBox layout = new VBox(title, detail);

    public SourceSiteListCell(){
        super();
        title.setStyle("-fx-font-size: 20px;");
    }

    @Override
    protected void updateItem(SourceSite item, boolean empty){
        super.updateItem(item, empty);
        setText(null);

        if (empty || item == null ) {
            title.setText(null);
            detail.setText(null);
            setGraphic(null);
        } else if(StringUtils.isNotEmpty(item.getLink()) && StringUtils.isNotEmpty(item.getName())){

            title.setText(item.getName());
            detail.setText(item.getLink());
            setGraphic(layout);
        }
    }

}

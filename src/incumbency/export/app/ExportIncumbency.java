/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package incumbency.export.app;

import hrs.Page;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author youli
 */
public class ExportIncumbency extends Page{

    public ExportIncumbency(){
        super("export incumbency");
    }
    @Override
    public Node createView() {
        VBox root = new VBox();
        Label pojCaption = new Label("项目信息");
        pojCaption.getStyleClass().add("page-header");
        GridPane pojGrid = new GridPane();
        pojGrid.setHgap(4);
        pojGrid.setVgap(6);
        pojGrid.setPadding(new Insets(18,18,18,18));
        ObservableList<Node> content = pojGrid.getChildren();
        
        CheckBox countLabel = new CheckBox("计算标志");
        GridPane.setConstraints(countLabel, 0, 0);
        content.add(countLabel);
        countLabel.getStyleClass().add("page-subheader");
        
        CheckBox pojType = new CheckBox("项目类型");
        GridPane.setConstraints(pojType, 1, 0);
        content.add(pojType);
        pojType.getStyleClass().add("page-subheader");
        
        CheckBox pojName = new CheckBox("项目名称");
        GridPane.setConstraints(pojName, 2, 0);
        content.add(pojName);
        pojName.getStyleClass().add("page-subheader");
        
        root.getChildren().addAll(pojCaption,pojGrid,new Separator());
        
        return root;
    }

}

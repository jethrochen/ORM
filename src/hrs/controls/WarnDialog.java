/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package hrs.controls;

import hrs.HrsMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 *
 * @author youli
 */
public class WarnDialog extends VBox {
    protected Button okButton;
    private static String res = "";
    private final Window owner;
    private Node options;
    protected final Button cancelBtn;
    public WarnDialog(final Window owner, String dialogTitle,
            String explan, Node middlePane,final WarnDialogOkPressed okPressed){
        this.owner = owner;
        
        setId("WarnDialog");
        setSpacing(10);
        setMaxSize(230,USE_PREF_SIZE);
        setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent t){
                t.consume();
            }
        });
        Text explanation = new Text(explan);
        explanation.setWrappingWidth(400);
        
        BorderPane explPane = new BorderPane();
        VBox.setMargin(explPane, new Insets(5,5,5,5));
        explPane.setCenter(explanation);
        BorderPane.setMargin(explanation, new Insets(5,5,5,5));
        if(dialogTitle==null)
            dialogTitle = "WarnDialog";
        Label title = new Label(dialogTitle);
        title.setId("title");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        this.getChildren().add(title);
        
        options = middlePane;
        
        cancelBtn = new Button("取消");
        cancelBtn.setId("cancelButton");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent actionEvent){
                res = "no";
                HrsMain.getHrsMain().hideModalMessage();
            }
        });
        cancelBtn.setMinWidth(74);
        cancelBtn.setPrefWidth(74);
        HBox.setMargin(cancelBtn, new Insets(0,8,0,0));
        okButton = new Button("确认");
        okButton.setId("saveButton");
        okButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                res = "ok";
                okButton.setDisable(true);
                cancelBtn.setDisable(true);
                okPressed.okPressButtonPressed();
                //HrsMain.getHrsMain().hideModalMessage();
            }
        });
        okButton.setMinWidth(74);
        okButton.setPrefWidth(74);
        okButton.setDefaultButton(true);
        
        HBox bottomBar = new HBox(0);
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.getChildren().addAll(cancelBtn,okButton);
        VBox.setMargin(bottomBar, new Insets(20,5,5,5));
        
        getChildren().add(explPane);
        if(options != null)
            getChildren().add(options);
        getChildren().add(bottomBar);
    }
    public void setOptions(Node options){
        this.options = options;
        getChildren().add(1, options);
    }
    public static String getRes(){
        return res;
    }
}

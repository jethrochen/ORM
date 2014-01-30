/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.app;

import hrs.HrsMain;
import hrs.controls.WarnDialog;
import hrs.controls.WarnDialogOkPressed;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import person.controller.ImportFileController;

/**
 *
 * @author youli
 */
public class ImportFileChooser extends WarnDialog implements WarnDialogOkPressed{
    private String fileUrl = null;
    private final TextField textField;
    private final Button browseButton;
    int rowIndex = 0;private ProgressBar pgb = null;

    public ImportFileChooser(final Window owner, String dialogTitle, String explan, Node middlePane, WarnDialogOkPressed okPressed) {
        super(owner, dialogTitle, explan, middlePane, okPressed);
        GridPane midPane = new GridPane();
        midPane.setPadding(new Insets(8));
        midPane.setHgap(5.0F);
        midPane.setVgap(5.0F);
        
        Label parentDirLabel = new Label("本地XLS文件");
        parentDirLabel.setId("parent-dir-label");
        GridPane.setConstraints(parentDirLabel, 0, rowIndex);
        midPane.getChildren().add(parentDirLabel);
        
        rowIndex++;
        textField = new TextField();
        textField.setEditable(false);
        GridPane.setConstraints(textField, 0, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER,Priority.ALWAYS,Priority.NEVER);
        
        browseButton = new Button("浏览");
        browseButton.setId("browseButton");
        browseButton.setMinWidth(USE_PREF_SIZE);
        GridPane.setConstraints(browseButton, 1, rowIndex);
        browseButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent actionEvent){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Excel文件位置");
                FileChooser.ExtensionFilter filter
                        = new FileChooser.ExtensionFilter("EXCEL",
                                Arrays.asList("*.xls","*.xlsx"));
                fileChooser.getExtensionFilters().add(filter);
                File selectedFile = fileChooser.showOpenDialog(owner);
                if(selectedFile != null){
                    textField.setText(selectedFile.getAbsolutePath());
                    fileUrl = selectedFile.toURI().toString();
                    fileUrl = fileUrl.substring(0, fileUrl.lastIndexOf('/')+1);
                    
                }
            }
        });
        midPane.getChildren().addAll(textField,browseButton);
        if(pgb == null)
            pgb = new ProgressBar();
        else
            pgb.setProgress(0.0);
        pgb.setPrefWidth(400);
        pgb.setVisible(false);
        midPane.getChildren().add(pgb);
        setOptions(midPane);
        
        okButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                okPressButtonPressed();
            }
            
        });
    }
   
    @Override
    public void okPressButtonPressed() {
        browseButton.setDisable(true);
        final ImportFileController ifc = new ImportFileController(textField.getText());
        pgb.visibleProperty().bind(ifc.runningProperty());
        pgb.progressProperty().bind(ifc.progressProperty());
        Thread importThread = new Thread(ifc);
        importThread.start();
        ifc.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    if(ifc.get()){
                        //导入成功，退出
                    }else{
                       textField.setPromptText("导入错误！请确定导入文件格式正确");
                       browseButton.setDisable(false);
                       okButton.setDisable(false);
                       cancelBtn.setDisable(false);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImportFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(ImportFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
}

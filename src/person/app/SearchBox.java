
package person.app;

import hrs.HrsMain;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import person.entity.Person;
import person.service.PersonService;
import person.service.PersonServiceImpl;
import sun.reflect.Reflection;

/**
 * A mac style search box with drop down with results
 */
public class SearchBox extends Region {
    private ChoiceBox choiceBox;
    private TextField textBox;
    private Button clearButton;
    private PersonService personService;
    private ContextMenu contextMenu = new ContextMenu();
    private Popup extraInfoPopup = new Popup();
    private Label infoName;
    private Label infoDescription;
    private VBox infoBox;
    private Tooltip searchErrorTooltip = new Tooltip();
    private Timeline searchErrorTooltipHidder = null;
    private HBox pack;
    
    public SearchBox() {
        setId("SearchBox");
        setMinHeight(24);
        setPrefSize(150, 24);
        setMaxHeight(24);
        choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(PersonField.getFieldNames());
        choiceBox.getSelectionModel().selectFirst();
        
        textBox = new TextField();
        textBox.setPromptText("Search");
        clearButton = new Button();
        clearButton.setVisible(false);
        getChildren().addAll(textBox,choiceBox,clearButton);
        //pack = new HBox();
        //pack.getChildren().addAll(textBox,clearButton);
        //getChildren().addAll(pack, choiceBox);
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                textBox.setText("");
                textBox.requestFocus();
            }
        });
        textBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    ///System.out.println("SearchBox.handle DOWN");
                    contextMenu.setFocused(true);
                }
            }
        });
        personService = new PersonServiceImpl();
        textBox.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clearButton.setVisible(textBox.getText().length() != 0);
                if (textBox.getText().length() == 0) {
                    if (contextMenu != null) contextMenu.hide();
                    showError(null);
                } else {
                    boolean haveResults = false;
                    Map<String, List<Person>> results = new HashMap<String,List<Person>>();
                    
                        boolean isAny = choiceBox.getSelectionModel().getSelectedIndex()==0;
                        switch(choiceBox.getSelectionModel().getSelectedIndex()){
                            case 0:
                            case 1:
                                results.put(PersonField.ID.getFieldName(), personService.searchPerson("personId", textBox.getText()));
                                if(!isAny)break;
                            case 2:
                                results.put(PersonField.NAME.getFieldName(), personService.searchPerson("name", textBox.getText()));
                                if(!isAny)break;
                            case 3:
                                results.put(PersonField.BIRTHDAY.getFieldName(), personService.searchPerson("birthday", textBox.getText()));
                                if(!isAny)break;
                                case 4:
                                results.put(PersonField.DEPARTMENT.getFieldName(), personService.searchPerson("depart", textBox.getText()));
                                if(!isAny)break;
                                    case 5:
                                results.put(PersonField.JOBTITLE.getFieldName(), personService.searchPerson("jobTitle", textBox.getText()));
                                if(!isAny)break;
                                        case 6:
                                results.put(PersonField.EDUBACKGROUND.getFieldName(), personService.searchPerson("eduBackground", textBox.getText()));
                                if(!isAny)break;
                        }
                        
                    
                     // check if we have any results
                        for (List<Person> categoryResults: results.values()) {
                            if (categoryResults.size() > 0) {
                                haveResults = true;
                                break;
                            }
                        }
                    if (haveResults) {
                        showError(null);
                        populateMenu(results);
                        if (!contextMenu.isShowing()) contextMenu.show(SearchBox.this, Side.BOTTOM, 10,-5);
                    } else {
                        if (searchErrorTooltip.getText() == null) showError("No matches");
                        contextMenu.hide();
                    }
                    contextMenu.setFocused(true);
                }
            }
        });
        // create info popup
        infoBox = new VBox();
        infoBox.setId("search-info-box");
        infoBox.setFillWidth(true);
        infoBox.setMinWidth(USE_PREF_SIZE);
        infoBox.setPrefWidth(350);
        infoName = new Label();
        infoName.setId("search-info-name");
        infoName.setMinHeight(USE_PREF_SIZE);
        infoName.setPrefHeight(28);
        infoDescription = new Label();
        infoDescription.setId("search-info-description");
        infoDescription.setWrapText(true);
        infoDescription.setPrefWidth(infoBox.getPrefWidth()-24);
        infoBox.getChildren().addAll(infoName,infoDescription);
        extraInfoPopup.getContent().add(infoBox);
        // hide info popup when context menu is hidden
        contextMenu.setOnHidden(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent windowEvent) {
                extraInfoPopup.hide();
            }
        });
       // this.getStyleClass().add(this.getClass().getResource("SearchBox.css").toExternalForm());
    }
    
    private void showError(String message) {
        searchErrorTooltip.setText(message);
        if (searchErrorTooltipHidder != null) searchErrorTooltipHidder.stop();
        if (message != null) {
            Point2D toolTipPos = textBox.localToScene(0, textBox.getLayoutBounds().getHeight());
            double x = toolTipPos.getX() + textBox.getScene().getX() + textBox.getScene().getWindow().getX();
            double y = toolTipPos.getY() + textBox.getScene().getY() + textBox.getScene().getWindow().getY();
            searchErrorTooltip.show( textBox.getScene().getWindow(),x, y);
            searchErrorTooltipHidder = new Timeline();
            searchErrorTooltipHidder.getKeyFrames().add( 
                new KeyFrame(Duration.seconds(3), 
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            searchErrorTooltip.hide();
                            searchErrorTooltip.setText(null);
                        }
                    }
                )
            );
            searchErrorTooltipHidder.play();
        } else {
            searchErrorTooltip.hide();
        }
    }

    private void populateMenu(Map<String, List<Person>> results) {
        contextMenu.getItems().clear();
        for (Map.Entry<String, List<Person>> entry : results.entrySet()) {
            boolean first = true;
            for(Person result: entry.getValue()) {
                final Person sr = result;
                final HBox hBox = new HBox();
                hBox.setFillHeight(true);
                
                int fieldNameIndex = choiceBox.getSelectionModel().getSelectedIndex();
                if(fieldNameIndex == 0)
                    fieldNameIndex = 2;
                String fieldName = PersonField.getFieldName(fieldNameIndex);
                Method getMethod = null;
                try {
                    getMethod = result.getClass().getMethod("get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length()), null);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Label itemLabel = new Label(result.getName());
                Label itemLabel = null;
                try {
                    itemLabel = new Label(getMethod.invoke(sr, null).toString());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
                }
                itemLabel.getStyleClass().add("item-label");
                if (first) {
                    first = false;
                    Label groupLabel = new Label(entry.getKey().toString());
                    groupLabel.getStyleClass().add("group-label");
                    groupLabel.setAlignment(Pos.CENTER_RIGHT);
                    groupLabel.setMinWidth(USE_PREF_SIZE);
                    groupLabel.setPrefWidth(70);
                    hBox.getChildren().addAll(groupLabel,itemLabel);
                } else {
                    Region spacer = new Region();
                    spacer.setMinWidth(USE_PREF_SIZE);
                    spacer.setPrefWidth(70);
                    hBox.getChildren().addAll(spacer,itemLabel);
                }
                // create a special node for hiding/showing popup content
                final Region popRegion = new Region();
                popRegion.getStyleClass().add("search-menu-item-popup-region");
                popRegion.setPrefSize(10, 10);
                hBox.getChildren().add(popRegion);
                final String name = result.getName();
                final String shortDescription = result.toString();
                popRegion.opacityProperty().addListener(new ChangeListener<Number>() {
                    @Override public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        Platform.runLater( new Runnable() { // TODO runLater used here as a workaround for RT-14396
                            @Override public void run() {
                                if (popRegion.getOpacity() == 1) {
                                    infoName.setText(name);
                                    infoDescription.setText(shortDescription);
                                    Point2D hBoxPos = hBox.localToScene(0, 0);
                                    extraInfoPopup.show(getScene().getWindow(),
                                        hBoxPos.getX() + contextMenu.getScene().getX() + contextMenu.getX() - infoBox.getPrefWidth() - 10,
                                        hBoxPos.getY() + contextMenu.getScene().getY() + contextMenu.getY() - 27
                                    );
                                }
                            }
                        });
                    }
                });
                // create menu item
                CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
                menuItem.getStyleClass().add("search-menu-item");
                contextMenu.getItems().add(menuItem);
                // handle item selection
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        ///System.out.println("SearchBox.handle menuItem.setOnAction");
                        ((PersonListPage)HrsMain.getHrsMain().getCurrentPage()).setSelectedPerson(sr);
                    }
                });
            }
        }
    }

    @Override protected void layoutChildren() {
        choiceBox.resizeRelocate(0, 0, 80, getHeight());
        textBox.resizeRelocate(choiceBox.getLayoutX()+82,0,200,getHeight());
        clearButton.resizeRelocate(textBox.getWidth()+textBox.getLayoutX()-18,6,12,13);
        //choiceBox.resizeRelocate(clearButton.getLayoutX()-80, 0, 75, 24);
    }
}

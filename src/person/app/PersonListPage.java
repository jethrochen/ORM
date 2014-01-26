/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.app;

import hrs.Page;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.PaginationBuilder;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import person.controller.PersonInfoController;
import person.controller.PersonListController;
import person.entity.Person;

/**
 *
 * @author youli
 */
public class PersonListPage extends Page{
    private static Person selectedPerson;
    public static int PageId = 0;
    private PersonInfoPage personInfoPage;
    private Pagination pagination;
    public PersonListPage(){
        super("personlist");
    }
    
    static PersonListController service;
    public static void list(){
        service.restart();
    }
    public void setSelectedPerson(Person p){
        selectedPerson = p;
        ((PersonInfoController)personInfoPage.getController()).setPerson(selectedPerson);
    }
    @Override
    public Node createView() {
        controller = new PersonListController();
        service = (PersonListController) controller;
        Group root = new Group();
        root.maxHeight(Double.MAX_VALUE);
        root.maxWidth(Double.MAX_VALUE);
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(12));
        final TableView tableView = new TableView();
        Button refreshButton = new Button("刷新");
        refreshButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                service.restart();
            }
        });
        refreshButton.getStyleClass().add("combutton");
        
        Region veil = new Region();
        veil.setId("personlist-veil");
        ProgressIndicator pi = new ProgressIndicator();
        pi.setMaxSize(150,150);
        
        TableColumn idCol = new TableColumn();
        idCol.setText("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("personId"));
        TableColumn nameCol = new TableColumn();
        nameCol.setText("姓名");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn departCol = new TableColumn();
        departCol.setText("部门");
        departCol.setCellValueFactory(new PropertyValueFactory("depart"));
        TableColumn jobTitleCol = new TableColumn();
        jobTitleCol.setText("职称");
        jobTitleCol.setCellValueFactory(new PropertyValueFactory("jobTitle"));
        TableColumn eduBackgroundCol = new TableColumn();
        eduBackgroundCol.setText("学历");
        eduBackgroundCol.setCellValueFactory(new PropertyValueFactory("eduBackground"));
        TableColumn birthdayCol = new TableColumn();
        birthdayCol.setText("出生年月");
        birthdayCol.setCellValueFactory(new PropertyValueFactory("birthday"));
        tableView.getColumns().setAll(idCol,nameCol,departCol,jobTitleCol,eduBackgroundCol,birthdayCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        pi.progressProperty().bind(service.progressProperty());
        veil.visibleProperty().bind(service.runningProperty());
        pi.visibleProperty().bind(service.runningProperty());
        tableView.itemsProperty().bind(service.valueProperty());
        
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent t){
                setSelectedPerson((Person)tableView.getSelectionModel().getSelectedItem());
            }
        });
        
        pagination = PaginationBuilder.create().pageCount(service.getPageNum()).pageFactory(new Callback<Integer,Node>(){

            @Override
            public Node call(Integer pageIndex) {
                PageId = pageIndex;
                list();
                return tableView;
            }
            
        }).build();
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        vbox.getChildren().addAll(pagination, refreshButton);
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(vbox,veil,pi);
        HBox hbox = new HBox();
        hbox.getChildren().add(stack);
        personInfoPage = new PersonInfoPage();
        hbox.getChildren().add(personInfoPage.createView());
        root.getChildren().add(hbox);
        root.getStylesheets().add(PersonListPage.class.getResource("Personinfo.css").toExternalForm());
        service.setApp(this);
        ((PersonInfoController)personInfoPage.getController()).setPerson(selectedPerson);
        return root;
    }

}

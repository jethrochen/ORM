/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */
package org.contact.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.contact.entity.Contact;

/**
 *
 * @author youli
 */
public class ContactPane extends BorderPane {

    String bgcolor = "-fx-background-color: #f0f0f0";
    //  String style = "-fx-font-weight:normal; -fx-color: #f0f0f0; -fx-font-size:11; -fx-font-family: Verdana;";

    private TableView<Contact> table = new TableView<>();
    private String buttonCaption[] = {"Add New", "Update", "Delete", "|<",
        "<<", ">>", ">|"};
    private String label[] = {"Contact ID", "First Name", "Lat Name", "Email",
        "Phone"};
    private String fields[] = {"contactId", "firstName", "lastName", "email",
        "phone"};
    private Button button[] = new Button[7];
    private TextField textField[] = new TextField[5];

    private ContactController controller = new ContactController();
    private static int index;

    public ContactPane() {
        setTop(createButtonBox());
        setCenter(createForm());
        setBottom(table);
        setStyle(bgcolor);
        setPadding(new Insets(10, 10, 10, 10));
        populateForm(0);
        createTable();
        populateTable();
    }

    private Pane createForm() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(20);
        // grid.setStyle(style);
        grid.setVgap(2);
        for (int i = 0; i < label.length; i++) {
            grid.add(new Label(label[i] + " :"), 1, i);
            textField[i] = new TextField();
            grid.add(textField[i], 2, i);
        }
        textField[0].setEditable(false);
        textField[0].setTooltip(new Tooltip(
                "This field is automatically generated hence not editable"));
        return grid;
    }

    private Pane createButtonBox() {
        int width = 100;
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);

        for (int i = 0; i < buttonCaption.length; i++) {
            button[i] = new Button(buttonCaption[i]);
            // button[i].setStyle(style);
            button[i].setMinWidth(width);
            button[i].setOnAction(new ButtonHandler());
            box.getChildren().add(button[i]);
        }
        return box;
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource().equals(button[0])) {
                Contact c = new Contact(111, textField[1].getText(),
                        textField[2].getText(), textField[3].getText(),
                        textField[4].getText());
                controller.addContact(c);
            } else if (event.getSource().equals(button[1])) {
                Contact c = new Contact(
                        Integer.parseInt(textField[0].getText()),
                        textField[1].getText(), textField[2].getText(),
                        textField[3].getText(), textField[4].getText());
                controller.updateContact(c);
                System.out.println("update button clicked");
            } else if (event.getSource().equals(button[2])) {
                Contact c = (Contact) controller.getContactList().get(index);
                controller.removeContact(c.getContactId());
            } else if (event.getSource().equals(button[4])) {
                if (index > 0) {
                    index--;
                } else {
                    event.consume();
                }
            } else if (event.getSource().equals(button[3])) {
                index = 0;
            } else if (event.getSource().equals(button[5])) {
                if (index < controller.getContactList().size() - 1) {
                    index++;
                } else {
                    event.consume();
                }
            } else if (event.getSource().equals(button[6])) {
                index = controller.getContactList().size() - 1;
            }
            populateForm(index);
            populateTable();
        }

    }

    private void populateForm(int i) {
        if (controller.getContactList().isEmpty()) {
            return;
        }
        Contact c = (Contact) controller.getContactList().get(i);
        textField[0].setText(c.getContactId().toString());
        textField[1].setText(c.getFirstName());
        textField[2].setText(c.getLastName());
        textField[3].setText(c.getEmail());
        textField[4].setText(c.getPhone());
    }

    private void createTable() {
        TableColumn<Contact, Integer> contactIdCol = new TableColumn<Contact, Integer>("Contact ID");
        contactIdCol.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("contactId"));
        TableColumn<Contact, String> firstNameCol = new TableColumn<Contact, String>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        TableColumn<Contact, String> lastNameCol = new TableColumn<Contact, String>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        TableColumn<Contact, String> emailCol = new TableColumn<Contact, String>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));
        TableColumn<Contact, String> phoneCol = new TableColumn<Contact, String>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));
        table.getColumns().setAll(contactIdCol, firstNameCol, lastNameCol, emailCol, phoneCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void populateTable() {
        table.getItems().clear();
        //table.setStyle(style);     
        table.setItems(controller.getContactList());
    }

}

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package login.app;

import hrs.HrsMain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;

    //private HrsMain application;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
    }

    public void processLogin(ActionEvent event) {

            if (!HrsMain.hrsMain.userLogging(userId.getText(), password.getText())){
                errorMessage.setText("Unknown user " + userId.getText());
            }
    }
}

/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 */
package login.app;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import login.entity.User;
import login.security.Authenticator;
import org.contact.app.ContactPane;

/**
 * Main Application. This class handles navigation and user session.
 */
public class Main extends Application {

    private Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                @Override
                public void handle(WindowEvent e){
                    System.exit(0);
                    Platform.exit();
                }
            });
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }
        
    public boolean userLogging(String userId, String password){
        System.out.println("got user id " + userId + " password " + password);
        if (Authenticator.validate(userId, password)) {
            System.out.println("OK");
            loggedUser = User.of(userId);
            //gotoProfile();
            gotoContact();
            return true;
        } else {
            System.out.println("NOT OK");
            return false;
        }
    }
    
    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }
    
    private void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) replaceSceneContent("Profile.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        
        // Store the stage width and height in case the user has resized the window
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
        }
        
        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
        }
        
        Scene scene = new Scene(page);
        if (!Double.isNaN(stageWidth)) {
            page.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            page.setPrefHeight(stageHeight);
        }
        
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    private void gotoContact() {
        Pane contactPane = new ContactPane();
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
        }
        
        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
        }
        Scene scene = new Scene(contactPane);
        if (!Double.isNaN(stageWidth)) {
            contactPane.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            contactPane.setPrefHeight(stageHeight);
        }
        stage.setScene(scene);
        stage.sizeToScene();
    }
}

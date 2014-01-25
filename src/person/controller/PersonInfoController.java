/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import person.entity.Person;

/**
 *
 * @author youli
 */
public class PersonInfoController extends AnchorPane implements Initializable {
    @FXML
    TextField personId;
    
    @FXML
    TextField birthday;
    
    @FXML
    TextField department;
    
    @FXML
    TextField personName;
    
    @FXML
    TextField eduBackground;
    
    @FXML
    TextField jobTitle;
    
    @FXML 
    private Label success;
    
    @FXML 
    private Button update;
    
    @FXML
    private Hyperlink backlink;
    
    private static Person currentPerson;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void setPerson(Person p){
        if(p!=null)
            currentPerson = p;
        if(currentPerson!=null){
            personId.setText(currentPerson.getPersonId().toString());
            department.setText(currentPerson.getDepart().toString());
            birthday.setText(new SimpleDateFormat("yyyy-mm-dd").format(currentPerson.getBirthday()));
            eduBackground.setText(currentPerson.getEduBackground());
            jobTitle.setText(currentPerson.getJobTitle());
            personName.setText(currentPerson.getName());
        }

    }
    public void processUpdate(ActionEvent event){
        
    }
    public void backToPersonList(ActionEvent event){
        
    }
}

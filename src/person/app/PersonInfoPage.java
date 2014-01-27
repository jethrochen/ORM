/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.app;

import hrs.Page;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.contact.entity.Department;
import person.controller.PersonInfoController;
import person.entity.Person;
import person.service.PersonServiceImpl;

/**
 *
 * @author youli
 */
public class PersonInfoPage extends Page {

    public PersonInfoPage(){
        super("personinfopage");
    }
    
    @Override
    public Node createView() {
        try {
           // SearchBox searchBox = new SearchBox();
            Pane pane = this.getFXMLPane(this.getClass(), "PersonInfo.fxml");
           // VBox vbox = new VBox();
           // vbox.getChildren().addAll(searchBox,pane);
           // return vbox;
            return pane;
        } catch (IOException ex) {
            Logger.getLogger(PersonInfoPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

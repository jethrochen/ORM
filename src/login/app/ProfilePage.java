/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package login.app;

import hrs.HrsMain;
import hrs.Page;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author youli
 */
public class ProfilePage extends Page{
    public ProfilePage(){
        super("profilepage");
    }
    
    @Override
    public Node createView() {
        try {
            Pane pane = this.getFXMLPane(this.getClass(),"Profile.fxml");
            ((ProfileController)controller).setApp(HrsMain.hrsMain);
            return pane;
        } catch (IOException ex) {
            Logger.getLogger(ProfilePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package login.app;

import hrs.Page;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;

/**
 *
 * @author youli
 */
public class LoginPage extends Page {

    public LoginPage(){
        super("login");
    }
    @Override
    public Node createView() {
        try {
            return getFXMLPane(this.getClass(),"Login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

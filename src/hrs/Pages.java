/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package hrs;

import java.util.HashMap;
import java.util.Map;
import login.app.LoginPage;
import login.app.ProfilePage;

/**
 *
 * @author youli
 */
public class Pages {
    public static final String LOGIN = "LOGIN PAGE";
    public static final String USERPROFILE = "USER PROFILE";
    public static final String PERSON = "PERSON INFO";
    public static final String PROJECT = "PROJECT INFO";
    public static final Map<String,Page> pages;
    static{
        pages = new HashMap<String,Page>();
        pages.put(LOGIN, new LoginPage());
        pages.put(USERPROFILE,new ProfilePage());
    }
    public static Page getPage(String pageName){
        return pages.get(pageName);
    }
    ///public 
}

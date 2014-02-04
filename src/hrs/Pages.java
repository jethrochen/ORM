/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package hrs;

import incumbency.export.app.ExportIncumbency;
import java.util.HashMap;
import java.util.Map;
import login.app.LoginPage;
import login.app.ProfilePage;
import person.app.PersonInfoPage;
import person.app.PersonListPage;

/**
 * pages 维护所有的页面对象，所有的页面都需要在这里注册到pages里面
 * 
 * @author youli
 */
public class Pages {
    public static final String LOGIN = "LOGIN PAGE";
    public static final String USERPROFILE = "USER PROFILE";
    public static final String PERSONINFO = "PERSON INFO";
    public static final String PERSONLIST = "PERSON LIST";
    public static final String PROJECT = "PROJECT INFO";
    public static final String EXPORTINCUMBENCY = "EXPORT INCUMBENCY";
    public static final Map<String,Page> pages;
    static{
        pages = new HashMap<String,Page>();
        pages.put(LOGIN, new LoginPage());
        pages.put(USERPROFILE,new ProfilePage());
        pages.put(PERSONINFO, new PersonInfoPage());
        pages.put(PERSONLIST, new PersonListPage());
        pages.put(EXPORTINCUMBENCY, new ExportIncumbency());
    }
    public static Page getPage(String pageName){
        return pages.get(pageName);
    }
    ///public 
}

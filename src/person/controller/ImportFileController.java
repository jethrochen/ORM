/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.controller;

import hrs.HrsMain;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author youli
 */
public class ImportFileController extends Task<Boolean>{
    String fileUrl;
    public ImportFileController(String fileUrl){
        this.fileUrl = fileUrl;
    }

    @Override
    protected Boolean call() throws Exception {
        try{
           for(int i = 0; i < 500; i++){
                updateProgress(i,500);
                Thread.sleep(5);
            }
            HrsMain.getHrsMain().hideModalMessage();
            return true; 
        }catch(Exception e){
            return false;
        }
    }
}

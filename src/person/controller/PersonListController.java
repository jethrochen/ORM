/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import person.app.PersonListPage;
import person.entity.Person;
import person.service.PersonService;
import person.service.PersonServiceImpl;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class PersonListController extends Service<ObservableList<Person>> implements Initializable {

    private PersonService personService = new PersonServiceImpl();
    private PersonListPage page;
    private static int ONEPAGENUM = 1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(PersonListPage page){
        this.page = page;
        //this.start();
    }
    public int getPageNum(){
        int result = (int)Math.ceil((double)personService.getPersonNum()/ONEPAGENUM);
        if(result == 0)
            result = 1;
        return result;
    }
    @Override
    protected Task<ObservableList<Person>> createTask() {
        return new GetPersonListTask();
    }

    public class GetPersonListTask extends Task<ObservableList<Person>> {

        public GetPersonListTask() {
        }

        @Override
        protected ObservableList<Person> call() throws Exception {
            for (int i = 0; i < 500; i++) {
                updateProgress(i, 500);
                Thread.sleep(5);
            }
           // List<Person> gotPersons = (List<Person>)personService.getAllPerson();
            //获取第PageId页面的person数据
            List<Person> gotPersons = (List<Person>)personService.getPagedPerson(PersonListPage.PageId, ONEPAGENUM);
            ObservableList<Person> persons = FXCollections.observableArrayList(gotPersons);
            //将详细信息页面的数据设为当前页第一个person
            page.setSelectedPerson(persons.get(0));
            return persons;
        }
    }
    
}

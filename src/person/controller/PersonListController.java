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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            ObservableList<Person> persons;
            persons = FXCollections.observableArrayList((List<Person>)personService.getAllPerson());
            return persons;
        }
    }
    
}

/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.service;

import java.util.List;
import person.dao.PersonDAO;
import person.dao.PersonDAOImpl;
import person.entity.Person;

/**
 *
 * @author youli
 */
public class PersonServiceImpl implements PersonService{
    PersonDAO dao = new PersonDAOImpl();
    @Override
    public Person getPerson(int personid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPerson(Person person) {
        dao.addPerson(person);
    }

    @Override
    public void removePerson(int personid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPersonNum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPerson() {
        return dao.getAllPerson();
    }

    @Override
    public List<Person> getPagedPerson(int pageId, int onePageNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

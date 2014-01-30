/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.contact.entity.Department;
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
    public int addPerson(Person person) {
        dao.addPerson(person);
        return 0;
    }

    @Override
    public int removePerson(int personid) {
        dao.removePerson(personid);
        return 0;
    }

    @Override
    public int updatePerson(Person person) {
        try{
            dao.updatePerson(person); 
            return 0;
        }catch(Exception e){
            Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            return -1;
        }
    }

    @Override
    public int getPersonNum() {

        return 2;
    }

    @Override
    public List<Person> getAllPerson() {
        return dao.getAllPerson();
    }

    @Override
    public List<Person> getPagedPerson(int pageId, int onePageNum) {
        switch(pageId){
            case 0:
                return  Arrays.asList(new Person(201400,"尤立",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"),
                                    new Person(201401,"陈杰",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"));
            case 1:
                return  Arrays.asList(new Person(201402,"尤立",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"),
                                    new Person(201403,"陈杰",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"));
        }
        return null;
    }

    @Override
    public List<Person> searchPerson(String field, Object fieldValue) {
        switch(field){
            case "personId":
                return  Arrays.asList(new Person(201400,"尤立",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"),
                                    new Person(201401,"陈杰",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"));
            case "name":
                return  Arrays.asList(new Person(201402,"尤立",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"),
                                    new Person(201403,"陈杰",new Date(),new Department(1,"科研计划处"),"攻城狮","硕士"));
        }
        return Arrays.asList();
    }

    @Override
    public Department getDepartment(String depname) {
        return new  Department(1,"科研计划处");
    }

}

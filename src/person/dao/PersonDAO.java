/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.dao;

import java.util.List;
import person.entity.Person;

/**
 *
 * @author Administrator
 */
public interface PersonDAO {
    
    public List<Person> getAllPerson();
    public void addPerson(Person p);
}

/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package person.dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import person.entity.Person;

/**
 *
 * @author youli
 */
public class PersonDAOImpl implements PersonDAO {

    @Override
    public List<Person> getAllPerson() {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        List<Person> list = s.createQuery("from Person").list();
        s.getTransaction().commit();
        return list;
    }

    @Override
    public void addPerson(Person p) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.save(p);
        s.getTransaction().commit();
    }

}

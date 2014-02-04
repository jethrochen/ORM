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
        s.close();
        return list;
    }

    @Override
    public void addPerson(Person p) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.save(p);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public Person getPerson(int personid) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        Person per;// = (Person) s.byId(Person.class).load(personid);
        per = (Person) s.get(Person.class, personid);
        s.getTransaction().commit();
        s.close();
        return per;
    }

    @Override
    public void removePerson(int personid) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        Person per = (Person) s.load(Person.class, personid);
        s.delete(per);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void updatePerson(Person person) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.update(person);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public int getPersonNum() {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        int n = s.createQuery("from Person").getFetchSize();
        s.getTransaction().commit();
        s.close();
        return n;
    }

    @Override
    public List<Person> getPagedPerson(int pageId, int onePageNum) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        List<Person> res = s.createQuery("from Person").setFirstResult(pageId*onePageNum)
                .setMaxResults(onePageNum).list();
        s.getTransaction().commit();
        s.close();
        return res;
    }

}

/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.dao;

import java.util.ArrayList;
import java.util.List;
import org.contact.entity.Contact;
import hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author youli
 */
public class ContactDAOImpl implements ContactDAO{

    @Override
    public void addContact(Contact contact) {
      Session s = HibernateUtil.openSession();
      s.beginTransaction();
      s.save(contact);
      s.getTransaction().commit();
      s.close();
    }

    @Override
    public List<Contact> listContact() {
        List<Contact> list = new ArrayList<>();
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        list = s.createQuery("from Contact").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }

    @Override
    public void removeContact(Integer id) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        Contact c = (Contact)s.load(Contact.class, id);
        s.delete(c);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void updateContact(Contact contact) {
        Session s = HibernateUtil.openSession();
        s.beginTransaction();
        s.update(contact);
        s.getTransaction().commit();
        s.close();
    }

}

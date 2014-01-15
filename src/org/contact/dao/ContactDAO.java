/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.dao;

import java.util.List;
import org.contact.entity.Contact;

/**
 *
 * @author youli
 */
public interface ContactDAO {
    public void addContact(Contact contact);
    public List<Contact> listContact();
    public void removeContact(Integer id);
    public void updateContact(Contact contact);
}

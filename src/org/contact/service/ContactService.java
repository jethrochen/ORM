/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.service;

import java.util.List;
import org.contact.entity.Contact;

/**
 *
 * @author Administrator
 */
public interface ContactService {
    public void addContact(Contact contact);
    public List<Contact> listContact();
    public void removeContact(Integer id);
    public void updateContact(Contact contact);
}

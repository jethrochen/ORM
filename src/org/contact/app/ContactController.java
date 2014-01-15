/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package org.contact.app;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.contact.entity.Contact;
import org.contact.service.ContactService;
import org.contact.service.ContactServiceImpl;

/**
 *
 * @author youli
 */
public class ContactController {
    private ContactService contactService = new ContactServiceImpl();
    private ObservableList<Contact> contactList = FXCollections.observableArrayList();
    
    public void addContact(Contact contact){
        contactService.addContact(contact);
    }
    public ObservableList<Contact> getContactList(){
        if(!contactList.isEmpty())
            contactList.clear();
        contactList = FXCollections.observableArrayList((List<Contact>)contactService.listContact());
        return contactList;
    }
    public void removeContact(Integer id){
        contactService.removeContact(id);
    }
    public void updateContact(Contact contact){
        contactService.updateContact(contact);
    }
}

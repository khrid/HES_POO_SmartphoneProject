package contacts;

import apps.contacts.ContactsController;
import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;

import static org.junit.jupiter.api.Assertions.*;

class ContactsControllerTest {

    @Test
    void addXMLContact() throws TransformerException {
        ContactsController cc = new ContactsController();
        cc.GetContacts();
        int i = cc.GetContactsCount();
        cc.AddXMLContact("Test","","","","","");
        i++;
        cc.GetContacts();
        assert i==cc.GetContactsCount();
    }


}
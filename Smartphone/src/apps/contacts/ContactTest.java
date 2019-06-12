package apps.contacts;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

class ContactTest {
    private Element XMLContact;

    @Test
    void getNom() {
        Contact myContact = new Contact(XMLContact, "testContact","","","","","");
        assert "testContact".equals(myContact.getNom());
    }
}
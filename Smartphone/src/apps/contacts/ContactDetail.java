package apps.contacts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactDetail extends ContactBase {
    protected Contact contact;

    public ContactDetail(ContactsMain parent, ContactsController controller){
        super(parent,controller);

        buttonCancel.addActionListener(new BackContactsList());
    }

    public void SetContact(Contact contact){
        this.contact=contact;
        textNom.setText(contact.getNom());
        textPrenom.setText(contact.getPrenom());
        textFixe.setText(contact.getFixe());
        textMobile.setText(contact.getMobile());
    }

    // LISTENERS
    class BackContactsList implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            parent.ShowContactsMain();
        }
    }
}

package apps.contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactEdit extends ContactDetail {
    private JButton buttonSave;

    public ContactEdit(ContactsMain parent, ContactsController controller){
        super(parent,controller);

        pnlButtons.setLayout(new GridLayout(1,2));
        buttonSave=new JButton("Save");
        buttonSave.addActionListener(new SaveEditedContact());
        pnlButtons.add(buttonSave);
    }


    // LISTENERS
    class SaveEditedContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            contact.getXMLContact();
            parent.ShowContactsMain();
        }
    }
}

package apps.contacts;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactAdd extends ContactBase {

    private JButton buttonSave;

    public ContactAdd(ContactsMain parent, ContactsController controller){
        super(parent,controller);

        pnlButtons.setLayout(new GridLayout(1,2));
        buttonSave=new JButton("Save");
        buttonSave.addActionListener(new SaveNewContact());
        buttonCancel.addActionListener(new CancelNewContact());
        pnlButtons.add(buttonSave);
    }

    // LISTENERS
    class CancelNewContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            parent.ShowContactsMain();
        }
    }

    class SaveNewContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("New contact : " + textNom.getText() + " " + textPrenom.getText() + " " + textFixe.getText() + " " + textMobile.getText());
            try {
                controller.AddXMLContact(textNom.getText(),textPrenom.getText(),textFixe.getText(),textMobile.getText());
            } catch (TransformerException e) {
                e.printStackTrace();
            }
            parent.RefreshData();
            parent.ShowContactsMain();
        }
    }
}

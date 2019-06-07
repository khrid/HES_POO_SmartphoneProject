package apps.contacts;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class ContactAdd extends ContactBase {

    private JButton buttonSave;

    public ContactAdd(ContactsMain parent, ContactsController controller) throws ParseException {
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
                controller.AddXMLContact(textNom.getText(),textPrenom.getText(),textFixe.getText(),textMobile.getText(),textEmail.getText());
                parent.RefreshData();
                parent.ShowContactsMain();
                ResetFields();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
    }
}

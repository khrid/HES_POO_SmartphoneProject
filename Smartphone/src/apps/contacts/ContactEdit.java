package apps.contacts;

import javax.swing.*;
import javax.xml.transform.TransformerException;
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
            System.out.println("Edited contact : " + textNom.getText() + " " + textPrenom.getText() + " " + textFixe.getText() + " " + textMobile.getText());
            try {
                controller.EditXMLContact(contact, textNom.getText(),textPrenom.getText(),textFixe.getText(),textMobile.getText());
            } catch (TransformerException e) {
                e.printStackTrace();
            }
            parent.RefreshData();
            parent.ShowContactsMain();
        }
    }
}

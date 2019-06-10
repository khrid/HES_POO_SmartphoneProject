package apps.contacts;

import apps.gallery.GalleryItemPath;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class ContactEdit extends ContactDetail {
    private JButton buttonSave;
    private JComboBox<GalleryItemPath> cbPictures;

    @Override
    protected void InitializeFields(){
        // DO NOTHING
    }

    public ContactEdit(ContactsMain parent, ContactsController controller) throws ParseException {
        super(parent,controller);

        pnlButtons.setLayout(new GridLayout(1,2));
        buttonSave=new JButton("Save");
        buttonSave.addActionListener(new SaveEditedContact());
        //pnlButtons.add(buttonSave);

        // PLACER AUTRE PART

        cbPictures=new JComboBox<>();
        ArrayList<GalleryItemPath> myPictures=controller.GetAvailablePictures();
        for (int i=0;i<myPictures.size();i++){
            cbPictures.addItem(myPictures.get(i));
        }
        pnlButtons.add(cbPictures);
    }


    // LISTENERS
    class SaveEditedContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if(isEditValid()) {
                System.out.println("Edited contact : " + textNom.getText() + " " + textPrenom.getText() + " " + textFixe.getText() + " " + textMobile.getText());
                try {
                    controller.EditXMLContact(contact, textNom.getText(), textPrenom.getText(), textFixe.getText(), textMobile.getText(), textEmail.getText());
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                parent.RefreshData();
                parent.ShowContactsMain();
            } else {
                System.out.println("Information incorrect !");
                lblUserMessages.setText("Saisie non valide !");
            }
        }
    }
}

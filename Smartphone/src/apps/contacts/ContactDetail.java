package apps.contacts;

import apps.gallery.GalleryController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContactDetail extends ContactBase {
    protected Contact contact;

    protected void InitializeFields(){
        textNom.setEditable(false);
        textPrenom.setEditable(false);
        textFixe.setEditable(false);
        textMobile.setEditable(false);
        textEmail.setEditable(false);
        cbPictures.setEnabled(false);
    }

    public ContactDetail(ContactsMain parent, ContactsController controller) {
        super(parent,controller);
        buttonCancel.addActionListener(new BackContactsList());

        InitializeFields();
    }

    public void SetContact(Contact contact){
        this.contact=contact;
        textNom.setText(contact.getNom());
        textPrenom.setText(contact.getPrenom());
        textFixe.setText(contact.getFixe());
        textMobile.setText(contact.getMobile());
        textEmail.setText(contact.getEmail());
        cbPictures.setSelectedItem(contact.getPicture());
        SetPictureInContainer(contact.getPicture());
    }

    // LISTENERS
    class BackContactsList implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            parent.ShowContactsMain();
        }
    }
}

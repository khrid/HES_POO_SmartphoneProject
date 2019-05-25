package apps.contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactDetails extends JPanel {
    private ContactsMain parent;
    private ContactsController controller;
    private Contact contact;

    private JPanel pnlContactInformation;
    private JPanel pnlButtons;

    private JButton buttonSave;
    private JButton buttonCancel;

    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblFixe;
    private JLabel lblMobile;

    private JTextField textNom;
    private JTextField textPrenom;
    private JTextField textFixe;
    private JTextField textMobile;

    public ContactDetails(ContactsMain parent, ContactsController controller) {
        this.parent=parent;
        this.controller=controller;

        setLayout(new BorderLayout());

        pnlContactInformation=new JPanel(new GridLayout(4,2));

        lblNom=new JLabel("Nom");
        lblPrenom=new JLabel("Prénom");
        lblFixe=new JLabel("Fixe");
        lblMobile=new JLabel("Mobile");

        textNom=new JTextField();
        textPrenom=new JTextField();
        textFixe=new JTextField();
        textMobile=new JTextField();

        pnlContactInformation.add(lblNom);
        pnlContactInformation.add(textNom);
        pnlContactInformation.add(lblPrenom);
        pnlContactInformation.add(textPrenom);
        pnlContactInformation.add(lblFixe);
        pnlContactInformation.add(textFixe);
        pnlContactInformation.add(lblMobile);
        pnlContactInformation.add(textMobile);


        pnlButtons=new JPanel(new GridLayout(1,1));
        //buttonSave=new JButton("Save");
        //buttonSave.addActionListener(new SaveNewContact());
        buttonCancel=new JButton("Back");
        buttonCancel.addActionListener(new CancelNewContact());
        pnlButtons.add(buttonCancel);
        //pnlButtons.add(buttonSave);


        add(pnlContactInformation);
        add(pnlButtons,BorderLayout.SOUTH);
    }

    public void SetContact(Contact contact){
        this.contact=contact;
        textNom.setText(contact.getNom());
        textPrenom.setText(contact.getPrenom());
        textFixe.setText(contact.getFixe());
        textMobile.setText(contact.getMobile());
    }

    // LISTENERS
    class CancelNewContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            parent.ShowContactsMain();
        }
    }

}

package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactAdd extends JPanel {
    private ContactsMain parent;
    private ContactsController controller;

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

    public ContactAdd(ContactsMain parent, ContactsController controller) {
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


        pnlButtons=new JPanel(new GridLayout(1,2));
        buttonSave=new JButton("Save");
        buttonSave.addActionListener(new SaveNewContact());
        buttonCancel=new JButton("Back");
        buttonCancel.addActionListener(new CancelNewContact());
        pnlButtons.add(buttonCancel);
        pnlButtons.add(buttonSave);


        add(pnlContactInformation);
        add(pnlButtons,BorderLayout.SOUTH);
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

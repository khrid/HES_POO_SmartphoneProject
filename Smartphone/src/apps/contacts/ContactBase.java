package apps.contacts;


import javax.swing.*;

import java.awt.*;


public class ContactBase extends JPanel {
    private JPanel pnlContactInformation;

    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblFixe;
    private JLabel lblMobile;


    protected ContactsMain parent;
    protected ContactsController controller;
    protected JPanel pnlButtons;
    protected JButton buttonCancel;
    protected JTextField textNom;
    protected JTextField textPrenom;
    protected JTextField textFixe;
    protected JTextField textMobile;

    protected void ResetFields(){
        textNom.setText("");
        textPrenom.setText("");
        textFixe.setText("");
        textMobile.setText("");
    }

    public ContactBase(ContactsMain parent, ContactsController controller) {
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
        buttonCancel=new JButton("Back");
        pnlButtons.add(buttonCancel);

        add(pnlContactInformation);
        add(pnlButtons,BorderLayout.SOUTH);
    }

}

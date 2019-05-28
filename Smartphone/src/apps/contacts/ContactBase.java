package apps.contacts;


import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.text.ParseException;


public class ContactBase extends JPanel {
    private JPanel pnlContactInformation;

    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblFixe;
    private JLabel lblMobile;
    private JLabel lblEmail;

    private MaskFormatter mfFixe;
    private MaskFormatter mfMobile;


    protected ContactsMain parent;
    protected ContactsController controller;
    protected JPanel pnlBottom;
    protected JPanel pnlUserMessages;
    protected JPanel pnlButtons;
    protected JLabel lblUserMessages;
    protected JButton buttonCancel;
    protected JTextField textNom;
    protected JTextField textPrenom;
    protected JFormattedTextField textFixe;
    protected JFormattedTextField textMobile;
    protected JTextField textEmail;

    protected void ResetFields(){
        textNom.setText("");
        textPrenom.setText("");
        textFixe.setText("");
        textMobile.setText("");
        textEmail.setText("");
        lblUserMessages.setText("");
    }

    protected boolean isEditValid(){
        return (textFixe.isEditValid() && textMobile.isEditValid());
    }

    public ContactBase(ContactsMain parent, ContactsController controller) throws ParseException {
        this.parent=parent;
        this.controller=controller;

        setLayout(new BorderLayout());

        pnlContactInformation=new JPanel(new GridLayout(5,2));

        lblNom=new JLabel("Nom");
        lblPrenom=new JLabel("Prénom");
        lblFixe=new JLabel("Fixe");
        lblMobile=new JLabel("Mobile");
        lblEmail=new JLabel("Email");

        mfFixe = new MaskFormatter("### ### ## ##");
        mfFixe.setPlaceholderCharacter('_');
        mfMobile = new MaskFormatter("### ### ## ##");
        mfMobile.setPlaceholderCharacter('_');

        textNom=new JTextField();
        textPrenom=new JTextField();
        textFixe=new JFormattedTextField(mfFixe);
        textMobile=new JFormattedTextField(mfMobile);
        textEmail=new JTextField();

        pnlContactInformation.add(lblNom);
        pnlContactInformation.add(textNom);
        pnlContactInformation.add(lblPrenom);
        pnlContactInformation.add(textPrenom);
        pnlContactInformation.add(lblFixe);
        pnlContactInformation.add(textFixe);
        pnlContactInformation.add(lblMobile);
        pnlContactInformation.add(textMobile);
        pnlContactInformation.add(lblEmail);
        pnlContactInformation.add(textEmail);

        pnlBottom=new JPanel(new BorderLayout());
        lblUserMessages=new JLabel();
        pnlButtons=new JPanel(new GridLayout(1,1));
        buttonCancel=new JButton("Back");
        pnlButtons.add(buttonCancel);

        pnlBottom.add(lblUserMessages,BorderLayout.NORTH);
        pnlBottom.add(pnlButtons,BorderLayout.SOUTH);

        add(pnlContactInformation);
        add(pnlBottom,BorderLayout.SOUTH);
    }

}

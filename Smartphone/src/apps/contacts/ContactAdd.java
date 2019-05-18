package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactAdd extends JPanel {
    private ContactsMain parent;

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

    public ContactAdd(ContactsMain parent) {
        this.parent=parent;
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
        buttonSave=new JButton("Enregistrer");
        buttonCancel=new JButton("Annuler");
        buttonCancel.addActionListener(new Cancel());
        pnlButtons.add(buttonCancel);
        pnlButtons.add(buttonSave);


        add(pnlContactInformation);
        add(pnlButtons,BorderLayout.SOUTH);
    }

    // LISTENERS
    class Cancel implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            parent.ShowContactsMain();
        }
    }
}

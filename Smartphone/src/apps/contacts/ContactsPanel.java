package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContactsPanel extends AppPanel {
    private JList listContacts;
    private JScrollPane scrollPaneContacts;
    private JButton buttonOpenContact;
    private JButton buttonAddContact;
    private JButton buttonEditContact;
    private JButton buttonDeleteContact;
    private JPanel panelButtons;
    private String test[]= { "January", "February", "March",
            "April", "May", "June", "July", "August",
            "September", "October", "November", "December",
            "January", "February", "March",
            "April", "May", "June", "July", "August",
            "September", "October", "November", "December",
            "January", "February", "March",
            "April", "May", "June", "July", "August",
            "September", "October", "November", "December",
            "January", "February", "March",
            "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};
    public ContactsPanel(String appName) {
        super(appName);
        System.out.println("Creating ContactsPanel");
        setBackground(Color.yellow);

        removeAll();

        listContacts=new JList(test);
        listContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listContacts.setLayoutOrientation(JList.VERTICAL);
        scrollPaneContacts = new JScrollPane(listContacts);
        scrollPaneContacts.setBounds(0,0,100,100);
        listContacts.setFixedCellHeight(30);

        buttonOpenContact=new JButton("View");
        buttonAddContact=new JButton("New");
        buttonEditContact=new JButton("Edit");
        buttonDeleteContact=new JButton("Delete");

        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1,4));
        panelButtons.add(buttonOpenContact);
        panelButtons.add(buttonAddContact);
        panelButtons.add(buttonEditContact);
        panelButtons.add(buttonDeleteContact);

        setLayout(new BorderLayout());
        add(scrollPaneContacts);
        add(panelButtons,BorderLayout.SOUTH);

    }
}
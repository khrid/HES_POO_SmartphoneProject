package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsMain extends AppPanel {
    private ContactsController controller = new ContactsController();

    private CardLayout contactsCards;
    private JPanel pnlContactsMulti;

    private JPanel pnlMain;
    private ContactDetails pnlDetails;
    private ContactAdd pnlAdd;

    private JList listContacts;
    private JScrollPane scrollPaneContacts;
    private JButton buttonOpenContact;
    private JButton buttonAddContact;
    private JButton buttonEditContact;
    private JButton buttonDeleteContact;
    private JPanel panelButtons;

    public ContactsMain(String appName) {
        super(appName);
        setLayout(new BorderLayout());

        System.out.println("Creating ContactsMain");

        contactsCards = new CardLayout();
        pnlContactsMulti = new JPanel(contactsCards);

        listContacts=new JList();
        RefreshData();

        listContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listContacts.setLayoutOrientation(JList.VERTICAL);
        scrollPaneContacts = new JScrollPane(listContacts);
        scrollPaneContacts.setBounds(0,0,100,100);
        listContacts.setFixedCellHeight(30);

        buttonOpenContact=new JButton("View");
        buttonAddContact=new JButton("New");
        buttonEditContact=new JButton("Edit");
        buttonDeleteContact=new JButton("Delete");

        panelButtons = new JPanel(new GridLayout(1,4));
        panelButtons.add(buttonOpenContact);
        panelButtons.add(buttonAddContact);
        panelButtons.add(buttonEditContact);
        panelButtons.add(buttonDeleteContact);

        buttonOpenContact.addActionListener(new OpenContact());
        buttonAddContact.addActionListener(new AddContact());

        pnlMain = new JPanel(new BorderLayout());
        pnlMain.add(scrollPaneContacts);
        pnlMain.add(panelButtons,BorderLayout.SOUTH);

        pnlContactsMulti.add(pnlMain, "ContactsMain");
        pnlDetails = new ContactDetails(this,controller);
        pnlAdd = new ContactAdd(this,controller);
        pnlContactsMulti.add(pnlDetails,"ContactDetails");
        pnlContactsMulti.add(pnlAdd,"ContactAdd");

        add(pnlContactsMulti);
        contactsCards.show(pnlContactsMulti,"ContactsMain");
    }

    public void RefreshData(){
        listContacts.setModel(controller.GetContacts());
    }

    public void ShowContactsMain(){
        contactsCards.show(pnlContactsMulti,"ContactsMain");
    }


    // LISTENERS
    class OpenContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            pnlDetails.SetContact(controller.GetSelectedContact(listContacts.getSelectedIndex()));
            contactsCards.show(pnlContactsMulti,"ContactDetails");
        }
    }

    class AddContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            contactsCards.show(pnlContactsMulti,"ContactAdd");
        }
    }
}
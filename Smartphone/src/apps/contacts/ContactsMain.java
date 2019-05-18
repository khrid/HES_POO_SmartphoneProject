package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsMain extends AppPanel {
    private ContactsController myController = new ContactsController();

    private CardLayout cards;
    private JPanel pnlContactsMulti;
    //private static String activePanel = "ContactsMain";

    private JPanel pnlMain;
    private JPanel pnlDetail;

    private JList listContacts;
    private JScrollPane scrollPaneContacts;
    private JButton buttonOpenContact;
    private JButton buttonAddContact;
    private JButton buttonEditContact;
    private JButton buttonDeleteContact;
    private JPanel panelButtons;
    private String months[]= { "January", "February", "March",
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
    private DefaultListModel<String> aListContacts;

    private void RefreshData(DefaultListModel<String> aListContacts){
        listContacts.setModel(aListContacts);
    }

    public ContactsMain(String appName) {
        super(appName);

        System.out.println("Creating ContactsMain");

        listContacts=new JList(myController.GetXMLContacts());
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

        //pnlContactsMulti.setLayout(new BorderLayout());

        pnlMain = new JPanel(new BorderLayout());
        pnlMain.add(scrollPaneContacts);
        pnlMain.add(panelButtons,BorderLayout.SOUTH);

        cards = new CardLayout();
        pnlContactsMulti = new JPanel(cards);
        pnlContactsMulti.add(pnlMain, "ContactsMain");
        pnlDetail = new ContactAdd(this);
        pnlContactsMulti.add(pnlDetail,"ContactAdd");

        add(pnlContactsMulti,BorderLayout.CENTER);
        cards.show(pnlContactsMulti,"ContactsMain");
    }

    public void ShowContactsMain(){
        cards.show(pnlContactsMulti,"ContactsMain");
    }


    // LISTENERS
    class OpenContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            //cards.show(pnlContactsMulti,"ContactAdd");
        }
    }

    class AddContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            cards.show(pnlContactsMulti,"ContactAdd");
        }
    }
}
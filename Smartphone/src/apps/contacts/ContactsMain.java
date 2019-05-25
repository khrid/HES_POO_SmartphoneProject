package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsMain extends AppPanel {
    private ContactsController controller = new ContactsController();

    private CardLayout contactsCards;
    private JPanel pnlContactsMulti;

    private JPanel pnlMain;
    private ContactDetail pnlDetail;
    private ContactAdd pnlAdd;
    private ContactEdit pnlEdit;

    private JList listContacts;
    private JScrollPane scrollPaneContacts;
    private JButton buttonOpenContact;
    private JButton buttonAddContact;
    private JButton buttonEditContact;
    private JButton buttonDeleteContact;
    private JPanel panelButtons;

    private boolean IsAContactSelected(){
        return !(listContacts.getSelectedIndex()==-1);
    }

    private Contact GetSelectedContact(){
        return controller.GetContactAt(listContacts.getSelectedIndex());
    }

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
        listContacts.setSelectedIndex(0);

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
        buttonDeleteContact.addActionListener(new DeleteContact());
        buttonEditContact.addActionListener(new EditContact());

        pnlMain = new JPanel(new BorderLayout());
        pnlMain.add(scrollPaneContacts);
        pnlMain.add(panelButtons,BorderLayout.SOUTH);

        pnlContactsMulti.add(pnlMain, "ContactsMain");
        pnlDetail = new ContactDetail(this,controller);
        pnlAdd = new ContactAdd(this,controller);
        pnlEdit = new ContactEdit(this,controller);
        pnlContactsMulti.add(pnlDetail,"ContactDetails");
        pnlContactsMulti.add(pnlAdd,"ContactAdd");
        pnlContactsMulti.add(pnlEdit,"ContactEdit");

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
            pnlDetail.SetContact(GetSelectedContact());
            contactsCards.show(pnlContactsMulti,"ContactDetails");
        }
    }

    class DeleteContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (IsAContactSelected()){
                Contact contactToDelete = GetSelectedContact();
                System.out.println(contactToDelete.getNom() + " " + contactToDelete.getPrenom());

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this contact ?", "Contacts", dialogButton);
                if(dialogResult == 0) {
                    try {
                        controller.DeleteXMLContact(contactToDelete.getXMLContact());
                        RefreshData();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No Option");
                }
            }
            else {
                JOptionPane optionPane = new JOptionPane("No contact selected", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }
    }

    class AddContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            contactsCards.show(pnlContactsMulti,"ContactAdd");
        }
    }

    class EditContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            pnlEdit.SetContact(GetSelectedContact());
            contactsCards.show(pnlContactsMulti,"ContactEdit");
        }
    }
}
package apps.contacts;

import smartphone.AppPanel;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

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

    private Contact GetSelectedContact(){
        return controller.GetContactAt(listContacts.getSelectedIndex());
    }

    private void OpenSelectedContact(){
        if (!listContacts.isSelectionEmpty()) {
            pnlDetail.ResetFields();
            pnlDetail.SetContact(GetSelectedContact());
            contactsCards.show(pnlContactsMulti, "ContactDetails");
        }
    }

    public ContactsMain(String appName) throws ParseException {
        super(appName);
        setLayout(new BorderLayout());

        System.out.println("Creating ContactsMain");

        contactsCards = new CardLayout();
        pnlContactsMulti = new JPanel(contactsCards);

        listContacts=new JList();


        listContacts.addMouseListener(new ListDoubleClick());

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
        listContacts.setCellRenderer(new ContactListCellRenderer());
    }

    public void ShowContactsMain(){
        contactsCards.show(pnlContactsMulti,"ContactsMain");
    }


    // Drawing Table's Cells class
    class ContactListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Contact) {
                Contact contact = (Contact)value;
                setText(contact.getNom() + " " + contact.getPrenom());
                //setToolTipText(contact.getFixe());
            }
            return this;
        }
    }

    // LISTENERS
    class OpenContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            OpenSelectedContact();
        }
    }

    class ListDoubleClick extends MouseAdapter {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                OpenSelectedContact();
            }
        }
    }

    class DeleteContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (!listContacts.isSelectionEmpty()){
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
            pnlAdd.ResetFields();
            contactsCards.show(pnlContactsMulti,"ContactAdd");
        }
    }

    class EditContact implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (!listContacts.isSelectionEmpty()) {
                pnlEdit.ResetFields();
                pnlEdit.SetContact(GetSelectedContact());
                contactsCards.show(pnlContactsMulti, "ContactEdit");
            }
        }
    }
}
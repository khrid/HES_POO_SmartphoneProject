package apps.contacts;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class ContactBase extends JPanel {
    private JPanel pnlContactInformation;
    private GridBagConstraints c;

    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblFixe;
    private JLabel lblMobile;
    private JLabel lblEmail;

    protected ContactsMain parent;
    protected ContactsController controller;
    protected JPanel pnlBottom;
    protected JPanel pnlUserMessages;
    protected JPanel pnlButtons;
    protected JLabel lblUserMessages;
    protected JButton buttonCancel;
    protected JTextField textNom;
    protected JTextField textPrenom;

    protected JNumberTextField textFixe;
    protected JNumberTextField textMobile;

    protected JTextField textEmail;

    protected void ResetFields(){
        textNom.setText("");
        textPrenom.setText("");
        textFixe.setText("");
        textMobile.setText("");
        textEmail.setText("");
        lblUserMessages.setText("");
        ResetTextFieldsWarning();
    }

    protected void ResetTextFieldsWarning(){
        if(textNom.getBackground().equals(Color.RED))
            textNom.setBackground(Color.WHITE);

        if(textPrenom.getBackground().equals(Color.RED))
            textPrenom.setBackground(Color.WHITE);

        if(textFixe.getBackground().equals(Color.RED))
            textFixe.setBackground(Color.WHITE);

        if(textMobile.getBackground().equals(Color.RED))
            textMobile.setBackground(Color.WHITE);

        if(textEmail.getBackground().equals(Color.RED))
            textEmail.setBackground(Color.WHITE);

    }

    protected void setTextFieldWarning(JTextField myTextField){
        myTextField.setBackground(Color.RED);
    }

    protected boolean isEmailValid() {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-]+\\.)+[\\w]+[\\w]$";
        boolean isValid=(textEmail.getText().matches(regex) || textEmail.getText().equals(""));
        if (!isValid)
            setTextFieldWarning(textEmail);

        return isValid;
    }

    protected boolean isFirstameValid() {
        boolean isValid=!(textNom.getText().equals(""));
        if (!isValid)
            setTextFieldWarning(textNom);

        return isValid;
    }

    protected boolean isEditValid(){
        ResetTextFieldsWarning();
        return (isFirstameValid() && isEmailValid());
    }

    public ContactBase(ContactsMain parent, ContactsController controller) {
        this.parent=parent;
        this.controller=controller;

        setLayout(new BorderLayout());

        //pnlContactInformation=new JPanel(new GridLayout(5,2));

        pnlContactInformation=new JPanel(new GridBagLayout());
        c=new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridx=c.gridy=0;
        c.weightx=1.;
        c.fill=GridBagConstraints.HORIZONTAL;

        lblNom=new JLabel("Nom");
        lblPrenom=new JLabel("Prénom");
        lblFixe=new JLabel("Fixe");
        lblMobile=new JLabel("Mobile");
        lblEmail=new JLabel("Email");

        textNom=new JTextField();
        textPrenom=new JTextField();
        textFixe=new JNumberTextField();
        textMobile=new JNumberTextField();
        textEmail=new JTextField();

        pnlContactInformation.add(lblNom,c);
        c.gridx++;
        pnlContactInformation.add(textNom,c);
        c.gridx--;
        c.gridy++;
        pnlContactInformation.add(lblPrenom,c);
        c.gridx++;
        pnlContactInformation.add(textPrenom,c);
        c.gridx--;
        c.gridy++;
        pnlContactInformation.add(lblFixe,c);
        c.gridx++;
        pnlContactInformation.add(textFixe,c);
        c.gridx--;
        c.gridy++;
        pnlContactInformation.add(lblMobile,c);
        c.gridx++;
        pnlContactInformation.add(textMobile,c);
        c.gridx--;
        c.gridy++;
        pnlContactInformation.add(lblEmail,c);
        c.gridx++;
        pnlContactInformation.add(textEmail,c);

        pnlBottom=new JPanel(new BorderLayout());
        lblUserMessages=new JLabel();
        pnlButtons=new JPanel(new GridLayout(1,1));
        buttonCancel=new JButton("Back");
        pnlButtons.add(buttonCancel);

        pnlBottom.add(lblUserMessages,BorderLayout.NORTH);
        pnlBottom.add(pnlButtons,BorderLayout.SOUTH);

        add(pnlContactInformation);
        add(pnlBottom,BorderLayout.SOUTH);
        //repaint();
    }

}

class JNumberTextField extends JTextField {

    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (Character.isDigit(ev.getKeyChar()) || ev.getKeyChar()==' ' || ev.getKeyChar()=='\b') {
            super.processKeyEvent(ev);
        }
        ev.consume();
        return;
    }
}

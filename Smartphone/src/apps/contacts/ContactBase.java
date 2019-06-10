package apps.contacts;

import apps.gallery.GalleryController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ContactBase extends JPanel {
    private JPanel pnlContactInformation;
    private GridBagConstraints c;

    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblFixe;
    private JLabel lblMobile;
    private JLabel lblEmail;
    private JLabel lblPicture;

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
    protected JComboBox<String> cbPictures;
    protected JLabel lblPictureContainer;


    protected void ResetFields(){
        textNom.setText("");
        textPrenom.setText("");
        textFixe.setText("");
        textMobile.setText("");
        textEmail.setText("");
        FillPicturesCombo();
        cbPictures.setSelectedIndex(0);
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

    protected void FillPicturesCombo(){
        cbPictures.removeAllItems();
        cbPictures.addItem("");
        ArrayList<String> myPictures=controller.GetAvailablePictures();
        for (int i=0;i<myPictures.size();i++){
            cbPictures.addItem(myPictures.get(i));
        }
    }

    protected void SetPictureInContainer(String picture){
        lblUserMessages.setText("");

        if (picture!="" && picture!=null){
            BufferedImage img = null;
            File f = new File(GalleryController.GALLERY_LOCATION + picture);
            try {
                img = ImageIO.read(f);
                lblPictureContainer.setIcon(new ImageIcon(img.getScaledInstance(100,100, Image.SCALE_SMOOTH)));
                lblPictureContainer.setSize(new Dimension(100, 100));
            } catch (IOException e) {
                //e.printStackTrace();
                lblUserMessages.setText("Problème au chargement de l'image");
            }

        } else{
            lblPictureContainer.setIcon(null);

        }
        repaint();
    }

    public ContactBase(ContactsMain parent, ContactsController controller) {
        this.parent=parent;
        this.controller=controller;

        setLayout(new BorderLayout());

        pnlContactInformation=new JPanel(new GridBagLayout());
        c=new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridx=c.gridy=0;
        c.weightx=1.;
        c.fill=GridBagConstraints.HORIZONTAL;

        // Initialisation des composants graphiques
        lblNom=new JLabel("Nom");
        lblPrenom=new JLabel("Prénom");
        lblFixe=new JLabel("Fixe");
        lblMobile=new JLabel("Mobile");
        lblEmail=new JLabel("Email");
        lblPicture=new JLabel("Image");

        textNom=new JTextField();
        textPrenom=new JTextField();
        textFixe=new JNumberTextField();
        textMobile=new JNumberTextField();
        textEmail=new JTextField();
        cbPictures=new JComboBox<String>();

        cbPictures.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetPictureInContainer((String)cbPictures.getSelectedItem());
            }
        });

        lblPictureContainer=new JLabel();

        // Placement des composants sur la grille
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
        c.gridx--;
        c.gridy++;
        pnlContactInformation.add(lblPicture,c);
        c.gridx++;
        pnlContactInformation.add(cbPictures,c);
        c.gridx--;
        c.gridy++;
        pnlContactInformation.add(lblPictureContainer,c);

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

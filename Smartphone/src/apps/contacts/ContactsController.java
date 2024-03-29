package apps.contacts;

import apps.gallery.GalleryController;
import apps.gallery.GalleryItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import smartphone.Smartphone;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ContactsController {
    public static final String CONTACTS_LOCATION = Smartphone.ROOT_DIR+"contacts/";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String FIXE = "fixe";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";
    private static final String PICTURE = "picture";

    private String path;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element racine;
    private ArrayList<Contact> unsortedContactsList;
    private DefaultListModel<Contact> contactsList;

    private Element XMLNom;
    private Element XMLPrenom;
    private Element XMLFixe;
    private Element XMLMobile;
    private Element XMLEmail;
    private Element XMLPicture;


    private void InitializeXMLFile(){
        System.out.println("XML file initializing");
        path = CONTACTS_LOCATION+"contacts.xml";

        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();

            document = builder.parse(new File(path));

            racine = document.getDocumentElement();

        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        System.out.println("XML file initialized");
    }

    private void GetXMLContacts() {
        System.out.println("Contacts initializing");

        unsortedContactsList.clear();

        NodeList racineNoeuds = racine.getChildNodes();
        int nbRacineNoeuds = racineNoeuds.getLength();
        for (int i = 0; i<nbRacineNoeuds; i++) {
            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element XMLContact = (Element) racineNoeuds.item(i);

                XMLNom = (Element) XMLContact.getElementsByTagName(NOM).item(0);
                XMLPrenom = (Element) XMLContact.getElementsByTagName(PRENOM).item(0);
                XMLFixe = (Element) XMLContact.getElementsByTagName(FIXE).item(0);
                XMLMobile = (Element) XMLContact.getElementsByTagName(MOBILE).item(0);
                XMLEmail = (Element) XMLContact.getElementsByTagName(EMAIL).item(0);
                XMLPicture = (Element) XMLContact.getElementsByTagName(PICTURE).item(0);

                unsortedContactsList.add(new Contact(XMLContact,XMLNom.getTextContent(),XMLPrenom.getTextContent(),
                        XMLFixe.getTextContent(),XMLMobile.getTextContent(),XMLEmail.getTextContent(),XMLPicture.getTextContent()));
            }
        }
        System.out.println("Contacts initialized");
        System.out.println("Number of contacts : " + GetContactsCount());
    }

    private void WriteXMLFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
        System.out.println("File saved !");
    }



    public ContactsController() {
        contactsList = new DefaultListModel();
        unsortedContactsList = new ArrayList<>();
        InitializeXMLFile();
    }

    public void SortContacts(){
        Collections.sort(unsortedContactsList);
        contactsList.clear();
        for (int i=0; i<unsortedContactsList.size();i++){
            contactsList.addElement(unsortedContactsList.get(i));
        }
    }



    public ArrayList<String> GetAvailablePictures(){
        GalleryController gc = new GalleryController();

        ArrayList<GalleryItem> myItemsPath=gc.getItems();
        ArrayList<String> myStringPath=new ArrayList<>();

        for (int i=0;i<myItemsPath.size();i++){
            myStringPath.add(myItemsPath.get(i).getPath());
        }

        return myStringPath;
    }

    public DefaultListModel<Contact> GetContacts() {
        GetXMLContacts();
        SortContacts();
        return contactsList;
    }

    public int GetContactsCount(){
        return unsortedContactsList.size();
    }

    public Contact GetContactAt(int index){
        System.out.println("Getting contact at index " + index);
        return contactsList.getElementAt(index);
    }


    public void AddXMLContact(String nomValue, String prenomValue, String fixeValue, String mobileValue, String emailValue, String pictureValue) throws TransformerException {
        Element XMLContact = document.createElement("contact");
        racine.appendChild(XMLContact);

        XMLNom = document.createElement(NOM);
        XMLPrenom = document.createElement(PRENOM);
        XMLFixe = document.createElement(FIXE);
        XMLMobile = document.createElement(MOBILE);
        XMLEmail = document.createElement(EMAIL);
        XMLPicture = document.createElement(PICTURE);

        XMLContact.appendChild(XMLNom);
        XMLContact.appendChild(XMLPrenom);
        XMLContact.appendChild(XMLFixe);
        XMLContact.appendChild(XMLMobile);
        XMLContact.appendChild(XMLEmail);
        XMLContact.appendChild(XMLPicture);

        XMLNom.appendChild(document.createTextNode(nomValue));
        XMLPrenom.appendChild(document.createTextNode(prenomValue));
        XMLFixe.appendChild(document.createTextNode(fixeValue));
        XMLMobile.appendChild(document.createTextNode(mobileValue));
        XMLEmail.appendChild(document.createTextNode(emailValue));
        XMLPicture.appendChild(document.createTextNode(pictureValue));

        WriteXMLFile();
    }

    public void EditXMLContact(Contact contact, String nomValue, String prenomValue, String fixeValue, String mobileValue, String emailValue, String pictureValue) throws TransformerException {
        Element XMLContact = contact.getXMLContact();

        Element XMLNom = (Element) XMLContact.getElementsByTagName(NOM).item(0);
        Element XMLPrenom = (Element) XMLContact.getElementsByTagName(PRENOM).item(0);
        Element XMLFixe = (Element) XMLContact.getElementsByTagName(FIXE).item(0);
        Element XMLMobile = (Element) XMLContact.getElementsByTagName(MOBILE).item(0);
        Element XMLEmail = (Element) XMLContact.getElementsByTagName(EMAIL).item(0);
        Element XMLPicture = (Element) XMLContact.getElementsByTagName(PICTURE).item(0);

        XMLNom.setTextContent(nomValue);
        XMLPrenom.setTextContent(prenomValue);
        XMLFixe.setTextContent(fixeValue);
        XMLMobile.setTextContent(mobileValue);
        XMLEmail.setTextContent(emailValue);
        XMLPicture.setTextContent(pictureValue);

        WriteXMLFile();
    }

    public void DeleteXMLContact(Element contactToDelete) throws TransformerException {
        contactToDelete.getParentNode().removeChild(contactToDelete);
        WriteXMLFile();
    }
}
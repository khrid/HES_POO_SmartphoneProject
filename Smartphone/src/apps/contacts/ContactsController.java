package apps.contacts;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ContactsController {
    private static final String ID = "id";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String FIXE = "fixe";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";

    private String path;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element racine;
    private DefaultListModel<Contact> contactsList;

    private void InitializeXMLFile(){
        System.out.println("XML file initializing");

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            path = URLDecoder.decode(classLoader.getResource("apps/contacts/contacts.xml").getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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

        contactsList.clear();

        NodeList racineNoeuds = racine.getChildNodes();
        int nbRacineNoeuds = racineNoeuds.getLength();
        for (int i = 0; i<nbRacineNoeuds; i++) {
            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element personne = (Element) racineNoeuds.item(i);


                Element nom = (Element) personne.getElementsByTagName(NOM).item(0);
                Element prenom = (Element) personne.getElementsByTagName(PRENOM).item(0);
                Element fixe = (Element) personne.getElementsByTagName(FIXE).item(0);
                Element mobile = (Element) personne.getElementsByTagName(MOBILE).item(0);
                Element email = (Element) personne.getElementsByTagName(EMAIL).item(0);

                contactsList.addElement(new Contact(Integer.parseInt(personne.getAttribute(ID)),nom.getTextContent(),prenom.getTextContent(),
                        fixe.getTextContent(),mobile.getTextContent(),email.getTextContent()));
            }
        }
        System.out.println("Contacts initialized");
        System.out.println("Number of contacts : " + contactsList.size());
    }

    private void WriteXMLFile() throws TransformerException {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);
        System.out.println("File saved !");
    }



    public ContactsController() {
        contactsList = new DefaultListModel();
        InitializeXMLFile();
    }

    public DefaultListModel<Contact> GetContacts() {
        GetXMLContacts();

        return contactsList;
    }

    public Contact GetSelectedContact(int index){
        return contactsList.getElementAt(index);
    }



    public void AddXMLContact(String nomValue, String prenomValue, String fixeValue, String mobileValue) throws TransformerException {
        Element personne = document.createElement("contact");
        racine.appendChild(personne);

        Element nom = document.createElement(NOM);
        Element prenom = document.createElement(PRENOM);
        Element fixe = document.createElement(FIXE);
        Element mobile = document.createElement(MOBILE);
        Element email = document.createElement(EMAIL);

        personne.appendChild(nom);
        personne.appendChild(prenom);
        personne.appendChild(fixe);
        personne.appendChild(mobile);
        personne.appendChild(email);

        personne.setAttribute("id", "999");

        nom.appendChild(document.createTextNode(nomValue));
        prenom.appendChild(document.createTextNode(prenomValue));
        fixe.appendChild(document.createTextNode(fixeValue));
        mobile.appendChild(document.createTextNode(mobileValue));
        email.appendChild(document.createTextNode(""));

        WriteXMLFile();
    }
}
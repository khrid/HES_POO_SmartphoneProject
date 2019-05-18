package apps.contacts;

import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ContactsController {
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String FIXE = "fixe";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element racine;

    private void InitializeXMLFile(String path){
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
    }

    public ContactsController(){
        InitializeXMLFile("C:/tmp/contacts.xml");
    }

    public DefaultListModel<String> GetXMLContacts() {

        DefaultListModel<String> aListContacts=new DefaultListModel();


        NodeList racineNoeuds = racine.getChildNodes();
        int nbRacineNoeuds = racineNoeuds.getLength();
        for (int i = 0; i<nbRacineNoeuds; i++) {
            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element personne = (Element) racineNoeuds.item(i);

                //Affichage d'une personne
                //System.out.println("\n*************PERSONNE************");

                Element nom = (Element) personne.getElementsByTagName(NOM).item(0);
                Element prenom = (Element) personne.getElementsByTagName(PRENOM).item(0);
                Element fixe = (Element) personne.getElementsByTagName(FIXE).item(0);
                Element mobile = (Element) personne.getElementsByTagName(MOBILE).item(0);
                Element email = (Element) personne.getElementsByTagName(EMAIL).item(0);

                //Affichage du nom et du prénom
                    /*
                    System.out.println("nom : " + nom.getTextContent());
                    System.out.println("prénom : " + prenom.getTextContent());
                    System.out.println("fixe : " + fixe.getTextContent());
                    System.out.println("mobile : " + mobile.getTextContent());
                    System.out.println("email : " + email.getTextContent());
                     */

                aListContacts.addElement(nom.getTextContent() + " " + prenom.getTextContent());
            }
        }

        return aListContacts;
    }
}
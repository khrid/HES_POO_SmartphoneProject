package apps.contacts;

import org.w3c.dom.Element;

public class Contact {
    private Element XMLContact;
    private int id;
    private String prenom;
    private String nom;
    private String fixe;
    private String mobile;
    private String email;

    public Contact(Element XMLContact, int id, String nom, String prenom, String fixe, String mobile, String email) {
        this.XMLContact=XMLContact;
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.fixe=fixe;
        this.mobile=mobile;
        this.email=email;
    }

    public Element getXMLContact() {
        return XMLContact;
    }

    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getFixe() {
        return fixe;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

}

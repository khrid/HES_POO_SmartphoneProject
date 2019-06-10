package apps.contacts;

import org.w3c.dom.Element;

public class Contact implements Comparable<Contact>{
    private Element XMLContact;
    private String prenom;
    private String nom;
    private String fixe;
    private String mobile;
    private String email;
    private String picture;

    public Contact(Element XMLContact, String nom, String prenom, String fixe, String mobile, String email, String picture) {
        this.XMLContact=XMLContact;
        this.nom=nom;
        this.prenom=prenom;
        this.fixe=fixe;
        this.mobile=mobile;
        this.email=email;
        this.picture=picture;
    }

    public Element getXMLContact() {
        return XMLContact;
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

    public String getPicture() {
        return picture;
    }

    @Override
    public int compareTo(Contact o) {
        return this.nom.compareTo(o.nom);
    }
}

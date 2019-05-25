package apps.contacts;

public class Contact {
    private int id;
    private String prenom;
    private String nom;
    private String fixe;
    private String mobile;
    private String email;

    public Contact(int id, String prenom, String nom, String fixe, String mobile, String email) {
        this.id=id;
        this.prenom=prenom;
        this.nom=nom;
        this.fixe=fixe;
        this.mobile=mobile;
        this.email=email;
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

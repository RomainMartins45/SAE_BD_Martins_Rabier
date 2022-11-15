public class Client{
    private int id;
    private String nom;
    private String prenom;
    private float poids;
    private boolean cotisation;

    public Client(int id, String nom, String prenom, float poids, boolean cotisation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poids = poids;
        this.cotisation = cotisation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public boolean isCotisation() {
        return cotisation;
    }

    public void setCotisation(boolean cotisation) {
        this.cotisation = cotisation;
    }

    public String toString(){
        String res = "Le client " + this.id + " : " + this.prenom + " " + this.nom + " pèse " + this.poids + " kg";
        if(this.isCotisation()){
            res += " a payé sa cotisation.";
        }
        else{
            res += " n'a pas payé sa cotisation.";
        }
        return res;
    }
}
import java.util.Date;

public class Cours{
    private int idC;
    private String intituleCours;
    private float prix;
    private int nbPersonnes;
    private TypeC typeC;
    private Moniteur moniteur;
    private int duree;
    private Date date;
    private int heure;
    public Cours(int idC, String intituleCours, float prix, int nbPersonnes, TypeC typeC, Moniteur moniteur,int duree,Date date,int heure) {
        this.idC = idC;
        this.intituleCours = intituleCours;
        this.prix = prix;
        this.nbPersonnes = nbPersonnes;
        this.typeC = typeC;
        this.moniteur = moniteur;
        this.duree = duree;
        this.heure = heure;
        this.date = date;
    }
    public int getIdC() {
        return idC;
    }
    public void setIdC(int idC) {
        this.idC = idC;
    }
    public String getIntituleCours() {
        return intituleCours;
    }
    public void setIntituleCours(String intituleCours) {
        this.intituleCours = intituleCours;
    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public int getNbPersonnes() {
        return nbPersonnes;
    }
    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }
    public TypeC getTypeC() {
        return typeC;
    }
    public void setTypeC(TypeC typeC) {
        this.typeC = typeC;
    }
    public Moniteur getMoniteur() {
        return moniteur;
    }
    public void setMoniteur(Moniteur moniteur) {
        this.moniteur = moniteur;
    }

    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getHeure() {
        return heure;
    }

    @Override
    public String toString() {
        return "Cours " + this.intituleCours + " du " + this.date + " " + this.heure + " dure " + this.duree
        + " compte " + this.nbPersonnes + " participants.";
    }
}
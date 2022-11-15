public class Cours{
    private int idC;
    private String intituleCours;
    private float prix;
    private int nbPersonnes;
    private TypeC typeC;
    private Moniteur moniteur;
    public Cours(int idC, String intituleCours, float prix, int nbPersonnes, TypeC typeC, Moniteur moniteur) {
        this.idC = idC;
        this.intituleCours = intituleCours;
        this.prix = prix;
        this.nbPersonnes = nbPersonnes;
        this.typeC = typeC;
        this.moniteur = moniteur;
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
    @Override
    public String toString() {
        return "Cours [idC=" + idC + ", intituleCours=" + intituleCours + ", prix=" + prix + ", nbPersonnes="
                + nbPersonnes + ", typeC=" + typeC + ", moniteur=" + moniteur + "]";
    }

}
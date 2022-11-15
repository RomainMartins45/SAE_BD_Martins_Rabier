public class Moniteur {
    private int idM;
    private String nomM;
    private String prenomM;
    private int ageM;
    
    public Moniteur(int idM, String nomM, String prenomM, int ageM) {
        this.idM = idM;
        this.nomM = nomM;
        this.prenomM = prenomM;
        this.ageM = ageM;
    }
    
    public int getIdM() {
        return idM;
    }
    public void setIdM(int idM) {
        this.idM = idM;
    }
    public String getNomM() {
        return nomM;
    }
    public void setNomM(String nomM) {
        this.nomM = nomM;
    }
    public String getPrenomM() {
        return prenomM;
    }
    public void setPrenomM(String prenomM) {
        this.prenomM = prenomM;
    }
    public int getAgeM() {
        return ageM;
    }
    public void setAgeM(int ageM) {
        this.ageM = ageM;
    }

    public String toString(){
        return "Le moniteur " + this.idM + " s'appelle " + this.nomM + " " + this.prenomM + " et a " + this.ageM+ " ans."; 
    }
}

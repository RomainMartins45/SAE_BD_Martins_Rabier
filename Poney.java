public class Poney {
    private int idP;
    private String nomP;
    private float poidsMax;

    public Poney(int idP, String nomP, float poidsMax) {
        this.idP = idP;
        this.nomP = nomP;
        this.poidsMax = poidsMax;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public float getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(float poidsMax) {
        this.poidsMax = poidsMax;
    }

    public String toString(){
        return "Le poney " + this.idP + " s'appelle " + this.nomP + " et peut porter un poids maximum de " + this.poidsMax; 
    }
}

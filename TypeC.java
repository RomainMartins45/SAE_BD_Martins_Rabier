public class TypeC {
    private int idT;
    private String intituleT;

    public TypeC(int idT, String intituleT) {
        this.idT = idT;
        this.intituleT = intituleT;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public String getIntituleT() {
        return intituleT;
    }

    public void setIntituleT(String intituleT) {
        this.intituleT = intituleT;
    }

    @Override
    public String toString() {
        return this.intituleT;
    }
}

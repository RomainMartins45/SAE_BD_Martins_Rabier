import java.sql.Date;

public class Reservation {
    private Cours cours;
    private Client client;
    private Poney poney;
    private int duree;
    private Date date;
    private int heure;
    public Reservation(Cours cours, Client client, Poney poney, int duree, Date date, int heure) {
        this.cours = cours;
        this.client = client;
        this.poney = poney;
        this.duree = duree;
        this.date = date;
        this.heure = heure;
    }

    public Cours getCours() {
        return cours;
    }
    public void setCours(Cours cours) {
        this.cours = cours;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Poney getPoney() {
        return poney;
    }
    public void setPoney(Poney poney) {
        this.poney = poney;
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
    public void setHeure(int heure) {
        this.heure = heure;
    }
    @Override
    public String toString() {
        return "Reservation [cours=" + cours + ", client=" + client + ", poney=" + poney + ", duree=" + duree
                + ", date=" + date + ", heure=" + heure + "]";
    }
}

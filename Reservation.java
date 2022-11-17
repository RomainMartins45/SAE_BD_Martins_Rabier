import java.sql.Date;

public class Reservation {
    private Cours cours;
    private Client client;
    private Poney poney;
    public Reservation(Cours cours, Client client, Poney poney) {
        this.cours = cours;
        this.client = client;
        this.poney = poney;
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
    @Override
    public String toString() {
        return "Reservation [cours=" + cours + ", client=" + client + ", poney=" + poney + "]";
    }
}

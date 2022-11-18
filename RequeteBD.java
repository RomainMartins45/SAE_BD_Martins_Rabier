import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequeteBD{

    ConnexionMySQL connexion;

    public RequeteBD(ConnexionMySQL connexion){
        this.connexion = connexion;
    }

    //synthaxe exemple recup simple
    public int maxNum(){
        try{
            Connection co = this.connexion.getConnexion();
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery("select max(idClient) from Client");
            rs.next();
            int max = rs.getInt(1);
            return max;

        }
        catch(SQLException e){
            System.out.println(e);
            return -1;
        }
    }

    //récupère la liste des poneys appartenant au club
    public List<Poney> getListePoneys() throws SQLException{
        try{
            List<Poney> listPoney = new ArrayList<Poney>();
            Connection co = this.connexion.getConnexion();
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery("select * from Poney");
            while (rs.next()){
                listPoney.add(new Poney(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
            }
            return listPoney;
        }
        catch(SQLException e){
            throw new SQLException("Il n'y a pas de poneys");
        }
    }

    //récupère un TypeC à partir d'un id
    public TypeC recupTypeC(int num) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps = co.prepareStatement("select * from TypeC where idType = ?");
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new TypeC(rs.getInt(1), rs.getString(2));
        }
        catch(SQLException e){
            throw new SQLException("pas d'article lié à ce numéro");
        }
    }

    //récupère un Moniteur à partir d'un id
    public Moniteur recupMoniteur(int num) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps = co.prepareStatement("select * from Moniteur where idM = ?");
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Moniteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }
        catch(SQLException e){
            throw new SQLException("pas de moniteur lié à ce numéro");
        }
    }


    //récupère la liste des cours du club
    public List<Cours> getListeCours() throws SQLException{
        try{
            List<Cours> listCours = new ArrayList<Cours>();
            Connection co = this.connexion.getConnexion();
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery("select * from Cours");
            while (rs.next()){
                listCours.add(new Cours(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), recupTypeC(rs.getInt(5)), recupMoniteur(rs.getInt(6))));
            }
            return listCours;
        }
        catch(SQLException e){
            throw new SQLException("Il n'y a pas de cours");
        }
    }

    //récupère le nombre de personne qui on réservé le cours dont l'id est donnée
    public int getNbReserv(int idCours) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps = co.prepareStatement("select count(*) from RESERVER where idCours = ?");
            ps.setInt(1, idCours);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int nb = rs.getInt(1);
            return nb;
        }
        catch(SQLException e){
            throw new SQLException("Personne n'a réservé ce cours");
        }
    }

    //récupère la liste des cours disponibles
    public List<Cours> getListeCoursDispo() throws SQLException{
        try{
            List<Cours> listCours = new ArrayList<Cours>();
            Connection co = this.connexion.getConnexion();
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery("select * from Cours");
            while (rs.next()){
                if(getNbReserv(rs.getInt(1)) <= rs.getInt(4)){
                    listCours.add(new Cours(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), recupTypeC(rs.getInt(5)), recupMoniteur(rs.getInt(6))));
                }
            }
            return listCours;
        }
        catch(SQLException e){
            throw new SQLException("Il n'y a pas de cours disponibles");
        }
    }

    //vérifie si un client est insrit au club
    public boolean verifClientInscrit(Client client) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps = co.prepareStatement("select * from Client where idClient = ?");
            ps.setInt(1, client.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;           
        }
        catch(SQLException e){
            // throw new SQLException("Le client n'est pas inscrit");
            return false;
        }
    }

    //payer la cotisation (faire passer la cotisation de false à true)
    public void payerCotisation(Client client) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            if(!client.isCotisation()){
                PreparedStatement ps = co.prepareStatement("UPDATE Client SET cotisation = true WHERE idClient = ?");
                ps.setInt(1, client.getId());                
                ResultSet rs = ps.executeQuery();
                rs.next();
                System.out.println("Cotisation payée !");
            }
            else{
                System.out.println("Le client a déjà payer sa cotisation");
            }
        }
        catch(SQLException e){
            throw new SQLException("le client n'existe pas");
        }
    }

    //s'inscrire à un cours
    public void inscriptionCours(Cours cours, Client client, Poney poney, int duree, Date jjmmaaaa, int heure) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps =co.prepareStatement("INSERT INTO RESERVER VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cours.getIdC());
            ps.setInt(2, client.getId());
            ps.setInt(3, poney.getIdP());
            ps.setInt(4, duree);
            ps.setDate(5, jjmmaaaa);
            ps.setInt(6, heure);
            ResultSet rs = ps.executeQuery();
            rs.next();
        }
        catch(SQLException e){
            throw new SQLException("erreur lors de l'inscription au cours");
        }
    }
}
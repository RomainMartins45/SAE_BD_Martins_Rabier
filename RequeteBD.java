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
                listCours.add(new Cours(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), recupTypeC(rs.getInt(5)), recupMoniteur(rs.getInt(6)),rs.getInt(6),rs.getDate(7),rs.getInt(8)));
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
                    listCours.add(new Cours(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), recupTypeC(rs.getInt(5)), recupMoniteur(rs.getInt(6)),rs.getInt(6),rs.getDate(7),rs.getInt(8)));
                }
            }
            return listCours;
        }
        catch(SQLException e){
            throw new SQLException("Il n'y a pas de cours disponibles");
        }
    }

    //vérifie si un client est insrit au club
    public boolean verifClientInscrit(String prenom,String nom,Float poids) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps = co.prepareStatement("select * from Client where nomC = ? and prenomC = ? and poids = ?");
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setFloat(3, poids);
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

    //Ajouter un client
    public void addClient(Client client) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps =co.prepareStatement("INSERT INTO CLIENT VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, client.getId());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getPrenom());
            ps.setFloat(4, client.getPoids());
            ps.setBoolean(5, client.isCotisation());
            ResultSet rs = ps.executeQuery();
            rs.next();
        }
        catch(SQLException e){
            throw new SQLException("erreur lors de l'inscription au cours");
    }
}

    //payer la cotisation (faire passer la cotisation de false à true)
    public Client getClient(String prenom,String nom,Float poids) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps = co.prepareStatement("select idClient,cotisation from Client where nomC = ? and prenomC = ? and poids = ?");
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setFloat(3, poids);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            boolean cotisation = rs.getBoolean(2);
            Client client = new Client(id,nom,prenom,poids,cotisation);
            return client;
        }
        catch(SQLException e){
            throw new SQLException("Le client n'existe pas");
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
    public void inscriptionCours(Cours cours, Client client, Poney poney) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps =co.prepareStatement("INSERT INTO RESERVER VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cours.getIdC());
            ps.setInt(2, client.getId());
            ps.setInt(3, poney.getIdP());
            ps.setInt(4, cours.getDuree());
            ps.setDate(5, new java.sql.Date(cours.getDate().getTime()));
            ps.setInt(6, cours.getHeure());
            ResultSet rs = ps.executeQuery();
            rs.next();
        }
        catch(SQLException e){
            throw new SQLException("erreur lors de l'inscription au cours");
    }
}

    //récupère la liste des moniteurs 
    public List<Moniteur> getMoniteurs() throws SQLException{
        try{
            List<Moniteur> listMoniteurs = new ArrayList<Moniteur>();
            Connection co = this.connexion.getConnexion();
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery("select * from Moniteur");
            while (rs.next()){
                listMoniteurs.add(new Moniteur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
            return listMoniteurs;
        }
        catch(SQLException e){
            throw new SQLException("Il n'y a pas de moniteurs");
        }
    }

    //récupère la liste des poneys sur lesquels le client peut monter
    public List<Poney> getListePoneys(Client client) throws SQLException{
        try{
            List<Poney> listPoney = new ArrayList<Poney>();
            Connection co = this.connexion.getConnexion();
            Statement s = co.createStatement();
            ResultSet rs = s.executeQuery("select * from Poney");
            while (rs.next()){
                if(client.getPoids() <= rs.getFloat(3)){
                    listPoney.add(new Poney(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
                }
            }
            return listPoney;
        }
        catch(SQLException e){
            throw new SQLException("Il n'y a pas de poneys éligibles à ce client");
        }
    }

    //ajoute un client à la base de donnée
    public void inscriptionCours(Client client) throws SQLException{
        try{
            Connection co = this.connexion.getConnexion();
            PreparedStatement ps =co.prepareStatement("INSERT INTO Client VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1,client.getId());
            ps.setString(2,client.getNom());
            ps.setString(3,client.getPrenom());
            ps.setFloat(4,client.getPoids());
            ps.setBoolean(5,client.isCotisation());
            ResultSet rs = ps.executeQuery();
            rs.next();
        }
        catch(SQLException e){
            throw new SQLException("erreur lors de l'inscription du client");
    }
}}
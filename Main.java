import java . sql .*;

public class Main {
    public static void main ( String [] args ) throws SQLException {
        ConnexionMySQL co ;
        String loginMySQL = "rabier";
        String mdpMySQL = "rabier";
        String nomServeur = "servinfo-mariadb";
        String nomBase = "DB" + loginMySQL ;
        co = new ConnexionMySQL(nomServeur, nomBase, loginMySQL, mdpMySQL);
        Client jean = new Client(1, "Dupont", "Jean", 0.5f, true);
        if(co.getConnecte()){
            System.out.println("La connexion c’est bien passée");
            RequeteBD req = new RequeteBD(co);
            //appel des requetes pour les tester
            req.payerCotisation(jean);
        }
        else{
            System.out.println("La connexion à votre BD ne c’est pas faite");
        }
    }
}

//javac *.java
//java -cp .:/usr/share/java/mariadb-java-client.jar Main
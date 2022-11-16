import java . sql .*;

public class Main {
    public static void main ( String [] args ) throws SQLException {
        ConnexionMySQL co ;
        String loginMySQL = "rabier";
        String mdpMySQL = "rabier";
        String nomServeur = "servinfo-mariadb";
        String nomBase = "DB" + loginMySQL ;
        co = new ConnexionMySQL(nomServeur, nomBase, loginMySQL, mdpMySQL);
        if(co.getConnecte()){
            System.out.println("La connexion c’est bien passée");
            RequeteBD req = new RequeteBD(co);
            //appel des requetes pour les tester
            // System.out.println(req.getListePoneys());
            // System.out.println(req.getListeCours());
            // System.out.println(req.getNbReserv(1));
            // System.out.println(req.getListeCoursDispo());
            // System.out.println(req.verifClientInscrit(new Client(1,"Dupont","Jean", Float.parseFloat("50"),false)));
        }
        else{
            System.out.println("La connexion à votre BD ne c’est pas faite");
        }
    }
}

//javac *.java
//java -cp .:/usr/share/java/mariadb-java-client.jar Main
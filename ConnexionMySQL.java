import java.sql.*;

public class ConnexionMySQL {
	Connection mysql=null;
    boolean connecte=false;
    
	public ConnexionMySQL(String nomServeur, String nomBase, String nomLogin, String motDePasse) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver MySQL non trouvé?");
			mysql=null;
			return;
		}
		try {
			mysql = DriverManager.getConnection(
					"jdbc:mysql://"+nomServeur+":3306/"+nomBase,nomLogin, motDePasse);
			connecte=true;
		} catch (SQLException e) {
			System.out.println("Echec de connexion!"); 
			System.out.println(e.getMessage());
			mysql=null;
			return;
		}
	}
    public Connection getConnexion(){
        return this.mysql;
    }
    public boolean getConnecte(){
        return this.connecte;
    }
	
}
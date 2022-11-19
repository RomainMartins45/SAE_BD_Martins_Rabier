import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class application{
  public static void main(String[] args) throws SQLException {
    ConnexionMySQL co = new ConnexionMySQL("servinfo-mariadb", "DBmartins", "martins", "martins");
    boolean continuer = true;
    while(continuer){
        RequeteBD requeteBD = new RequeteBD(co);
        System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
        System.out.println("|     APPLICATION GRAND GALOP     |");
        System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
        List<String> options = new ArrayList<>();
        options.add("S'inscrire au poney club      |");
        options.add("Voir les poneys du club       |");
        options.add("Voir les cours disponibles    |");
        options.add("Voir la liste des moniteurs   |");
        options.add("S'inscrire à un cours         |");
        options.add("Payer sa cotisation annuelle  |");
        options.add("Quittez                       |");
        for(int i =0;i<options.size();i++){
            System.out.println("|" + (i+1) + ". " + options.get(i));
        }
        System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        Scanner sc = new Scanner(System.in);
        System.out.println("\nChoisissez ce que voulez faire en rentrant un chiffre entre 1 et " + options.size());
        String choix = sc.nextLine();
        int choixI = Integer.parseInt(choix);
        switch(choixI){
            case 1:
                System.out.println("Veuillez rentrez votre prénom");
                String prenom = sc.nextLine();
                System.out.println("Veuillez rentrez votre nom");
                String nom = sc.nextLine();
                System.out.println("Veuillez rentrez votre poids en kilogrammes");
                String poidsText;
                Float poids;
                // Vérifie si l'utilisateur rentre bien un nombre et non un String
                try{
                    poidsText = sc.nextLine();
                    poids = Float.parseFloat(poidsText);
                }
                catch(NumberFormatException e){
                    System.out.print("Veuillez rentrez un chiffre\n");
                    break;
                }
                // Gére la cotisation de l'utilisateur qui s'inscrit
                System.out.println("Souhaitez vous payé votre cotisation maintenant (OUI/NON)");
                String cotisation = sc.nextLine();
                if (cotisation.equals("OUI")){
                    Client client = new Client(requeteBD.maxNum()+1,nom,prenom,poids,true);
                    requeteBD.addClient(client);
                    System.out.println("Inscription validée");
                }
                else if (cotisation.equals("NON")){
                    // Mettre l'id au max + 1
                    Client client = new Client(requeteBD.maxNum()+1,nom,prenom,poids,false);
                    requeteBD.addClient(client);
                    System.out.println("Inscription validée");
                }
                // Si l'utilisateur rentre autre chose que "OUI" ou que "NON"
                else{
                    System.out.println("Nous n'avons pas compris votre choix, veuillez vous réinscrire");
                }
                break;

            case 2:
                // Affiche tout les poneys enregistrés dans la BD 
                System.out.println("Les poneys présents dans le poney club sont :");
                List<Poney> poneys = requeteBD.getListePoneys();
                for (Poney poney : poneys){
                    System.out.println(poney);}
                break;
            case 3:
            // Affiche tout les cours disponibles (ou il reste de la place pour s'inscrire) enregistrés dans la BD 
                System.out.println("Les cours disponibles sont :");
                List<Cours> listeCours = requeteBD.getListeCoursDispo();
                for (Cours cours : listeCours){
                    System.out.println(cours);}
                break;
            case 4:
            // Affiche tout les moniteurs enregistrés dans la BD 
                System.out.println("Les moniteurs travaillant au grand galop sont :");
                List<Moniteur> listeMoniteurs = requeteBD.getMoniteurs(); // récupère la liste des moniteurs
                for (Moniteur moniteur : listeMoniteurs){
                    System.out.println(moniteur);}
            // Gérera l'inscription à un cours
            case 5:
                System.out.println("Veuillez rentrez vos informations personelles\n");
                System.out.println("Veuillez rentrez votre prénom");
                String prenomUtil = sc.nextLine();
                System.out.println("Veuillez rentrez votre nom");
                String nomUtil = sc.nextLine();
                System.out.println("Veuillez rentrez votre poids en kilogrammes");
                String poidsTextUtil;
                Float poidsUtil;
                Client clientInscriptionCours;
                // Vérifie si l'utilisateur rentre bien un nombre et non un String
                try{
                    poidsTextUtil = sc.nextLine();
                    poidsUtil = Float.parseFloat(poidsTextUtil);
                }
                catch(NumberFormatException e){
                    System.out.println("Veuillez rentrez un nombre");
                    break;
                }
                // Vérifie si le client est inscrit dans la BD 
                if(requeteBD.verifClientInscrit(prenomUtil, nomUtil, poidsUtil)){
                    clientInscriptionCours = requeteBD.getClient(prenomUtil, nomUtil, poidsUtil);
                }
                // Le client n'est pas inscrit
                else{
                    System.out.println("Nous ne vous connaissons pas, veuillez vous inscrire avant de réserver un cours");
                    break;
                }
                // vérifie si le client a payé sa cotisation annuelle (cotisation à true)
                if(!(clientInscriptionCours.isCotisation())){
                    System.out.println("Veuillez payer votre cotisation annuelle avant de vous inscrire à un cours");
                    break;
                }
                // Affiche la liste des poneys que le client peut avoir (Poids du client est plus petit que le poids max du poney)
                    System.out.println("Veuillez choisir quel poney vous intéresserez :\n");
                    System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
                    System.out.println("|        Poneys Disponibles       |");
                    System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
                    List<Poney> listeDePoneys = requeteBD.getListePoneys();
                    String ligne;
                    int taille;
                    for(int ind =0;ind<listeDePoneys.size();ind++){
                        ligne = "|" + (ind+1) + ". " + listeDePoneys.get(ind);
                        taille = ligne.length();     
                        while (taille < 34){
                            ligne += " ";
                            taille += 1;
                        }
                        ligne += "|\n";
                        System.out.print(ligne);
                        ligne = "";
                    }
                    System.out.println("|_________________________________|");
                    String choixPoney = sc.nextLine();
                    int choixPoneyI = Integer.parseInt(choixPoney);
                    Poney poneyChoisis = new Poney(999999, "nom",Float.parseFloat("999999")); // Si je n'itialise pas le poney, la variable n'est pas reconnu quand je fais inscriptionCours(coursChoisis, clientInscriptionCours, poneyChoisis)
                    // Récupère le poney dans la liste grâce au choix de l'utilisateur
                    if(1 <= choixPoneyI || choixPoneyI <= listeDePoneys.size()){
                        poneyChoisis = listeDePoneys.get(choixPoneyI - 1);
                    }
                    else{
                        System.out.println("Nous n'avons pas compris votre choix, veuillez recommencez\n");
                        break;
                    }
                    // Affichera tout les cours disponibles
                    System.out.println("\nVeuillez choisir quel cours vous intéresserez :\n");
                    System.out.println("Cours Disponibles\n");

                    List<Cours> listeDeCours = requeteBD.getListeCoursDispo();
                    String ligneC;
                    int tailleC;
                    for(int ind =0;ind<listeDeCours.size();ind++){
                        ligneC = (ind+1) + ". " + listeDeCours.get(ind);
                        tailleC = ligneC.length();     
                        while (tailleC < 50){
                            ligneC += " ";
                            tailleC += 1;
                        }
                        ligneC += "\n";
                        System.out.print(ligneC);
                        ligneC = "";
                    }
                    String choixCours = sc.nextLine();
                    int choixCoursI = Integer.parseInt(choixCours);
                    Cours coursChoisis = listeDeCours.get(choixCoursI);
                    // Inscrit le client avec le poney choisis  dans le cours
                    requeteBD.inscriptionCours(coursChoisis, clientInscriptionCours, poneyChoisis);
                break;

            case 6:
                System.out.println("Veuillez rentrez vos informations personelles\n");
                System.out.println("Veuillez rentrez votre prénom");
                String prenomCotisation = sc.nextLine();
                System.out.println("Veuillez rentrez votre nom");
                String nomCotisation = sc.nextLine();
                System.out.println("Veuillez rentrez votre poids en kilogrammes");
                String poidsTextCotisation;
                Float poidsCotisation;
                Client clientCotisation;
                // Vérifie si l'utilisateur rentre bien un nombre et non un String
                try{
                    poidsTextCotisation = sc.nextLine();
                    poidsCotisation = Float.parseFloat(poidsTextCotisation);
                }
                catch(NumberFormatException e){
                    System.out.println("Veuillez rentrez un nombre");
                    break;
                }
                // Vérifie si le client est inscrit dans la BD 
                if(requeteBD.verifClientInscrit(prenomCotisation, nomCotisation, poidsCotisation)){
                    clientCotisation = requeteBD.getClient(prenomCotisation, nomCotisation, poidsCotisation);
                }
                // Le client n'est pas inscrit
                else{
                    System.out.println("Nous ne vous connaissons pas, veuillez vous inscrire avant de réserver un cours");
                    break;
                }
                // vérifie si le client a payé sa cotisation annuelle (cotisation à true)
                if(!(clientCotisation.isCotisation())){
                    requeteBD.payerCotisation(clientCotisation);
                    System.out.println("Votre cotisation vient d'être payé, merci à vous.");
                }
                else{
                    System.out.println("Votre cotisation est déjà payé.");
                }
                    break;

            case 7:
                continuer = false;
                System.out.println("Merci et au revoir !");
                break;
            
        }
    }
  }
}




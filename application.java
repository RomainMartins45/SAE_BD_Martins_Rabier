import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class application{
  public static void main(String[] args) {
    boolean continuer = true;
    while(continuer){
        // RequeteBD requeteBD = new RequeteBD();
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
                try{
                    poidsText = sc.nextLine();
                    poids = Float.parseFloat(poidsText);
                }
                catch(NumberFormatException e){
                    System.out.print("Veuillez rentrez un chiffre\n");
                    break;
                }
                System.out.println("Souhaitez vous payé votre cotisation maintenant (OUI/NON)");
                String cotisation = sc.nextLine();
                if (cotisation.equals("OUI")){
                    // Mettre l'id au max + 1 et rajouter le Client dans la BD 
                    Client client = new Client(0,nom,prenom,poids,true);
                }
                else if (cotisation.equals("NON")){
                    // Mettre l'id au max + 1
                    Client client = new Client(0,nom,prenom,poids,false);
                }
                else{
                    System.out.println("Nous n'avons pas compris votre choix, veuillez vous réinscrire");
                }
                break;

            case 2:
                System.out.println("Les poneys présents dans le poney club sont :");
                // List<Poney> poneys = requeteBD.getListePoneys() récupère la liste des poneys
                // for (Poney poney : poneys){
                //     System.out.println(poney);
                break;
            case 3:
                System.out.println("Les cours disponibles sont :");
                // List<Cours> listeCours = requeteBD.getCoursDisponible() récupère la liste des cours dispo
                // for (Cours cours : listeCours){
                //     System.out.println(cours);
                break;
            case 4:
                System.out.println("Les moniteurs travaillant au grand galop sont :");
                // List<Moniteur> listeMoniteurs = requeteBD.getMoniteurs() récupère la liste des moniteurs
                // for (Moniteur moniteur : listeMoniteurs){
                //     System.out.println(moniteur);
            case 5:
                System.out.println("Veuillez rentrez vos informations personelles\n");
                System.out.println("Veuillez rentrez votre prénom");
                String prenomUtil = sc.nextLine();
                System.out.println("Veuillez rentrez votre nom");
                String nomUtil = sc.nextLine();
                System.out.println("Veuillez rentrez votre poids en kilogrammes");
                String poidsTextUtil;
                Float poidsUtil;
                try{
                    poidsTextUtil = sc.nextLine();
                    poidsUtil = Float.parseFloat(poidsTextUtil);
                }
                catch(NumberFormatException e){
                    System.out.print("Veuillez rentrez un chiffre\n");
                    break;
                }
                System.out.println("Souhaitez vous payé votre cotisation maintenant (OUI/NON)");
                String cotisationUtil = sc.nextLine();
                Client clientAAjouter;
                if (cotisationUtil.equals("OUI")){
                    // Mettre l'id au max + 1 et rajouter le Client dans la BD 
                    clientAAjouter = new Client(0,nomUtil,prenomUtil,poidsUtil,true);
                }
                else if (cotisationUtil.equals("NON")){
                    // Mettre l'id au max + 1
                    clientAAjouter = new Client(0,nomUtil,prenomUtil,poidsUtil,false);
                }
                else{
                    System.out.println("Nous n'avons pas compris votre choix, veuillez recommencez\n");
                    break;
                }
                // if(requeteBD.estClient(clientAAjouter)){
                    System.out.println("Veuillez choisir quel poney vous intéresserez :\n");
                    System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
                    System.out.println("|        Poneys Disponibles       |");
                    System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
                    // List<Poney> listeDePoneys = requeteBD.getPoneyDisponiblePourClient(clientAAjouter);
                    List<Poney> listeDePoneys = new ArrayList<>();
                    listeDePoneys.add(new Poney(1, "aaaaaaaaaaaaaaaaaaa", Float.parseFloat("42.2")));
                    listeDePoneys.add(new Poney(2, "b", Float.parseFloat("42.2")));
                    listeDePoneys.add(new Poney(3, "c", Float.parseFloat("42.2")));
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
                    System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");

                    System.out.println("\nVeuillez choisir quel cours vous intéresserez :\n");
                // else{
                //     System.out.println("Nous ne vous trouvez pas encore inscrit au poney club grand galop\n");
                //     System.out.println("Veuillez vous inscrire au club avant de vous inscrire à un cours.\n");
                // }
                break;

            case 7:
                continuer = false;
                break;
            }
        }
    }
  }



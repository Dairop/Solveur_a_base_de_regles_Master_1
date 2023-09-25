import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ChainageAvant implements Strategie{
    

    public void executer(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, boolean trace){

        Chronometre.start();
        BaseDeFaits baseDeFaitsEnTampon = BaseDeFaits.copy(baseDeFaits);
        BaseDeRegles baseDeReglesEnTampon = BaseDeRegles.copy(baseDeRegles);

        boolean inf = true;
        int nbInf = 0;
        boolean dec = true;

        
        while (inf) {
            // Réinitialisez inf à false au début de chaque itération
            inf = false;

            for (int i = 0; i < baseDeReglesEnTampon.taille(); i++) {
                dec = true;
                Regle regle = baseDeReglesEnTampon.avoirRegleParIndice(i);

                for (int j = 0; j < regle.taillePremice(); j++) {
                    Element premice = regle.avoirPremiceParIndice(j);
                    // Vérification si la prémisse est satisfaite
                    if (!baseDeFaitsEnTampon.contient(premice)
                    || (baseDeFaitsEnTampon.contient(premice) && 
                    baseDeFaitsEnTampon.avoirValeurFait(premice.nom()) != regle.avoirValeurPremice(premice.nom()))) {
                        dec = false; // La prémisse n'est pas satisfaite, la règle ne peut pas être exécutée
                        break;
                    }
                    
                }
                // Si toutes les prémises sont satisfaites, exécutez la règle
                if (dec) {
                    //ici on a la convertion des variables par exemple
                    //si je fais convertionVariables.get(vitesse) on aura rapide
                    //cela donne HashMap<RegleVariable, FaitVariable>
                    HashMap<String, ArrayList<String>> convertionsVariables = avoirTraductionVariable(regle, baseDeFaits);
                        
                        
                    // Ajoutez le résultat de la règle à la base de faits
                    for (int k = 0; k < regle.tailleConsequent(); k++) {
                        //maintenant on a juste a ajouté les conséquents si on ne les a pas.

                        //ici on va remplacer le conséquent par sa version traduite

                        ArrayList<String> consequentTraduit = traduireConsequent(convertionsVariables, regle.avoirConsequentParIndice(k).nom());

                        if (!baseDeFaitsEnTampon.contient(consequentTraduit)){
                            baseDeFaitsEnTampon.ajouterFait(consequentTraduit);
                            if (trace){
                                System.out.println("\n--------- Nombre d'inférences : " +nbInf);
                                System.out.println("\nOn a : "+regle.avoirPremices().toString()+" donc on utilise la règle : \n"+regle.toString()+" et on obtient : \n"+consequentTraduit.toString()+" \nNouvelle base de faits : \n"+baseDeFaitsEnTampon.toString());
                            }
                        }
                        baseDeReglesEnTampon.enleverRegle(regle.nom());
                    }

                    inf = true; // Indique qu'au moins une règle a été exécutée à cette itération
                    nbInf++; // Incrémente le compteur de règles exécutées
                }
            }
        }      
        Chronometre.stop();
        System.out.println("Résultat : Temps d'exécution : "+Chronometre.time()+" ms / Nombres d'inférences : "+nbInf);
        System.out.println(baseDeFaitsEnTampon.toString());
    }

    public static ArrayList<String> traduireConsequent(HashMap<String, ArrayList<String>> conversionsVariables,
    String consequent) {
ArrayList<String> translations = new ArrayList<>();
traduireConsequentHelper(conversionsVariables, consequent, 0, new StringBuilder(), translations);
return translations;
}

public static ArrayList<String> traduireConsequent(HashMap<String, ArrayList<String>> conversionsVariables,
                                                       String consequent) {
        ArrayList<String> translations = new ArrayList<>();
        traduireConsequentHelper(conversionsVariables, consequent, 0, new StringBuilder(), translations);
        return translations;
    }

}

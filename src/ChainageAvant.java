public class ChainageAvant implements Strategie{
    
    public void executer(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles){

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
                    // Ajoutez le résultat de la règle à la base de faits
                    for (int k = 0; k < regle.tailleConsequent(); k++) {
                        if (!baseDeFaitsEnTampon.contient(regle.avoirConsequentParIndice(k)))
                            baseDeFaitsEnTampon.ajouterFait(regle.avoirConsequentParIndice(k));
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
}

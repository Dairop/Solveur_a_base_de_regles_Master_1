public class ChainageAvant implements Strategie{
    
    public void executer(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles){
        boolean inf = true;
        int nbInf = 0;

        boolean dec = true;

        while (inf){
            inf = false;
            for (int i = 0; i < baseDeRegles.getTaille();i++){
                dec = true;
                Regle regle = baseDeRegles.getRegle(i);
                for (int j = 0; j < regle.getTaillePremice(); j++) {
                    Element premice = regle.getPremice(j); // Utilisation de j ici
    
                    // Vérification si la prémisse est satisfaite
                    if (!baseDeFaits.contient(premice)) {
                        dec = false; // La prémisse n'est pas satisfaite, la règle ne peut pas être exécutée
                        break; // Sortir de la boucle dès qu'une prémisse n'est pas satisfaite
                    }
                }
    
                // Si toutes les prémises sont satisfaites, exécutez la règle
                if (dec) {
                    // Ajoutez le résultat de la règle à la base de faits
                    baseDeFaits.ajouter(regle.get$);
                    inf = true; // Indique qu'au moins une règle a été exécutée à cette itération
                    nbInf++; // Incrémente le compteur de règles exécutées
                }
            }
        }
    }
}

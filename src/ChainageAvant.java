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
                for (int j = 0; j < regle.getTaillePremice();j++){
                    Element premice = regle.getPremice(i);
                }
            }
        }
    }
}

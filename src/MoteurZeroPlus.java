import java.sql.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoteurZeroPlus extends Moteur {
    ArrayList<Predicat> _predicats;

    public MoteurZeroPlus(){}

    public MoteurZeroPlus(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles) {
        super(baseDeFaits, baseDeRegles);
    }

    public MoteurZeroPlus(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, Strategie strategie){
        super(baseDeFaits, baseDeRegles, strategie);
    }

    public MoteurZeroPlus(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, Strategie strategie, boolean trace){
        super(baseDeFaits, baseDeRegles, strategie, trace);
    }



    public static boolean estPredicat(String chaine) {
        // Utilisation d'une expression régulière pour vérifier le format
        String regex = "^[a-zA-Z0-9]+\\(\\[a-zA-Z0-9]+\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chaine);
        
        return matcher.matches();
    }


    public void analyserRegles(){
        _predicats.clear();
        ArrayList<String> listeVariables = new ArrayList<String>();

        int nbRegles = this._baseDeRegles.taille();

        for (int i = 0; i < nbRegles; i++){
            Regle r = this._baseDeRegles.avoirRegleParIndice(i);
            
            //recuperer tous les Elements et regarder si certains sont des Predicats
            ArrayList<Element> premicesDeR = r.avoirPremicesListe();
            for (int p_i = 0; p_i < premicesDeR.size(); p_i++){
                //cherche s'il y a des parenthèses dans le nom       
                String pr_nom = premicesDeR.get(p_i).nom();
                String[] parametres = pr_nom.split(",");

                if (estPredicat(pr_nom)){
                    _predicats.add(new 
                        Predicat(pr_nom.split("(")[0], //nom du Predicat
                        parametres.length)      //nombre de parametres dans le predicat
                    );

                    for (String param: parametres) {
                        if (param.charAt(0) >= 65 && param.charAt(0) <= 90){
                            listeVariables.add(param);
                        }
                    }


                    //à chaque regle, remplacer les variables par leurs valeurs possibles s'il y en a
                    remplacerVariables(premicesDeR, listeVariables);
                }                
            }
        
        }
    }


    public ArrayList<Element> remplacerVariables( ArrayList<Element> listePremices, ArrayList<String> listeVariables){
        ArrayList<Element> result = new ArrayList<Element>();

        



        return result;
    }


}

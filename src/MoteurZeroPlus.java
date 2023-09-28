import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoteurZeroPlus extends Moteur{
    ArrayList<Predicat> _predicats = new ArrayList<Predicat>();

    public MoteurZeroPlus(){}

    public MoteurZeroPlus(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles) {
        super(baseDeFaits, baseDeRegles);
    }

    public MoteurZeroPlus(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, Strategie strategie){
        super(baseDeFaits, baseDeRegles, strategie);
    }

    public MoteurZeroPlus(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, Strategie strategie, boolean trace, boolean verifierIncoherences){
        super(baseDeFaits, baseDeRegles, strategie, trace, verifierIncoherences);
    }

    public static void executer(MoteurZeroPlus m){

        m._strategie.executer(m._baseDeFaits, m._baseDeRegles, m._trace);
        try {
            if (m._verifierIncoherences)
                m.verifierIncoherences();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (m._strategie == null) return;
        m.analyserRegles();
    }

    public boolean estPredicat(String chaine) {
        // Utilisation d'une expression régulière pour vérifier le format abc(E, M, pf5)
        
        String regex = "[A-Za-z0-9]+\\((!?[A-Za-z0-9]+,(\\s?)+)*!?[A-Za-z0-9]+\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chaine);
        
        return matcher.matches();
    }

    public String stringAleatoire(int taille){
        String alphabet = "0123456789abcdefghijklmnopqrstuvxyz";
        String stringAleatoire = "";
            for (int j = 0; j < taille; j++){
                int ch = (int)(alphabet.length() * Math.random());
                stringAleatoire += alphabet.charAt(ch);
            }
        return stringAleatoire;
    }


    //genere des valeurs possibles aleatoires temporairement, à remplacer dès que possible
    public void valeursPossiblesAleatoires(ArrayList<String> array){
        for (int i = 0; i < 3; i++){
            array.add(stringAleatoire(5));
        }
    }


    public void analyserRegles(){
        Moteur.print("Début de l'analyse des règles afin de remplacer les variables ...");

        _predicats.clear();
        
        ArrayList<String> listeVariables = new ArrayList<String>();

        ArrayList<ArrayList<String>> valeursPossiblesVariables = new ArrayList<ArrayList<String>>();

        int nbRegles = this._baseDeRegles.taille();

        for (int i = 0; i < nbRegles; i++){
            Regle r = this._baseDeRegles.avoirRegleParIndice(i);
            Moteur.print("  Analyse la regle "+r.toString()+" ...");


            //recuperer tous les Elements de la règle et regarder si certains sont des Predicats
            ArrayList<Element> elementsDeR = new ArrayList<Element>(r.avoirPremicesListe());
            elementsDeR.addAll(r.avoirConsequentsListe());

            for (int p_i = 0; p_i < elementsDeR.size(); p_i++){
                //cherche s'il y a des parenthèses dans l'element     
                String pr = elementsDeR.get(p_i).toString();  
                String pr_nom = elementsDeR.get(p_i).nom();
                
                Moteur.print("\n    Analyse de: '"+pr+"'");

                if (estPredicat(pr)){
                    String[] parametres = pr.substring(pr.indexOf("(")+1, pr.indexOf(")")).split(",");

                   Moteur.print("        Predicat trouvé\n");
                    
                    _predicats.add(new 
                        Predicat(pr_nom,        //nom du Predicat
                        parametres.length)      //nombre de parametres dans le predicat
                    );

                    for (String param: parametres) {
                        if (param.charAt(0) >= 65 && param.charAt(0) <= 90){
                            listeVariables.add(param);

                            ArrayList<String> vals = new ArrayList<String>();
                            valeursPossiblesAleatoires(vals);
                            valeursPossiblesVariables.add(vals);
                        }
                    }
                } else {
                    Moteur.print("        Aucun prédicat trouvé\n");
                }              
            }

            //à chaque regle, remplacer les variables par leurs valeurs possibles s'il y en a
            Integer nombreReglesSupprimees = 
            remplacerVariables(r, listeVariables, valeursPossiblesVariables);
            i -= nombreReglesSupprimees;

            Moteur.print(nombreReglesSupprimees.toString());
        
        }

        //afficher les nouvelles regles
        Moteur.print("nouvelles regles: ");
        for (int i = 0; i < this._baseDeRegles.taille(); i++) Moteur.print("  "+this._baseDeRegles.avoirRegleParIndice(i).toString());
    }

    //renvoie le nombre de regles supprimées afin de ne pas perdre le compte dans la boucle principale
    public int remplacerVariables( Regle r, ArrayList<String> listeVariables, 
                ArrayList<ArrayList<String>> valeursPossiblesVariables){
        if (listeVariables.size() == 0) return 0;

        //on remplace les occurences de la variable en tête de la pile
        String varRemplacee = listeVariables.get(0);


        this._baseDeRegles.enleverRegle(r.nom());


        for (int i = 0; i < valeursPossiblesVariables.get(0).size(); i++){
            //copie de la regle d'origine avec les occurences de la variable remplacées
            String stringNouvelleRegle = r.toString().replaceAll("\\b" + varRemplacee + "\\b", valeursPossiblesVariables.get(0).get(i));
            stringNouvelleRegle = stringNouvelleRegle.replaceFirst(r.nom() + " : ", "");
            String nomNouvelleRegle = stringAleatoire(6);

            Moteur.print("    Nouvelle combinaison:   " + nomNouvelleRegle + " : "+ stringNouvelleRegle);

            Regle nouvelleRegle = new Regle(
                //nouvelle regle avec un nouveau nom
                nomNouvelleRegle + " : " + stringNouvelleRegle
            );

        
            this._baseDeRegles.ajouterRègle(nouvelleRegle);
        }

        listeVariables.remove(0);
        valeursPossiblesVariables.remove(0);

        remplacerVariables(r, listeVariables, valeursPossiblesVariables);

        return 1;
    }


}

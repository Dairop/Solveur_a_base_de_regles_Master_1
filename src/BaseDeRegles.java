import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class BaseDeRegles {

    private ArrayList<Regle> _reglesListe = new ArrayList<>();

    public void ajouterRègle(Regle regle){
        _reglesListe.add(regle);
    }
    
    public int taille(){
        return _reglesListe.size();
    }

    public Regle avoirRegleParIndice(int index){
        return _reglesListe.get(index);
    }

    public String toString(){
        String resultat = "";
        for (int i = 0;i < _reglesListe.size();i++){
            resultat+=_reglesListe.get(i).toString()+"\n";
        }
        return resultat;
    }

    public static BaseDeRegles copy(BaseDeRegles baseDeRegles) {
        BaseDeRegles nouvelleBase = new BaseDeRegles();
        for (int i = 0; i < baseDeRegles.taille();i++)
            nouvelleBase.ajouterRègle(baseDeRegles.avoirRegleParIndice(i).clone());
        return nouvelleBase;
    }

    public BaseDeRegles clone(){
        BaseDeRegles nouvelle = new BaseDeRegles();
        for (int i = 0; i < _reglesListe.size();i++)
            nouvelle.ajouterRègle(_reglesListe.get(i).clone());

        return nouvelle;
    }

    public void enleverRegle(String nom){
        for (int i = 0; i < _reglesListe.size();i++)
            if (_reglesListe.get(i).nom().equals(nom)){
                _reglesListe.remove(i);
                return;
            }
    }

    public void verifierIncoherences() throws Exception {
        verifierDoublons();
        verifierNomsDouble();
        
        //ici on va tester les incohérences structurelles
        
        /*
            Ici, on va prendre tous les contextes d'un élément en conséquence d'une règle
            
        */
        int niveau = 10;
        ArrayList<Element> elementsQuiSontConsequents = new ArrayList<>();

        //ici on récupère tous les éléments qui sont en conséquents
        for (int i = 0; i < _reglesListe.size();i++){
            for (int j = 0; j < _reglesListe.get(i).tailleConsequent();j++)
                if (!elementsQuiSontConsequents.contains(_reglesListe.get(i).avoirConsequentParIndice(j)))
                    elementsQuiSontConsequents.add(_reglesListe.get(i).avoirConsequentParIndice(j));
        }

        

        //ici on fait un dictionnaire avec les éléemnts de élémentsQuiSontConséquents associés à leur 
        HashMap<Element, ArrayList<Element>> contextesDictionnaires = new HashMap<>();

        for (int i = 0; i < elementsQuiSontConsequents.size();i++){

            ArrayList<Element> tamponElementsCalcules = new ArrayList<>();
            ArrayList<Element> tamponElementsNonCalcules = new ArrayList<>();

            tamponElementsNonCalcules.add(elementsQuiSontConsequents.get(i));
            for (int j = 0; j < niveau;j++){
                ArrayList<Element> tamponCalculProchainNiveau = new ArrayList<>();
                //calcul du contexte des éléments de tampons non calculés
                for (int k = 0; k < tamponElementsNonCalcules.size();k++){
                    //on parcoure les règles pour trouver celles qui ont comme conséquent l'élément
                    ArrayList<Regle> reglesQuiOntCommeConsequentLelementNonCalcules = new ArrayList<>();
                    for (int l = 0; l < _reglesListe.size();l++){
                        for (int m = 0; m < _reglesListe.get(l).tailleConsequent();m++){
                            if (_reglesListe.get(l).avoirConsequentParIndice(m).equals(tamponElementsNonCalcules.get(k))){
                                reglesQuiOntCommeConsequentLelementNonCalcules.add(_reglesListe.get(l));
                            }
                        }
                    }

                    //on parcoure toutes les règles pour voir tous le contexte
                    for (int l = 0; l < reglesQuiOntCommeConsequentLelementNonCalcules.size();l++){
                        for (int m = 0; m < reglesQuiOntCommeConsequentLelementNonCalcules.get(l).taillePremice();m++){
                            Element premice = reglesQuiOntCommeConsequentLelementNonCalcules.get(l).avoirPremiceParIndice(m);
                            if (!tamponCalculProchainNiveau.contains(premice) && 
                            !tamponElementsCalcules.contains(premice) && 
                            !tamponElementsNonCalcules.contains(premice))
                                tamponCalculProchainNiveau.add(premice);
                        }
                    }
                }

                //ajout des éléments qui viennent d'être calculés : 
                for (int k = 0; k < tamponElementsNonCalcules.size();k++)
                    tamponElementsCalcules.add(tamponElementsNonCalcules.get(k));
                
                tamponElementsNonCalcules.clear();
                tamponElementsNonCalcules = new ArrayList<>(tamponCalculProchainNiveau);

                
            }
            contextesDictionnaires.put(elementsQuiSontConsequents.get(i), tamponElementsCalcules);
        }
        
        //maintenant on va vérifier s'il y a des incohérences

        for (Element cle : contextesDictionnaires.keySet()) {
            //ensuite on veut obtenir toutes les regles qui ont comme prémice l'élément du contextDictionnaire
            ArrayList<Regle> reglesQuiOntCommePremicesLelement = new ArrayList<>();
            for (int j = 0; j < _reglesListe.size();j++){
                for (int k = 0; k < _reglesListe.get(j).taillePremice();k++){
                    if (_reglesListe.get(j).avoirPremiceParIndice(k).equals(cle))
                        reglesQuiOntCommePremicesLelement.add(_reglesListe.get(j));
                }
            }

            //ensuite on va transformer les règles 
            for (int k = 0; k < reglesQuiOntCommePremicesLelement.size();k++){
                ArrayList<Element> premicesAvecContexte = new ArrayList<>();
                
                //on remplit le premicesAvecContexte avec les prémices de base
                for (int l = 0; l < reglesQuiOntCommePremicesLelement.get(k).taillePremice();l++){
                    premicesAvecContexte.add(reglesQuiOntCommePremicesLelement.get(k).avoirPremiceParIndice(l));
                }
                //et ensuite avec le contexte
                ArrayList<Element> tamponDictionnaire = contextesDictionnaires.get(cle);
                for (int l = 0; l < tamponDictionnaire.size();l++)
                    premicesAvecContexte.add(tamponDictionnaire.get(l));


                //ici on va vérifier que l'on est pas dans les prémices : A ET !A -> B
                //c'est-à-dire un élément et sa négation
                for (int l = 0; l < premicesAvecContexte.size();l++){
                    Element e = new Element(premicesAvecContexte.get(l).nom(),!premicesAvecContexte.get(l).estVrai());
                    if (premicesAvecContexte.contains(e)){
                        Moteur.print("On a une erreur incohérence dans la base de règle : "+  premicesAvecContexte.toString());
                    }else if (reglesQuiOntCommePremicesLelement.get(k).avoirConsequents().contient(e)){
                        Moteur.print("On a une erreur incohérence dans la base de règle : "+  premicesAvecContexte.toString());
                    }
                    
                }
            }


        }

    }

    private void verifierDoublons() {
        ArrayList<Regle> newList = new ArrayList<>();
        
        for (Regle regle : _reglesListe) {
            if (!newList.contains(regle)) {
                newList.add(regle);
            }
        }
        
        if (newList.size() != _reglesListe.size()){
            _reglesListe = newList;
            Moteur.print("On a modifié la base de règles pour enlever les doublons.\n");
        }    
    }

    public void trierPlusDePremices(){
        Comparator<Regle> comparateur = Comparator.comparingInt(Regle::getValeur);
        Collections.sort(_reglesListe, comparateur);
    }

    private void verifierNomsDouble() throws Exception {
        ArrayList<String> elementsVerifies = new ArrayList<>(); 
        ArrayList<Regle> erreursRegles = new ArrayList<>();

        for (int i = 0; i < _reglesListe.size();i++){
            if (elementsVerifies.contains(_reglesListe.get(i).nom())){
                erreursRegles.add(_reglesListe.get(i));
            }else
                elementsVerifies.add(_reglesListe.get(i).nom());
        }

        if (erreursRegles.isEmpty())
            return;
        String message = "Il y a des noms doublés dans les règles : ";
        for (int i = 0; i < erreursRegles.size();i++)
            message+="; \n;"+erreursRegles.get(i).toString();
        message+="\n1 : Pour arrêter le programme\n2 : Pour renommer les règles automatiquement\n3 : Renommer manuellement";
        String reponse = Moteur.lireReponse(message);
        if (reponse.contains("1"))
            throw new Exception("Programme interrompu car des règles ont les mêmes noms.");
        else if (reponse.contains("2")){
            int nb = 0;
            for (int i = 0;i < erreursRegles.size();i++){
                String nouveauNom =erreursRegles.get(i).nom()+nb;

                while (elementsVerifies.contains(nouveauNom)){
                    nouveauNom = erreursRegles.get(i).nom()+nb;
                    nb++;
                }
                nb++;
                erreursRegles.get(i).setNom(nouveauNom);
                elementsVerifies.add(nouveauNom);
            }
        }else {
            for (int i = 0;i < erreursRegles.size();i++){
                message = "Renommage de la règle : "+erreursRegles.toString()+" \nNouveau nom :"; 
                String nouveauNom = Moteur.lireReponse(message);

                while (elementsVerifies.contains(nouveauNom)){
                    nouveauNom = Moteur.lireReponse("Nom déjà pris, il en faut un autre : ");
                }
                erreursRegles.get(i).setNom(nouveauNom);
                elementsVerifies.add(nouveauNom);
            }

            Moteur.print(elementsVerifies.toString());
        }

        
    }

    public Regle avoirRegleParNom(String string) {
        for (int i = 0; i < _reglesListe.size();i++){
            if (_reglesListe.get(i).nom().equals(string))
                return _reglesListe.get(i);
        }
        return null;
    }


}

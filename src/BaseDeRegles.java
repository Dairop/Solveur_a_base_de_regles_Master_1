import java.util.ArrayList;

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
            System.out.println("On a modifié la base de règles pour enlever les doublons.On a donc : \n"+toString());
        }

        
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
        }

        System.out.println("Nouvelle base de faits corrigée : "+toString());

    }

}

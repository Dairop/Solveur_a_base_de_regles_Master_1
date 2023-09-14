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

    public void verifierIncoherences() {
    }

}

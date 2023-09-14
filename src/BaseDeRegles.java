import java.util.ArrayList;

public class BaseDeRegles {

    private ArrayList<Regle> _reglesListe = new ArrayList<>();

    public void ajouterRègle(Regle règle){
        _reglesListe.add(règle);
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

}

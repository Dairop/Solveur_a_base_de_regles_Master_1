import java.util.ArrayList;

public class BaseDeRegles {

    private ArrayList<Regle> _reglesListe = new ArrayList<>();

    public void ajouterRègle(Regle règle){
        _reglesListe.add(règle);
    }

    public int getTaille(){
        return _reglesListe.size();
    }

    public Regle getRegle(int index){
        return _reglesListe.get(index);
    }

    public String toString(){
        String result = "";
        for (int i = 0;i < _reglesListe.size();i++){
            result+=_reglesListe.get(i).toString()+"\n";
        }
        return result;
    }

}

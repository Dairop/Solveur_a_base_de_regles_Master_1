import java.util.ArrayList;

public class BaseDeRegles {

    ArrayList<Règle> _reglesListe = new ArrayList<>();

    public void ajouterRègle(Règle règle){
        _reglesListe.add(règle);
    }

    public String toString(){
        String result = "";
        for (int i = 0;i < _reglesListe.size();i++){
            result+=_reglesListe.get(i).toString()+"\n";
        }
        return result;
    }

}

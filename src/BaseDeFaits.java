import java.util.ArrayList;

public class BaseDeFaits {
    private ArrayList<Element> _base = new ArrayList<Element>();

    public BaseDeFaits(){}
    
    public void ajouterFait(Element nouveauFait){
        this._base.add(nouveauFait);
    }

    public void retireFait(Element faitARetirer) {
        this._base.remove(faitARetirer);
    }

    public Element recupererFaitParIndice(int id){
        return this._base.get(id);
    }

    public ArrayList<Element> recupererBase() {
        return this._base;
    }

    public int nombreFaits(){
        return _base.size();
    }

    public String toString(){
        String str = "[";
        for (int i = 0; i < _base.size(); i++){
            str += "["+_base.get(i).toString()+"]";
            if (i < _base.size()-1) str += ", ";
        }
        str += "]";
        return str;
    }

    public boolean contient(Element element){
        return _base.contains(element);
    }
    

    public static BaseDeFaits copy(BaseDeFaits b){
        BaseDeFaits nouvelleBase = new BaseDeFaits();
        for (int i = 0; i < b.nombreFaits();i++)
            nouvelleBase.ajouterFait(b.recupererFaitParIndice(i).clone());
        return nouvelleBase;
    }


    //alors 1 == true
    //0 == false
    //Sinon renvoie -1 si on sait pas
    public int avoirValeurFait(String premiceNom) {
        for (int i = 0; i < _base.size();i++){
            if (_base.get(i).nom().equals(premiceNom)){
                if (_base.get(i).estVrai())
                    return 1;
                else 
                    return 0;
            }

        }
        return -1;
    }
}

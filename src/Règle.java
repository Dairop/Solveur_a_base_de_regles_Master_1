public class Règle {
    
    String _nom;
    Premisse _consequent;
    Premisse _premice;


    public Règle(String nom, Premisse premice, Premisse consequent){
        this._nom = nom;
        this._premice = premice;
        this._consequent = consequent;
    }

    public String toString(){
        return _nom + " : "+_premice.toString()+" -> "+_consequent;
    }


}

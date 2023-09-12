public class Regle {
    
    String _nom;
    Premisse _consequent;
    Premisse _premice;


    public Regle(String nom, Premisse premice, Premisse consequent){
        this._nom = nom;
        this._premice = premice;
        this._consequent = consequent;
    }

    public Regle(String string) {
        System.out.print(string.split(":")[0]+"a\n");
    }

    public String toString(){
        return _nom + " : "+_premice.toString()+" -> "+_consequent;
    }


}

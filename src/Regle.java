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
        _nom = string.split(":")[0].trim();
        _premice = new Premisse(string.split(":")[1].split("->")[0].trim());
        _consequent = new Premisse(string.split(":")[1].split("->")[1].trim());
    }

    public String toString(){
        return _nom + " : "+_premice.toString()+" -> "+_consequent;
    }


}

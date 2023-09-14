public class Regle {
    
    private String _nom;
    private Premisse _consequent;
    private Premisse _premice;

    public Regle(String nom, Premisse premice, Premisse consequent){
        this._nom = nom;
        this._premice = premice;
        this._consequent = consequent;
    }

    //Mini-parser
    public Regle(String string) {
        _nom = string.split(":")[0].trim();
        _premice = new Premisse(string.split(":")[1].split("->")[0].trim());
        _consequent = new Premisse(string.split(":")[1].split("->")[1].trim());
    }

    public int taillePremice(){
        return _premice.taille();
    }

    public Element avoirPremiceParIndice(int indice){
        return _premice.avoirElementIndice(indice);
    }

    public int tailleConsequent(){
        return _consequent.taille();
    }

    public Element avoirConsequentParIndice(int indice){
        return _consequent.avoirElementIndice(indice);
    }

    public String toString(){
        return _nom + " : "+_premice.toString()+" -> "+_consequent;
    }


}

public class Element {
    String _nom;
    Boolean _estVrai;

    public Element(String nom){
        this._nom = nom;
        this._estVrai = true;
    }

    public Element(String nom, Boolean estVrai){
        this._nom = nom;
        this._estVrai = estVrai;
    }

    public String getNom() {
        return _nom;
    }

    public String toString(){
        
        return ((this._estVrai) ? "": "!") + _nom;
    }
}

public class Element {
    private String _nom;
    private Boolean _estVrai;

    public Element(String nom){
        this._nom = nom.trim();

        this._estVrai = true;
        if (this._nom.charAt(0) == '!') this._estVrai = false;
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

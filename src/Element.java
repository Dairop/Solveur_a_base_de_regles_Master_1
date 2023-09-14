import java.util.Objects;

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

    public String nom() {
        return _nom;
    }

    public String toString(){
        
        return ((this._estVrai) ? "": "!") + _nom;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return _nom.equals(element._nom) && _estVrai == element._estVrai;
    }
    
    //création d'une copie indépendante
    public Element clone(){
        return new Element(_nom, _estVrai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_nom, _estVrai);
    }

    public boolean estVrai() {
        return _estVrai;
    }
}

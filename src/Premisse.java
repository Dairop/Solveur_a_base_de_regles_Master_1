import java.util.ArrayList;

public class Premisse {
    
    private ArrayList<Element> _elementListe = new ArrayList<>();

    public Premisse(ArrayList<Element> elementListe){
        this._elementListe = elementListe;
    }

    public Element avoirElementIndice(int indice){
        return _elementListe.get(indice);
    }

    public int taille(){
        return _elementListe.size();
    }

    public Premisse(String description){
        String[] tampon = description.split("ET");
        for (int i = 0; i < tampon.length;i++)
            _elementListe.add(new Element(tampon[i].trim()));
    }

    public Premisse() {
    }

    public String toString(){
        if (_elementListe.size() == 0)
            return "Premisse Vide";
        String resultat = "";
        for (int i = 0; i < _elementListe.size();i++){
            if (i == 0)
                resultat+=_elementListe.get(i).toString();
            else
                resultat+=" & " +_elementListe.get(i).toString();
        }
        return resultat;
    }

    public void ajouterElement(Element e){
        _elementListe.add(e);
    }

    public Premisse clone(){
        Premisse nouvelle = new Premisse();
        for (int i = 0; i < _elementListe.size();i++)
            nouvelle.ajouterElement(_elementListe.get(i).clone());
        return nouvelle;
    }

    public ArrayList<Element> listeElements(){
        return this._elementListe;
    }

}

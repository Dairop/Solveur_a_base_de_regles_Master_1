import java.util.ArrayList;

public class Premisse {
    
    ArrayList<Element> _elementList = new ArrayList<>();

    public Premisse(ArrayList<Element> elementList){
        this._elementList = elementList;
    }

    public Premisse(String description){
        String[] tampon = description.split("ET");
        for (int i = 0; i < tampon.length;i++)
            _elementList.add(new Element(tampon[i].trim()));
    }

    public String toString(){
        if (_elementList.size() == 0)
            return "Premisse Vide";
        String resultat = "";
        for (int i = 0; i < _elementList.size();i++){
            if (i == 0)
                resultat+=_elementList.get(i).toString();
            else
                resultat+=" & " +_elementList.get(i).toString();
        }
        return resultat;
    }

}

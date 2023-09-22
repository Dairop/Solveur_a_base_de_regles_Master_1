import java.util.ArrayList;

public class Variable {
    String _nom;
    
    // une valeur peut être "chat", "(a ET b)", "c", "(!a ET (b ET !d))", ...
    String _value = null;

    public Variable(String nom){
        this._nom = nom;
    };
    public Variable(String nom, String valeur){        
        this._nom = nom;
        this._value = valeur;
    };
}

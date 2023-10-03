public class Fichier {
    

    public static String sauvegardeEnString(){
        String result = PanneauPrincipale.faits.getText();
        result+="\nReglesSauvegarde:\n";
        result+=PanneauPrincipale.regles.getText();
        result+="\nVariables:\n";
        result+=PanneauVariable.variablesEntree.getText();
        result+="\n\n";
        return "Pas encore implémenté";
    }
}

public class Fichier {
    

    public static String sauvegardeEnString(){
        String result = PanneauPrincipale.faits.getText();
        result+="\nReglesSauvegarde:\n";
        result+=PanneauPrincipale.regles.getText();
        result+="\nVariables:\n";
        result+=PanneauVariable.variablesEntree.getText();
        result+="\nPaquets : \n";
        result+=PanneauPaquet.paquets.getText();
        return result;
    }

    public static void chargerFichier(String contenu){
        PanneauPrincipale.faits.setText(contenu.split("\nReglesSauvegarde:\n")[0]);
    }
}

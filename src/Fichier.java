public class Fichier {

	
	public static final String urlCourante = Fichier.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	
	public static String sauvegardeEnString() {
		String result = PanneauPrincipale.faits.getText();
		result += "\nReglesSauvegarde:\n";
		result += PanneauPrincipale.regles.getText();
		result += "\nVariables:\n";
		result += PanneauVariable.variablesEntree.getText();
		result += "\nPaquets : \n";
		result += PanneauPaquet.paquets.getText();
		return result;
	}

	public static void chargerFichier(String contenu) {
		try {
			PanneauPrincipale.faits.setText(contenu.split("\nReglesSauvegarde:\n")[0]);
			contenu = contenu.split("\nReglesSauvegarde:\n")[1];
			PanneauPrincipale.regles.setText(contenu.split("\nVariables:\n")[0]);
			contenu = contenu.split("\nVariables:\n")[1];

			if (contenu.split("\nPaquets : \n").length == 0) {
				PanneauVariable.variablesEntree.setText("");
				PanneauPaquet.paquets.setText("");
			} else {
				if (contenu.split("\nPaquets : \n").length > 1) {
					PanneauVariable.variablesEntree.setText(contenu.split("\nPaquets : \n")[0]);
					PanneauPaquet.paquets.setText(contenu.split("\nPaquets : \n")[1]);
				}
			}
		} catch (Exception e) {
			Moteur.print("Fichier non supporté ou introuvable");
		}
	}
	
	public static String getUrlCourante() {
		System.out.println("\n\n"+urlCourante+"\n\n");
		if (!urlCourante.substring(urlCourante.length()-4).equals(".jar"))
			return urlCourante+"";
		else 
			return urlCourante.substring(0, urlCourante.lastIndexOf("/"))+"/Ress/";
	}
}

public class ChainageAvant implements Strategie {

	public void executer(BaseDeFaits baseDeFaits, BaseDeRegles baseDeRegles, boolean trace, Tri tri) {

		Chronometre.start();
		BaseDeFaits baseDeFaitsEnTampon = BaseDeFaits.copy(baseDeFaits);
		BaseDeRegles baseDeReglesEnTampon = BaseDeRegles.copy(baseDeRegles);

		boolean inf = true;
		int nbInf = 0;
		boolean dec = true;

		while (inf) {
			// Réinitialisez inf à false au début de chaque itération
			inf = false;

			for (int i = 0; i < baseDeReglesEnTampon.taille(); i++) {
				baseDeReglesEnTampon = tri.trier(baseDeReglesEnTampon, baseDeFaitsEnTampon);
				dec = true;
				Regle regle = baseDeReglesEnTampon.avoirRegleParIndice(i);

				for (int j = 0; j < regle.taillePremice(); j++) {
					Element premice = regle.avoirPremiceParIndice(j);
					// Vérification si la prémisse est satisfaite
					if (!baseDeFaitsEnTampon.contient(premice)
							|| (baseDeFaitsEnTampon.contient(premice) && baseDeFaitsEnTampon
									.avoirValeurFait(premice.nom()) != regle.avoirValeurPremice(premice.nom()))) {
						dec = false; // La prémisse n'est pas satisfaite, la régle ne peut pas être exécutée
						break;
					}

				}
				// Si toutes les prémises sont satisfaites, exécutez la régle
				if (dec) {
					// Ajoutez le résultat de la régle à la base de faits
					for (int k = 0; k < regle.tailleConsequent(); k++) {
						if (!baseDeFaitsEnTampon.contient(regle.avoirConsequentParIndice(k))) {
							Element e = new Element(regle.avoirConsequentParIndice(k).nom(),
									regle.avoirConsequentParIndice(k).estVrai(), nbInf);
							baseDeFaitsEnTampon.ajouterFait(e);
							regle.setDecouvertTempsPremice(nbInf);
							if (trace) {
								MoteurZeroPlus.print("\n--------- Nombre d'inférences : " + nbInf);
								MoteurZeroPlus.print("\nOn a : " + regle.avoirPremices().toString()
										+ " donc on utilise la régle : \n" + regle.toString() + " et on obtient : \n"
										+ regle.avoirConsequents().toString() + " \nNouvelle base de faits : \n"
										+ baseDeFaitsEnTampon.toString());
							}
						}
						baseDeReglesEnTampon.enleverRegle(regle.nom());
					}

					inf = true; // Indique qu'au moins une regle a été exécutée à cette itération
					nbInf++; // Incrémente le compteur de regles exécutées
				}
			}
		}
		Chronometre.stop();
		if (MoteurZeroPlus.moteur1)
			MoteurZeroPlus.print("Le patient a donc les observations et les (non-)maladies possibles : ");
		else
			MoteurZeroPlus.print("Résultat : Temps d'exécution : " + Chronometre.time()
					+ " ms / Nombres d'inférences : " + nbInf + ", \nOn a la base de faits : ");
		MoteurZeroPlus.print(baseDeFaitsEnTampon.toString());
	}
}

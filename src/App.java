import javax.swing.JOptionPane;

public class App implements Runnable {
	public static void main(String[] args) throws Exception {
		int s = JOptionPane.showConfirmDialog(null,
				"Lancer le moteur 1 avec la recherche de maladie ? \n(Il y a juste le texte qui change, c'est-à-dire qu'on ne change que l'affichage des informations par exemple \non appelera les faits, les observations médicales, ou les conséquences comme les maladies possibles). \nEn cliquant sur Non, on aura le moteur 0+ avec attribut valeur général.");
	
		if (s == 1) {
			Graphism.lancer();
			new Thread(new App()).start();
		} else if (s == 0) {
			MoteurZeroPlus.moteur1 = true;
			Graphism.lancer();
			new Thread(new App()).start();
		}

	}

	@Override
	public void run() {
	}
}

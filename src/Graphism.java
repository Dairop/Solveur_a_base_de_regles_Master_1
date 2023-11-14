import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class Graphism implements Runnable {

	static int typeChainage = 0;
	static boolean changementPanel = true;

	// interface
	static final Color couleurBouton = new Color(100, 100, 100);
	static final Color couleurTextBouton = Color.white;
	static final Color couleurFond = Color.lightGray;

	// élément graphique
	static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE, DORIAN BIAGI");
	private static PanneauPersonnalise panel = new PanneauPrincipale();

	static void initialiser() {
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);

		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setLayout(null);

		fenetre.setContentPane(panel);
		panel.initialiser();

	}

	@Override
	public void run() {
		if (MoteurZeroPlus.moteur1) {
			String contenu = "";
			String cheminDuFichier = Fichier.getUrlCourante() + "Moteur1.txt";
			try {
				FileReader fileReader = new FileReader(cheminDuFichier);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String ligne;
				while ((ligne = bufferedReader.readLine()) != null) {
					contenu += ligne + "\n";
				}

				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Fichier.chargerFichier(contenu);
		}
		initialiser();
		changementPanel = false;
		while (true && !changementPanel) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.refresh();
			panel.repaint();

		}

	}

	public static void lancer() {
		new Thread(new Graphism()).start();
	}

	public static void setPanel(PanneauPersonnalise newPanel) {
		changementPanel = true;

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		panel = newPanel;

		lancer();
	}
}

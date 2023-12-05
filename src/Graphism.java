import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Graphism implements Runnable {

	static int typeChainage = 0;
	static boolean changementPanel = true;

	// interface
	static final Color couleurBouton = new Color(119, 144, 198);
	static final Color couleurTextBouton = Color.white;
	static final Color couleurFond = Color.white;

	// élément graphique
	static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE, DORIAN BIAGI");
	private static PanneauPersonnalise panel = new PanneauPrincipal();

	static void initialiser() {
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);

		fenetre.setSize(1920/5, 1080/5); // ne pas supprimer, correspond à la taille actuelle lorsqu'on commence un redimensionnement 
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		fenetre.setMinimumSize(new Dimension(1920/5, 1080/5));
		panel.setLayout(null);

		fenetre.setContentPane(panel);
		panel.initialiser();

	}

	@Override
	public void run() {
		if (MoteurZeroPlus.moteur1) {
			String contenu = "";
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Il faut sélectionner le fichier Moteur1.txt qui est placé à côté du .jar");
			
			FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
			fileChooser.addChoosableFileFilter(txtFilter);
			fileChooser.setFileFilter(txtFilter);

			int result = fileChooser.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				// L'utilisateur a choisi un fichier
				File selectedFile = fileChooser.getSelectedFile();
				String selectedPath = selectedFile.getAbsolutePath();
				String cheminDuFichier = selectedPath;
				File f = new File(cheminDuFichier);
				if (!f.exists()){
					JOptionPane.showMessageDialog(null, "Le fichier Moteur1.txt n'est pas présent.\nIl doit être juste à côté du dossier src.\n Ici:\n"+cheminDuFichier);
					System.exit(0);
				}
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
			}else{

				JOptionPane.showMessageDialog(null,"Vous n'avez pas valider le fichier donc le programme s'arrête.");
				System.exit(0);
			}
			
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

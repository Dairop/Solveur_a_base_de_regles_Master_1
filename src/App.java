import java.awt.Dimension;

import javax.swing.*;

public class App implements Runnable {
	public static void main(String[] args) throws Exception {
		String[] options = {"Interface complète", "Interface simplifiée avec détections de maladies"};

		UIManager.put("OptionPane.minimumSize", new Dimension(500,200)); 
		int result = JOptionPane.showOptionDialog(
                null,
                "Choisissez l'interface appropriée: \nSi vous n'êtes pas sûr, sélectionnez la simplifiée",
                "Choix de l'interface",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

		if (result == JOptionPane.CLOSED_OPTION) return;

		if (result == 0) {
			Graphism.lancer();
			new Thread(new App()).start();
		} else if (result == 1) {
			MoteurZeroPlus.moteur1 = true;
			Graphism.lancer();
			new Thread(new App()).start();
		}

		//lancer
	}

	@Override
	public void run() {
	}
}

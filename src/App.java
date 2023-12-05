import javax.swing.JOptionPane;
import javax.swing.*;

public class App implements Runnable {
	public static void main(String[] args) throws Exception {
		String[] options = {"Moteur 0+", "Moteur 1"};

		int result = JOptionPane.showOptionDialog(
                null,
                "Choisissez le moteur approprié: ",
                "Le moteur 1 est plus simple à utiliser mais moins personnalisable",
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
	}

	@Override
	public void run() {
	}
}

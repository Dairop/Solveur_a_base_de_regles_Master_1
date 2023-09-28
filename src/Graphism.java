import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Graphism implements Runnable{

    static int typeChainage = 0;
    static boolean changementPanel = true;

    //élément graphique
    static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE, DORIAN BIAGI");
    private static PanelPersonnalise panel = new PanneauPrincipale();


    static void initialiser(){
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setPreferredSize(screenSize);
        fenetre.setLayout(null);
        panel.setLayout(null);

        fenetre.setContentPane(panel);
        panel.initialiser();
        
        panel.repaint();
    }

    
    @Override
    public void run() {
        initialiser();
        changementPanel = false;
        while (true && !changementPanel){
            try {
                Thread.sleep(200);
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

    public static void setPanel(PanelPersonnalise newPanel) {
        changementPanel = true;
    
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    
        fenetre.getContentPane().removeAll();
        fenetre.revalidate();
        fenetre.repaint();
    
        panel = newPanel;
        panel.initialiser(); // Vous pouvez appeler initialiser() si nécessaire pour le nouveau panel.
    
        fenetre.pack();
        fenetre.revalidate();
        fenetre.repaint();
        lancer();
    }
}

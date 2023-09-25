import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Graphism {
    

    static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE");
    static PanelPersonnalise panel = new PanelPersonnalise();

    static void initialiser(){
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);



        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setPreferredSize(screenSize);
        fenetre.setContentPane(panel);
        fenetre.setLayout(null);
        panel.setLayout(null);

        Font texteFont = new Font("Gabriela", Font.PLAIN,20);
        Font titreFont = new Font("Gabriela", Font.BOLD,30);
        
        JTextArea faits = new JTextArea("",200,300);
        faits.setFont(texteFont);
        int w = panel.getWidth();
        int h = panel.getHeight();
        faits.setSize(w/10*4, h/2);
        faits.setLocation(w/20, h/20);
        
        faits.setBackground(Color.white);
        panel.add(faits);
        faits.repaint();

        JTextArea regles = new JTextArea("");
        regles.setFont(texteFont);
        regles.setSize(w/40*19, h/2);
        regles.setLocation(w/2, h/20);
        regles.setBackground(Color.white);
        panel.add(regles);
        regles.repaint();

        
        JLabel faitsLabel = new JLabel("Base de faits");
        faitsLabel.setSize(w/3, h/20);
        faitsLabel.setFont(titreFont);
        faitsLabel.setLocation(w/20, 0);
        faitsLabel.setForeground(Color.black);
        panel.add(faitsLabel);
        faitsLabel.repaint();


        JLabel reglesLabel = new JLabel("Base de règles");
        reglesLabel.setSize(w/3, h/20);
        reglesLabel.setFont(titreFont);
        reglesLabel.setLocation(w/2, 0);
        reglesLabel.setForeground(Color.black);
        panel.add(reglesLabel);
        reglesLabel.repaint();
        

        JTextArea resultat = new JTextArea("");
        resultat.setFont(texteFont);
        resultat.setSize(w/40*19, h/3);
        resultat.setLocation(w/2, h/20*12);
        resultat.setBackground(Color.white);
        panel.add(resultat);
        resultat.repaint();

        JLabel resultatLabel = new JLabel("Résultats");
        resultatLabel.setSize(w/3, h/20);
        resultatLabel.setFont(titreFont);
        resultatLabel.setLocation(w/2, h/20*11);
        resultatLabel.setForeground(Color.black);
        panel.add(resultatLabel);
        resultatLabel.repaint();

        JTextField input = new JTextField("Entrée utilisateur");
        input.setSize(w/40*19, h/20);
        input.setFont(texteFont);
        input.setBackground(Color.gray);
        input.setLocation(w/2, h/40*38);
        input.setForeground(Color.black);
        panel.add(input);
        input.repaint();
        
        System.out.println(resultat.getX());
    }

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Graphism {

    static int typeChainage = 0;
    

    static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE");
    static PanelPersonnalise panel = new PanelPersonnalise();
    static JTextArea resultat = new JTextArea("");

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


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
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
        

        //cresultat = new JTextArea("");
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
        

        JRadioButton chainageAvant = new JRadioButton("Chainage avant");
        chainageAvant.setFont(texteFont);
        chainageAvant.setSelected(true);
        chainageAvant.setSize(w/7, h/10);
        chainageAvant.setLocation(w/40, h/20*12);
        chainageAvant.setName("type");
        chainageAvant.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainageAvant.isSelected()) {
                    typeChainage = 0;
                }
            }
        });
        panel.add(chainageAvant);
        chainageAvant.repaint();

        JRadioButton chainageArriere = new JRadioButton("Chainage arrière");
        chainageArriere.setFont(texteFont);
        chainageArriere.setSize(w/7, h/10);
        chainageArriere.setLocation(w/40, h/20*15);
        chainageArriere.setName("type");
        chainageArriere.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainageArriere.isSelected()) {
                    typeChainage = 1;
                }
            }
        });
        panel.add(chainageArriere);
        chainageArriere.repaint();


        JRadioButton chainagePaquet = new JRadioButton("Chainage par paquets");
        chainagePaquet.setFont(texteFont);
        chainagePaquet.setSize(w/7, h/10);
        chainagePaquet.setLocation(w/40, h/20*18);
        chainagePaquet.setName("type");
        chainagePaquet.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainagePaquet.isSelected()) {
                    typeChainage = 2;
                }
            }
        });
        panel.add(chainagePaquet);
        chainagePaquet.repaint();

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(chainageArriere);
        buttonGroup.add(chainagePaquet);
        buttonGroup.add(chainageAvant);

        JButton aide = new JButton("?");
        aide.setFont(titreFont);
        aide.setSize(w/9, h/10);
        aide.setLocation(w/40*15, h/10*6);
        panel.add(aide);
        aide.repaint();
        

        JButton calculer = new JButton("Calculer");
        calculer.setFont(titreFont);
        calculer.setSize(w/9, h/10);
        calculer.setLocation(w/40*15, h/10*9);
        panel.add(calculer);
        calculer.repaint();

        JCheckBox trace = new JCheckBox("trace");
        trace.setFont(texteFont);
        trace.setSize(w/18,  h/16);
        trace.setLocation(w/40*15+w/36, h/40*33);
        panel.add(trace);
        trace.repaint();

        JCheckBox verifierIncoherences = new JCheckBox("vérification des incohérences");
        verifierIncoherences.setFont(texteFont);
        verifierIncoherences.setSize(w/9,  h/16);
        verifierIncoherences.setLocation(w/40*15, h/40*29);
        panel.add(verifierIncoherences);
        verifierIncoherences.repaint();

        calculer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BaseDeFaits bf = new BaseDeFaits();
                String text = faits.getText();
                String[] lines = text.split("\\r?\\n");
                for (int i = 0; i < lines.length; i++)
                    if (!lines[i].equals(""))
                        bf.ajouterFait(new Element(lines[i]));
                
                BaseDeRegles br = new BaseDeRegles();
                text = regles.getText();
                lines = text.split("\\r?\\n");
                for (int i = 0; i < lines.length; i++)
                    if (!lines[i].equals(""))
                        br.ajouterRègle(new Regle(lines[i]));

                MoteurZeroPlus.print("Faits extraits : "+bf.toString());
                MoteurZeroPlus.print("Règles extraits : "+br.toString());
                Strategie strategie = new ChainageAvant();
                if (typeChainage == 1)
                    strategie = new ChainageArriere();
                else if (typeChainage == 2)
                    strategie = new ChainageParPaquet();

                System.out.println(trace.isSelected());
                
                MoteurZeroPlus moteur = new MoteurZeroPlus(bf, br, strategie,trace.isSelected());
                moteur.executer();
            }
        });
        panel.repaint();
        fenetre.repaint();
    }

}

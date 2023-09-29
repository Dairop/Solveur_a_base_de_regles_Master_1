import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanneauPrincipale extends PanneauPersonnalise{
    
    //composants graphiques
    static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE, DORIAN BIAGI");
    static JScrollPane scrollPane2, scrollPane3;
    static JTextField input = new JTextField();
    static JTextArea faits,regles, resultat = new JTextArea("");
    static JRadioButton chainageArriere, chainageAvant, chainagePaquet;
    static JButton aide = new JButton("?"), calculer, variable = new JButton("Variables");
    static JLabel faitsLabel,reglesLabel, resultatLabel;
    static JCheckBox trace, verifierIncoherences;


    public void paintComponent(Graphics g){
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }


    @Override
    void initialiser() {
        faits = new JTextArea("");
        faits.setBackground(Color.white);
        add(faits);
        
        

        regles = new JTextArea("");
        scrollPane2 = new JScrollPane(regles);
        add(scrollPane2);
        
        faitsLabel = new JLabel("Base de faits");
        add(faitsLabel);


        reglesLabel = new JLabel("Base de règles");
        add(reglesLabel);
        
        scrollPane3 = new JScrollPane(resultat);
        add(scrollPane3);

        resultatLabel = new JLabel("Résultats");
        add(resultatLabel);
 
        add(input);
        ActionListener[] actionListeners = input.getActionListeners();
        for (ActionListener listener : actionListeners) 
            input.removeActionListener(listener);
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Moteur.attenteReponseUtilisateur = false;
        
            }
        });
        

        chainageAvant = new JRadioButton("Chainage avant");
        chainageAvant.setSelected(true);
        chainageAvant.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainageAvant.isSelected()) 
                    Graphism.typeChainage = 0;
            }
        });
        add(chainageAvant);
       
        chainageArriere = new JRadioButton("Chainage arrière");
        chainageArriere.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainageArriere.isSelected()) 
                    Graphism.typeChainage = 1;
            }
        });
        add(chainageArriere);


        chainagePaquet = new JRadioButton("Chainage par paquets");
        chainagePaquet.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainagePaquet.isSelected()) 
                    Graphism.typeChainage = 2;
            }
        });
        add(chainagePaquet);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(chainageArriere);
        buttonGroup.add(chainagePaquet);
        buttonGroup.add(chainageAvant);
        actionListeners = aide.getActionListeners();
        for (ActionListener listener : actionListeners) 
            aide.removeActionListener(listener);
        aide = new JButton("?");
        aide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphism.setPanel(new PanneauRegle());
            }
        });
        add(aide);


        actionListeners = variable.getActionListeners();
        for (ActionListener listener : actionListeners) 
            variable.removeActionListener(listener);
        
        variable = new JButton("Variables");

        variable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphism.setPanel(new PanneauVariable());
            }
        });
        add(variable);
        
        

        calculer = new JButton("Calculer");
        add(calculer);
        
        trace = new JCheckBox("trace");
        add(trace);
        

        verifierIncoherences = new JCheckBox("vérification des incohérences");
        add(verifierIncoherences);
        actionListeners = calculer.getActionListeners();
        for (ActionListener listener : actionListeners) 
            calculer.removeActionListener(listener);
        

        calculer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BaseDeFaits bf = new BaseDeFaits();
                String text = faits.getText();
                String[] lines = text.split("\\r?\\n");

                // Utilisation de StringBuilder pour concaténer les faits
                StringBuilder faitsBuilder = new StringBuilder();
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        bf.ajouterFait(new Element(line));
                        faitsBuilder.append(line).append("\n");
                    }
                }

                BaseDeRegles br = new BaseDeRegles();
                text = regles.getText();
                lines = text.split("\\r?\\n");

                // Utilisation de StringBuilder pour concaténer les règles
                StringBuilder reglesBuilder = new StringBuilder();
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        br.ajouterRègle(new Regle(line));
                        reglesBuilder.append(line).append("\n");
                    }
                }

                MoteurZeroPlus.print("Faits extraits : " + faitsBuilder.toString());
                MoteurZeroPlus.print("Règles extraits : " + reglesBuilder.toString());

                Strategie strategie = new ChainageAvant();
                if (Graphism.typeChainage == 1) {
                    strategie = new ChainageArriere();
                } else if (Graphism.typeChainage == 2) {
                    strategie = new ChainageParPaquet();
                }

                Moteur moteur = new Moteur(bf, br, strategie, trace.isSelected(), verifierIncoherences.isSelected());
                Moteur.executer(moteur);
            }
        });
        
        repaint();
        
    }


    @Override
    void refresh() {
        final Font texteFont = new Font("Gabriela", Font.PLAIN,20);
        final Font titreFont = new Font("Gabriela", Font.BOLD,30);
        final int w = getWidth();
        final int h = getHeight();


        faits.setFont(texteFont);
        faits.setSize(w/10*4, h/2);
        faits.setLocation(w/20, h/20);
        
        regles.setFont(texteFont);
        scrollPane2.setSize(w/40*19, h/2);
        scrollPane2.setLocation(w/2, h/20);

        faitsLabel.setSize(w/3, h/20);
        faitsLabel.setFont(titreFont);
        faitsLabel.setLocation(w/20, 0);

        reglesLabel.setSize(w/3, h/20);
        reglesLabel.setFont(titreFont);
        reglesLabel.setLocation(w/2, 0);
        
        scrollPane3.setSize(w/40*19, h/3);
        resultat.setFont(texteFont);
        scrollPane3.setLocation(w/2, h/20*12);
        
        resultatLabel.setSize(w/3, h/20);
        resultatLabel.setFont(titreFont);
        resultatLabel.setLocation(w/2, h/20*11);
        
        input.setSize(w/40*19, h/20);
        input.setFont(texteFont);
        input.setLocation(w/2, h/40*38);

        variable.setFont(texteFont);
        variable.setSize(w/7, h/10);
        variable.setLocation(w/5, h/20*12);
        
        chainageAvant.setFont(texteFont);
        chainageAvant.setSize(w/7, h/10);
        chainageAvant.setLocation(w/40, h/20*12);
        
        chainageArriere.setFont(texteFont);
        chainageArriere.setSize(w/7, h/10);
        chainageArriere.setLocation(w/40, h/20*15);
        
        chainagePaquet.setFont(texteFont);
        chainagePaquet.setSize(w/7, h/10);
        chainagePaquet.setLocation(w/40, h/20*18);
        
        aide.setFont(titreFont);
        aide.setSize(w/9, h/10);
        aide.setLocation(w/40*15, h/10*6);
        
        calculer.setFont(titreFont);
        calculer.setSize(w/9, h/10);
        calculer.setLocation(w/40*15, h/10*9);
        
        trace.setFont(texteFont);
        trace.setSize(w/18,  h/16);
        trace.setLocation(w/40*15+w/36, h/40*33);
        
        verifierIncoherences.setFont(texteFont);
        verifierIncoherences.setSize(w/9,  h/16);
        verifierIncoherences.setLocation(w/40*15, h/40*29);
        
    }

}

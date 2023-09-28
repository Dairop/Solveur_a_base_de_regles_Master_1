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

public class PanneauPrincipale extends PanelPersonnalise{
    
    //composants graphiques
    static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE, DORIAN BIAGI");
    static JScrollPane scrollPane2, scrollPane3;
    static JTextField input = new JTextField();
    static JTextArea faits,regles, resultat = new JTextArea("");
    static JRadioButton chainageArriere, chainageAvant, chainagePaquet;
    static JButton aide, calculer;
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
        this.add(faits);
        
        

        regles = new JTextArea("");
        scrollPane2 = new JScrollPane(regles);
        this.add(scrollPane2);
        
        faitsLabel = new JLabel("Base de faits");
        faitsLabel.setForeground(Color.black);
        this.add(faitsLabel);


        reglesLabel = new JLabel("Base de règles");
        reglesLabel.setForeground(Color.black);
        this.add(reglesLabel);
        
        scrollPane3 = new JScrollPane(resultat);
        this.add(scrollPane3);

        resultatLabel = new JLabel("Résultats");
        resultatLabel.setForeground(Color.black);
        this.add(resultatLabel);
 
        input.setForeground(Color.black);
        this.add(input);
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Moteur.attenteReponseUtilisateur = false;
        
            }
        });
        

        chainageAvant = new JRadioButton("Chainage avant");
        chainageAvant.setSelected(true);
        chainageAvant.setName("type");
        chainageAvant.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainageAvant.isSelected()) {
                    Graphism.typeChainage = 0;
                }
            }
        });
        this.add(chainageAvant);
       
        chainageArriere = new JRadioButton("Chainage arrière");
        chainageArriere.setName("type");
        chainageArriere.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainageArriere.isSelected()) {
                    Graphism.typeChainage = 1;
                }
            }
        });
        this.add(chainageArriere);


        chainagePaquet = new JRadioButton("Chainage par paquets");
        chainagePaquet.setName("type");
        chainagePaquet.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chainagePaquet.isSelected()) {
                    Graphism.typeChainage = 2;
                }
            }
        });
        this.add(chainagePaquet);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(chainageArriere);
        buttonGroup.add(chainagePaquet);
        buttonGroup.add(chainageAvant);

        aide = new JButton("?");
        aide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphism.setPanel(new PanneauRegle());
            }
        });
        this.add(aide);
        

        calculer = new JButton("Calculer");
        this.add(calculer);
        
        trace = new JCheckBox("trace");
        this.add(trace);
        

        verifierIncoherences = new JCheckBox("vérification des incohérences");
        this.add(verifierIncoherences);
        

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
                if (Graphism.typeChainage == 1)
                    strategie = new ChainageArriere();
                else if (Graphism.typeChainage == 2)
                    strategie = new ChainageParPaquet();

                Moteur moteur = new Moteur(bf, br, strategie,trace.isSelected(), verifierIncoherences.isSelected());
                
                Moteur.executer(moteur);
            }
        });
        this.repaint();
        fenetre.repaint();
    }


    @Override
    void refresh() {
        Font texteFont = new Font("Gabriela", Font.PLAIN,20);
        Font titreFont = new Font("Gabriela", Font.BOLD,30);
        int w = this.getWidth();
        int h = this.getHeight();


        faits.setFont(texteFont);
        faits.setSize(w/10*4, h/2);
        faits.setLocation(w/20, h/20);
        faits.repaint();

        regles.setFont(texteFont);
        scrollPane2.setSize(w/40*19, h/2);
        scrollPane2.setLocation(w/2, h/20);
        scrollPane2.repaint();


        faitsLabel.setSize(w/3, h/20);
        faitsLabel.setFont(titreFont);
        faitsLabel.setLocation(w/20, 0);
        faitsLabel.repaint();

        reglesLabel.setSize(w/3, h/20);
        reglesLabel.setFont(titreFont);
        reglesLabel.setLocation(w/2, 0);
        reglesLabel.repaint();

        scrollPane3.setSize(w/40*19, h/3);
        resultat.setFont(texteFont);
        scrollPane3.setLocation(w/2, h/20*12);
        scrollPane3.repaint();

        resultatLabel.setSize(w/3, h/20);
        resultatLabel.setFont(titreFont);
        resultatLabel.setLocation(w/2, h/20*11);
        reglesLabel.repaint();

        input.setSize(w/40*19, h/20);
        input.setFont(texteFont);
        input.setLocation(w/2, h/40*38);
        input.repaint();

        chainageAvant.setFont(texteFont);
        chainageAvant.setSize(w/7, h/10);
        chainageAvant.setLocation(w/40, h/20*12);
        chainageAvant.repaint();

        chainageArriere.setFont(texteFont);
        chainageArriere.setSize(w/7, h/10);
        chainageArriere.setLocation(w/40, h/20*15);
        chainageArriere.repaint();

        chainagePaquet.setFont(texteFont);
        chainagePaquet.setSize(w/7, h/10);
        chainagePaquet.setLocation(w/40, h/20*18);
        chainagePaquet.repaint();

        aide.setFont(titreFont);
        aide.setSize(w/9, h/10);
        aide.setLocation(w/40*15, h/10*6);
        aide.repaint();

        calculer.setFont(titreFont);
        calculer.setSize(w/9, h/10);
        calculer.setLocation(w/40*15, h/10*9);
        calculer.repaint();

        trace.setFont(texteFont);
        trace.setSize(w/18,  h/16);
        trace.setLocation(w/40*15+w/36, h/40*33);
        trace.repaint();

        verifierIncoherences.setFont(texteFont);
        verifierIncoherences.setSize(w/9,  h/16);
        verifierIncoherences.setLocation(w/40*15, h/40*29);
        verifierIncoherences.repaint();
    }

}

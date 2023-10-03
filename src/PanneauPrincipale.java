import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanneauPrincipale extends PanneauPersonnalise{
    
    //composants graphiques
    static JFrame fenetre = new JFrame("Solveur GIOVANNI CARRE, DORIAN BIAGI");
    static JScrollPane scrollPane2, scrollPane3, scrollPane1;
    static JTextField input = new JTextField();
    static JTextArea faits = new JTextArea(),regles = new JTextArea(), resultat = new JTextArea("");
    static JRadioButton chainageArriere, chainageAvant, chainagePaquet;
    static JButton aide = new JButton("?"), calculer, variable = new JButton("Variables"), charger = new JButton("Charger"), save=new JButton("Save"), paquet = new JButton("Paquets");
    static JLabel faitsLabel,reglesLabel, resultatLabel;
    static JCheckBox trace, verifierIncoherences;
    static JComboBox<String> comboBox;


    public void paintComponent(Graphics g){
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }


    @Override
    void initialiser() {

        faits.setBackground(Color.white);
        scrollPane1 = new JScrollPane(faits);
        add(scrollPane1);        
        

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
        


        add(charger);
        ActionListener[] actionListeners = charger.getActionListeners();
        for (ActionListener listener : actionListeners) 
            charger.removeActionListener(listener);
        charger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers texte (.txt)", "txt");
                fileChooser.setFileFilter(filter);
                
                int returnValue = fileChooser.showOpenDialog(null);
                
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    try {
                        // Lire le contenu du fichier
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
                        String line;
                        StringBuilder content = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        Fichier.chargerFichier(content.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier.");
                    }
                }
            }
        });


        add(paquet);
 
        add(input);
        actionListeners = input.getActionListeners();
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
        add(save);
        

        actionListeners = save.getActionListeners();
        for (ActionListener listener : actionListeners) 
            save.removeActionListener(listener);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers texte (.txt)", "txt");
                fileChooser.setFileFilter(filter);
                
                int returnValue = fileChooser.showSaveDialog(null);
                
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    if (!filePath.toLowerCase().endsWith(".txt")) {
                        filePath += ".txt"; // Assurez-vous que l'extension .txt est ajoutée
                    }
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                        writer.write(Fichier.sauvegardeEnString());

                        writer.close();
                        
                        JOptionPane.showMessageDialog(null, "Données enregistrées avec succès.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du fichier.");
                    }
                }
            }
        });

        calculer = new JButton("Calculer");
        calculer.setBackground(Color.red);
        add(calculer);
        
        trace = new JCheckBox("trace");
        add(trace);
        String[] options = {"Règle dans l'ordre","par le plus de prémisse", "par le fait le plus récent"};
        comboBox = new JComboBox<>(options);
        add(comboBox);
        

        verifierIncoherences = new JCheckBox("vérification des incohérences");
        add(verifierIncoherences);
        actionListeners = calculer.getActionListeners();
        for (ActionListener listener : actionListeners) 
            calculer.removeActionListener(listener);
        
        actionListeners = paquet.getActionListeners();
        for (ActionListener listener : actionListeners) 
            paquet.removeActionListener(listener);

        paquet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graphism.setPanel(new PanneauPaquet());    
            }
        });

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

                HashMap<String, Variable> variables = new HashMap<String, Variable>();
                String[] texteBrut = PanneauVariable.variablesEntree.getText().replace(" ", "").split("\n");          
                
                //conversion du texte brut en variables
                for (int i = 0; i < texteBrut.length; i++){
                    String[] nomPuisRegle = texteBrut[i].split(":");
                    if (nomPuisRegle.length == 2){
                        ArrayList<String> valeursPossibles = new ArrayList<String>();
                        String[] vps = nomPuisRegle[1].split(";");
                        for (String vp : vps) {
                            valeursPossibles.add(vp);
                        }

                        variables.put(nomPuisRegle[0], new Variable(nomPuisRegle[0], valeursPossibles));
                    }

                }

                if (strategie instanceof ChainageParPaquet){    
                    //Il faut mettre les paquets à jour
                    ArrayList<ArrayList<String>> paquets = new ArrayList<>();
                    String[] lignes = PanneauPaquet.paquets.getText().split("\n");
                    for (int i = 0; i < lignes.length;i++)
                        paquets.add(new ArrayList<>(Arrays.asList(lignes[i].split(";"))));
                    
                    MoteurZeroPlus moteur = new MoteurZeroPlus(bf, br, strategie, variables, trace.isSelected(), verifierIncoherences.isSelected());
                    ChainageParPaquet strat = (ChainageParPaquet)strategie;
                    strat.setBlocs(paquets);
                    MoteurZeroPlus.executer(moteur);    
                }else{
                    MoteurZeroPlus moteur = new MoteurZeroPlus(bf, br, strategie, variables, trace.isSelected(), verifierIncoherences.isSelected());
                    MoteurZeroPlus.executer(moteur);
                }
            }
        });
        
        repaint();
        
    }


    @Override
    void refresh() {
        final Font texteFont = new Font("Gabriela", Font.PLAIN,getWidth()/80);
        final Font titreFont = new Font("Gabriela", Font.BOLD,getHeight()/30);
        final int w = getWidth();
        final int h = getHeight();


        faits.setFont(texteFont);
        scrollPane1.setSize(w/10*4, h/2);
        scrollPane1.setLocation(w/20, h/20);
        
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
        
        
        charger.setFont(texteFont);
        charger.setSize(w/14, h/20);
        charger.setLocation(w/100*18, h/40*33);

        paquet.setFont(texteFont);
        paquet.setSize(w/7, h/20);
        paquet.setLocation(w/100*20, h/40*37);

        save.setFont(texteFont);
        save.setSize(w/14, h/20);
        save.setLocation(w/100*22+w/14, h/40*33);

        comboBox.setFont(texteFont);
        comboBox.setSize(w/7, h/20);
        comboBox.setLocation(w/5, h/40*29);


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

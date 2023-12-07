import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DouleurGraphic {
    public final JLabel label;
    public static int nbDouleur = 0;
    private static String[] typeDouleur = {"Intensité","aucune_douleur", "legere_douleur", "douleur_elevee"};
    private static String[] partiesCorps = {"Localisation","tete", "ventre", "bras", "bassin", "jambes", "extremites", "dos" ,"cou", "tout_le_corps"};

    public final JComboBox<String> choixDouleur;
    public final JComboBox<String> choixPartiesCorps;
    
    public DouleurGraphic(){
        nbDouleur++;
        label = new JLabel("Douleur "+nbDouleur);
        choixDouleur = new JComboBox(typeDouleur);
        choixPartiesCorps = new JComboBox(partiesCorps);
    }

    public void setFont(Font titreFont) {
        label.setFont(titreFont);
        choixDouleur.setFont(titreFont);
        choixPartiesCorps.setFont(titreFont);
    }

    public void setSize(int i, int j) {
        label.setSize(i,j);
        choixDouleur.setSize(i,j);
        choixPartiesCorps.setSize(i,j);
    }
}

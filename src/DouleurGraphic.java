import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DouleurGraphic {
    public final JLabel label;
    public static int nbDouleur = 0;
    private static String[] typeDouleur = {"aucune_douleur", "legere_douleur", "douleur_elevee"};
    private static String[] partiesCorps = {"tete", "ventre", "bras", "bassin", "jambes", "extremites", "dos" ,"cou", "tout_le_corps"};

    public final JComboBox<String> choixDouleur;
    public final JComboBox<String> choixPartiesCorps;
    
    public DouleurGraphic(){
        nbDouleur++;
        label = new JLabel("Douleur "+nbDouleur);
        choixDouleur = new JComboBox(typeDouleur);
        choixPartiesCorps = new JComboBox(partiesCorps);
    }
}

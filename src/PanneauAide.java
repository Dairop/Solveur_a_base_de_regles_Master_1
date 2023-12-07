import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanneauAide extends PanneauPersonnalise{

    JLabel titre = new JLabel("Aide");
    JTextArea aide = new JTextArea("Base de faits : \nNous avons un fait par ligne. \nEg:\n\"fievre(elevee)\"  ou  \"!a\" indiquant que nous savons 'a' à faux.\n\nBase de règles: \nUne règle par ligne. \nEg: \nnom : premisse(s) -> consequent(s)\ntel que\nR1 : A ET !B ET C -> S ET !G\nou \nrègle test : A -> B\n\nPour plus d'informations, voir la documentation technique\n\nLe survol des éléments est possible pour plus d'informations.");
    JButtonCustom retour = new JButtonCustom("Retour");
    JScrollPane scroll = new JScrollPane(aide);

    @Override
    void initialiser() {
        setLayout(null);

        add(titre);

        add(scroll);
        ActionListener[] actionListeners = retour.getActionListeners();
        for (ActionListener a : actionListeners)
            retour.removeActionListener(a);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphism.setPanel(new PanneauPrincipal());
            }
        });
        retour.setToolTipText("Retour au menu principal");

        add(retour);

    }

    @Override
    void refresh() {
        int w = getWidth();
        int h = getHeight();
     
        titre.setFont(new Font("Gabriela", Font.BOLD, 20));
        titre.setSize(w/3, h/5);
        titre.setLocation(w/2, h/20);        

        retour.setFont(new Font("Gabriela", Font.BOLD, 20));
        retour.setSize(w/5, h/8);
        retour.setLocation(w/10, h/10*9);
        
        aide.setFont(new Font("Gabriela", Font.BOLD, 20));
        scroll.setSize(w/5*4, h/2);
        scroll.setLocation(w/10, h/5);
    }
    
}

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PanneauRegle extends PanneauPersonnalise{

    JLabel titre = new JLabel("Aide");
    JTextArea aide = new JTextArea("Test");
    JButton retour = new JButton("Retour");

    @Override
    void initialiser() {
        setLayout(null);

        add(titre);

        add(aide);
        ActionListener[] actionListeners = retour.getActionListeners();
        for (ActionListener a : actionListeners)
            retour.removeActionListener(a);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphism.setPanel(new PanneauPrincipale());
            }
        });
        add(retour);
    }

    @Override
    void refresh() {
        int w = getWidth();
        int h = getHeight();


        titre.setFont(new Font("Gabriela", Font.BOLD, 30));
        titre.setSize(w/3, h/5);
        titre.setLocation(w/2, h/20);
        

        retour.setFont(new Font("Gabriela", Font.BOLD, 30));
        retour.setSize(w/5, h/8);
        retour.setLocation(w/10, h/10*9);
        
        aide.setFont(new Font("Gabriela", Font.BOLD, 30));
        aide.setSize(w/5*4, h/2);
        aide.setLocation(w/10, h/5);
    }
    
}

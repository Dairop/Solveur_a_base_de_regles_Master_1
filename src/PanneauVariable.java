import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanneauVariable extends PanneauPersonnalise{

    private static JLabel titre = new JLabel("Variables");
    private static JButton retour = new JButton("Retour");
    public static JTextArea variablesEntree = new JTextArea();
    private static JScrollPane scrollbar;

    @Override
    void initialiser() {
        setLayout(null);

        add(titre);

        scrollbar = new JScrollPane(variablesEntree);

        ActionListener[] actionListeners = retour.getActionListeners();
        for (ActionListener listener : actionListeners) 
            retour.removeActionListener(listener);
        
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Graphism.setPanel(new PanneauPrincipale());
            }
        });
        
        
        add(scrollbar);
        add(retour);
    }

    @Override
    void refresh() {
        int w = getWidth();
        int h = getHeight();

        titre.setFont(new Font("Gabriela", Font.BOLD, 30));
        titre.setSize(w/3, h/20);
        titre.setLocation(w/2, h/50);
        
        retour.setFont(new Font("Gabriela", Font.BOLD, 30));
        retour.setSize(w/3, h/20);
        retour.setLocation(w/20, h/10*9);
        

        variablesEntree.setFont(new Font("Gabriela", Font.BOLD, 30));
        scrollbar.setSize(w/5*4, h/2);
        scrollbar.setLocation(w/10, h/10);
    }
    
}

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class PanelPersonnalise extends JPanel{


    public void paintComponent(Graphics g){
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}

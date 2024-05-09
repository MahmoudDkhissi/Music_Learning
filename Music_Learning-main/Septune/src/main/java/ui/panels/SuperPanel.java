package main.java.ui.panels;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** Cette classe est la classe mère de tous les panels de notre package. 
 * Elle a pour objectif de définir des méthodes communes à tous les pannels 
 * qui permettent de gérer différentes choses tel que :
 * - la gestion de l'image de fond 
 * - l'ajout du listener pour gérer la pause
 * - la récupération d'un potentiel KeyboardPanel associé au panel
*/
public class SuperPanel extends JPanel {

    protected boolean hasBackground = true;

    public SuperPanel() {
        super();
    }

    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBackground(g);
	}
    
    protected void paintBackground(Graphics g) {
        if(hasBackground) {
            Image background = (new ImageIcon(getClass().getResource("/main/resources/pictures/background.png"))).getImage();
		    g.drawImage(background, 0, 0, null);
        }
    }

    public boolean hasKeyboardPanel() {
        return false;
    }

    public KeyboardPanel getKeyboardPanel() {
        return null;
    }



}
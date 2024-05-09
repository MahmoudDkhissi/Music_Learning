package main.java.ui.panels;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class SuperFrame extends JFrame
{
	
    public SuperFrame() {
    	
        super();
        
        // Création et paramétrage de la fenêtre graphique                    
        setTitle("Septune");           
        setSize(500,500);                 
        setExtendedState(JFrame.MAXIMIZED_BOTH);      
        setLocationRelativeTo(null);              
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setFocusable(false);
        setLayout(new BorderLayout());
    }

}
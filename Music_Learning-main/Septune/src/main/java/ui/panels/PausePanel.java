package main.java.ui.panels;
import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class PausePanel extends SuperPanel implements KeyListener {

    // ATTRIBUTS
	// Création des composants
	private JButton backWelcomeButton = new JButton("Back to welcome screen");
    private JButton exitButton = new JButton("Exit");
    private JPanel panel = new JPanel();
    
    public PausePanel() {
        // Initialisation de la fenêtre principale
        super();
        setFocusable(true);
        setLayout(new BorderLayout());

        // Paramétrage des composants
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);

        DesignManager.designMediumButton(backWelcomeButton);
        DesignManager.designMediumButton(exitButton);

        // Ajout des listeners
        backWelcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.backWelcome();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.exit();
            }
        });

        //Ajout des composants au panel intermédiaire
        panel.add(Box.createHorizontalGlue());
        panel.add(backWelcomeButton);
        panel.add(Box.createHorizontalGlue());
        panel.add(exitButton);
        panel.add(Box.createHorizontalGlue());

        

        // Ajout des composants au panel principal
        add(panel);

        // Rendre le panel principal visible
        setVisible(true);
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

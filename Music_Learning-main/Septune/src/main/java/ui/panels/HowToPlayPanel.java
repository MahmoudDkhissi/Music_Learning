package main.java.ui.panels;
import java.awt.*;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class HowToPlayPanel extends SuperPanel  {
    
    JButton quitButton = new JButton("Menu");
    JLabel mode1 = new JLabel("    Chord Quizz : L'application affiche un nom d'accord aléatoire et l'utilisateur essaie de le deviner.");
    JLabel mode2 = new JLabel("    Chord Quizz Reversed : L'applicaiton affiche une forme d'accord et l'utilisateur essaie d'en deviner le nom.");
    JLabel mode3 = new JLabel("    Free Mode : L'utilisateur peut jouer des accords et des notes sur le clavier et l'application les affiche.");
    JLabel mode4 = new JLabel("    Work Scale : Une bande son est jouée et l'utilisateur doit jouer le plus de notes possibles dans la gamme associée.");
    JLabel mode5 = new JLabel("    Work Ear : Des notes de différents instruments et de différentes hauteurs sont données et l'utilisateur doit trouver la hauteur de la note.");
    JLabel mode6 = new JLabel("    Cours de musique : L'utilisateur peut apprendre les bases de la musique notamment la construction d'accords majeurs et mineurs.");
    JPanel buttonPanel = new JPanel();

    public HowToPlayPanel() {

        super();
        setLayout(new GridLayout(7,0));

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);

        DesignManager.designMediumButton(quitButton);

        mode1.setForeground(Color.WHITE);
        mode2.setForeground(Color.WHITE);
        mode3.setForeground(Color.WHITE);
        mode4.setForeground(Color.WHITE);
        mode5.setForeground(Color.WHITE);
        mode6.setForeground(Color.WHITE);

        quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowManager.backWelcome();
			}
		});


        mode1.setFont(new Font("Montserrat", Font.PLAIN, 20));
        mode2.setFont(new Font("Montserrat", Font.PLAIN, 20));
        mode3.setFont(new Font("Montserrat", Font.PLAIN, 20));
        mode4.setFont(new Font("Montserrat", Font.PLAIN, 20));
        mode5.setFont(new Font("Montserrat", Font.PLAIN, 20));
        mode6.setFont(new Font("Montserrat", Font.PLAIN, 20));       
  

        buttonPanel.add(quitButton);
        
        // Ajouter les étiquettes à la fenêtre
        add(mode1);
        add(mode2);
        add(mode3);
        add(mode4);
        add(mode5);
        add(mode6);
        add(buttonPanel);
    } 
}
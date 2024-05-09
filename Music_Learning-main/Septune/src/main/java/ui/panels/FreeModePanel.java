package main.java.ui.panels;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.midi.Keyboard;
import main.java.structures.Chord;
import main.java.tool.Observer;
import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class FreeModePanel extends SuperPanelWithKeyboard implements Observer{
	
	// ATTRIBUTS
	/* Clavier interne à ce panneau */
	Keyboard keyboard;

	// Création des composants
	JLabel chordLabel = new JLabel("Jouez un accord, s'il est reconnu son nom sera affiché ici.");
	JButton quitButton = new JButton("Menu");
	JPanel panel = new JPanel();

	public FreeModePanel() {

		super();	// Appel au constructeur de la classe parente

		// Paramétrage du panel principal 
		setLayout(new BorderLayout());
		setOpaque(false);
		
		// Initialisation des attributs
		keyboard = new Keyboard(); 
		keyboardPanel = new KeyboardPanel(keyboard);

		// Ajout des observateurs
		keyboard.addObserver(keyboardPanel);
		keyboard.addObserver(this);
		
		// Paramétrage des composants

        DesignManager.designMediumLabel(chordLabel);
		DesignManager.designMediumButton(quitButton);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setOpaque(false);

		quitButton.addActionListener(e -> {
			keyboard.closeConnection();
			WindowManager.backWelcome();
		});

		// Ajout des composants aux panels intermédiaires
		panel.add(Box.createHorizontalGlue());
		panel.add(Box.createHorizontalGlue());
		panel.add(chordLabel);
		panel.add(Box.createHorizontalGlue());
		panel.add(quitButton);
		panel.add(Box.createHorizontalGlue());

        // Ajout des composants au panel principal
        add(panel, BorderLayout.CENTER);
		add(keyboardPanel, BorderLayout.SOUTH);

		// Rendre le panel principal visible
		setVisible(true);
		
		keyboard.openConnection();   
	}
	

	@Override
	public void update() {
		Chord chord;
		chord = new Chord(keyboard.getActivatedNotes());
		
		if(!chord.toString().equals("null")) { 
			chordLabel.setText(chord.toString());
		} else {
			chordLabel.setText("");
		}
		
	}

}

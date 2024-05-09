package main.java.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.midi.Keyboard;
import main.java.models.ChordQuizz;
import main.java.models.Statistic;
import main.java.structures.Chord;
import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;
import main.java.tool.Observer;

public class ChordQuizzPanel extends SuperPanelWithKeyboard implements Observer {
	
	// ATTRIBUTS
	private ChordQuizz model;

	// Création des composants
	private JPanel northPanel = new JPanel();

	private JPanel chordPanel = new JPanel();
	private JLabel chordLabel = new JLabel("<html>Appuyez sur \"Continuer ?\" pour <br>deviner un accord.</html>");

	private JPanel scoreCenterPanel = new JPanel();
	private JPanel scorePanel = new JPanel();
	private JLabel scoreLabel = new JLabel("Score : 0/0");
	private JLabel generalScoreLabel = new JLabel("Score général : " + Statistic.getGeneralScore("ChordQuizz") + " / " + Statistic.getGeneralAttempts("ChordQuizz"));
	private JButton continueButton = new JButton("Continuer ?");
	private JButton quitButton = new JButton("Menu");
	
	public ChordQuizzPanel(ChordQuizz model) {
		
		super();						// Appel au constructeur de la classe parente
		
		// Paramétrage du panel principal 
		setFocusable(false);			// Ce panel ne prend pas le focus
		setLayout(new BorderLayout()); 	// Ce panel a un BorderLayout
		setOpaque(false);				// Ce panel est transparent
		
		// Initialisation des attributs
		this.model = model;

		Keyboard keyboard = model.getKeyboard();
		keyboardPanel = new KeyboardPanel(keyboard);

		// Ajout des observateurs
		model.addObserver(this);
		keyboard.addObserver(keyboardPanel);
		
		// Paramétrage des composants


		northPanel.setLayout(new GridLayout(1, 2));
		northPanel.setOpaque(false);

		chordPanel.setLayout(new BorderLayout());
		chordPanel.setOpaque(false);

		scoreCenterPanel.setLayout(new BoxLayout(scoreCenterPanel, BoxLayout.Y_AXIS));
		scoreCenterPanel.setOpaque(false); 
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
		scorePanel.setOpaque(false);

		DesignManager.designMediumLabel(chordLabel);
		DesignManager.designSmallLabel(scoreLabel);
		DesignManager.designSmallLabel(generalScoreLabel);
		DesignManager.designMediumButton(continueButton);
		DesignManager.designMediumButton(quitButton);
		
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				continueButton.setEnabled(false);
				quitButton.setEnabled(false);
				Thread modelThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        model.launch();
						continueButton.setEnabled(true);
						quitButton.setEnabled(true);
                    }
                });
                modelThread.start();
				keyboardPanel.requestFocusInWindow();	
			}
		});

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.quit();
				WindowManager.backWelcome();
			}
		});
		
		// Ajout des composants aux panneaux intermédiaires

		scorePanel.add(Box.createHorizontalGlue());
		scorePanel.add(scoreLabel);
		scorePanel.add(Box.createHorizontalGlue()); 
		scorePanel.add(continueButton);
		scorePanel.add(Box.createHorizontalGlue()); 
		scorePanel.add(quitButton);
		scorePanel.add(Box.createHorizontalGlue()); 

		scoreCenterPanel.add(Box.createVerticalGlue());
		scoreCenterPanel.add(generalScoreLabel);
		scoreCenterPanel.add(Box.createVerticalGlue());
		scoreCenterPanel.add(scorePanel);
		scoreCenterPanel.add(Box.createVerticalGlue());

		chordPanel.add(chordLabel, BorderLayout.CENTER);

		northPanel.add(chordPanel);
		northPanel.add(scoreCenterPanel);

		// Ajout des composants au panel principal
        add(northPanel, BorderLayout.CENTER);
		add(keyboardPanel, BorderLayout.SOUTH);

		// Rendre le panel principal visible
		setVisible(true);

		
	}

	@Override
	public void update() {	
		
		// On met à jour les labels
		chordLabel.setText(model.getText());
		scoreLabel.setText("Score : " + model.getScore() + " / " + model.getAttempts());
		generalScoreLabel.setText("Score général : " + Statistic.getGeneralScore("ChordQuizz") + " / " + Statistic.getGeneralAttempts("ChordQuizz"));

		
		Chord chosenChord = model.getChosenChord();
		Chord correctChord = model.getCorrectChord();

		if(chosenChord != null) {
			switch(model.getAnswer()) {
				case 1:
					for(Integer note : chosenChord.getNotes()) {
						keyboardPanel.colorActivatedNote(note, DesignManager.VICTORY_COLOR);
					}
					break;
				case -1:
					for(Integer note : chosenChord.getNotes()) {
						keyboardPanel.colorActivatedNote(note, DesignManager.FAILURE_COLOR);
					}
					break;
				case 0:
					for(Integer note : chosenChord.getNotes()) {
						keyboardPanel.discolorActivatedNote(note);
					}
					break;
			}
		}

		if(correctChord != null) {

			correctChord = Chord.shiftInRange(correctChord, keyboardPanel.getMinTouch(), keyboardPanel.getMaxTouch());

			switch(model.getAnswer()) {
				case -1:
					for(Integer note : correctChord.getNotes()) {
						keyboardPanel.colorPermanentNote(note, DesignManager.CORRECTION_COLOR);
					}
					break;
				case 0:
					for(Integer note : correctChord.getNotes()) {
						keyboardPanel.discolorPermanentNote(note);
					}
					break;
			}
		}
	}
}

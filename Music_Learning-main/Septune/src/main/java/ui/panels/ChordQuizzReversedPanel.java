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
import javax.swing.JTextField;

import main.java.midi.Keyboard;
import main.java.models.ChordQuizzReversed;
import main.java.models.Statistic;
import main.java.structures.Chord;
import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class ChordQuizzReversedPanel extends SuperPanelWithKeyboard {
	
	// ATTRIBUTS
    Chord correctChord;

	// Création des composants
	private JPanel northPanel = new JPanel();

	private JPanel chordPanel = new JPanel();
	private JLabel chordLabel = new JLabel("<html>Appuyez sur \"Continuer ?\" pour <br>deviner un accord.</html>");

	private JPanel scoreCenterPanel = new JPanel();
    private JTextField answerField = new JTextField();
	private JPanel scorePanel = new JPanel();
	private JLabel scoreLabel = new JLabel("Score : 0/0");
    private JButton submitButton = new JButton("Soumettre");
	private JButton continueButton = new JButton("Continuer ?");
	private JLabel generalScoreLabel = new JLabel("Score général : " + Statistic.getGeneralScore("ChordQuizzReversed") + " / " + Statistic.getGeneralAttempts("ChordQuizzReversed"));
	private JButton quitButton = new JButton("Menu");
	
	public ChordQuizzReversedPanel(ChordQuizzReversed model) {
		
		super();						// Appel au constructeur de la classe parente
		
		// Paramétrage du panel principal 
		setFocusable(false);			// Ce panel ne prend pas le focus
		setLayout(new BorderLayout()); 	// Ce panel a un BorderLayout
		setOpaque(false);				// Ce panel est transparent
		
		// Initialisation des attributs
		Keyboard keyboard = model.getKeyboard();
		keyboardPanel = new KeyboardPanel(keyboard);
        correctChord = null;

		// Paramétrage des composants

        submitButton.setEnabled(false);
		answerField.setEnabled(false);

		northPanel.setLayout(new GridLayout(1, 2));
		northPanel.setOpaque(false);

		chordPanel.setLayout(new BorderLayout());
		chordPanel.setOpaque(false);

		scoreCenterPanel.setLayout(new BoxLayout(scoreCenterPanel, BoxLayout.Y_AXIS));
		scoreCenterPanel.setOpaque(false); 
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
		scorePanel.setOpaque(false);

        DesignManager.designTextField(answerField);
		DesignManager.designMediumLabel(chordLabel);
		DesignManager.designSmallLabel(generalScoreLabel);
		DesignManager.designSmallLabel(scoreLabel);
        DesignManager.designMediumButton(submitButton);
		DesignManager.designMediumButton(continueButton);
		DesignManager.designMediumButton(quitButton);
		
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                model.init();
                chordLabel.setText("Devinez cet accord : ");
                continueButton.setEnabled(false);
                submitButton.setEnabled(true);
				answerField.setEnabled(true);

                // Effacer le précédent accord
                if(correctChord != null) {
                    for(Integer note : Chord.shiftInRange(correctChord, keyboardPanel.getMinTouch(), keyboardPanel.getMaxTouch()).getNotes()) {
                        keyboardPanel.discolorPermanentNote(note);
                    }
                }

                
              

                // Récupérer le nouvel accord
                correctChord = model.getCorrectChord();

                // Afficher l'accord sur le keyboardPanel
                for(Integer note : Chord.shiftInRange(correctChord, keyboardPanel.getMinTouch(), keyboardPanel.getMaxTouch()).getNotes()) {
                    keyboardPanel.colorPermanentNote(note, DesignManager.CORRECTION_COLOR);
                }

				keyboardPanel.requestFocusInWindow();	
			}
		});

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean answer = model.submit(answerField.getText());
                answerField.setText("");
                continueButton.setEnabled(true);
                submitButton.setEnabled(false);
				answerField.setEnabled(false);
                if(answer) {
                    chordLabel.setText("Bonne réponse.");
                } else {
                    chordLabel.setText("<html>Mauvaise réponse.<br>La bonne réponse était : " + model.getCorrectChord().getName()+"</html>");
                }

				scoreLabel.setText("Score : " + model.getScore() + "/" + model.getAttempts());
				generalScoreLabel.setText("Score général : " + Statistic.getGeneralScore("ChordQuizzReversed") + " / " + Statistic.getGeneralAttempts("ChordQuizzReversed"));
                
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
        scorePanel.add(answerField);
        scorePanel.add(Box.createHorizontalGlue());
        scorePanel.add(submitButton);
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
}

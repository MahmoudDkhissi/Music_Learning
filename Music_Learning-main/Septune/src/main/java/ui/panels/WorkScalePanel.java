package main.java.ui.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.java.midi.Keyboard;
import main.java.models.Statistic;
import main.java.models.WorkScale;
import main.java.tool.Observer;
import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class WorkScalePanel extends SuperPanelWithKeyboard implements Observer {

    // ATTRIBUTS
	private WorkScale model;
	private Keyboard keyboard;
    

    // Création des composants
	private JLabel textLabel = new JLabel("Score : 0 / 0");
	private JLabel generalScoreLabel = new JLabel("Score général : " + Statistic.getGeneralScore("WorkScale") + " / " + Statistic.getGeneralAttempts("WorkScale"));
	private JPanel controlPanel = new JPanel();
	private JPanel labelPanel = new JPanel();
	private JComboBox<String> scaleComboBox = new JComboBox<>();
	private JButton playButton = new JButton("Lancer");
	private JButton pauseButton = new JButton("Pause");
	private JButton resumeButton = new JButton("Reprendre");
	private JButton stopButton = new JButton("Stop");
	private JButton quitButton = new JButton("Menu");

	public WorkScalePanel(WorkScale model) {
		
		super();						// Appel au constructeur de la classe parente
		
		// Paramétrage du panel principal 
		setFocusable(false);			// Ce panel ne prend pas le focus
		setLayout(new GridLayout(3,1)); 	// Ce panel a un BorderLayout
		setOpaque(false);				// Ce panel est transparent
		
		// Initialisation des attributs
		this.model = model;
		keyboard = model.getKeyboard();
		keyboardPanel = new KeyboardPanel(keyboard);

		// Gamme par défaut
		model.setScale("C major");

		// Ajout des observateurs
		model.addObserver(this);
		keyboard.addObserver(keyboardPanel);
		
		// Paramétrage des composants
        DesignManager.designMediumLabel(textLabel);
		DesignManager.designSmallLabel(generalScoreLabel);
		DesignManager.designMediumButton(playButton);
		DesignManager.designMediumButton(quitButton);
		DesignManager.designMediumButton(pauseButton);
		DesignManager.designMediumButton(resumeButton);
		DesignManager.designMediumButton(stopButton);

		controlPanel.setLayout(new FlowLayout());
		controlPanel.setOpaque(false);

		labelPanel.setLayout(new GridLayout(2,1));
		labelPanel.setOpaque(false);
		

		playButton.setEnabled(true);
		pauseButton.setEnabled(false);
		resumeButton.setEnabled(false);

		//playButton.setIcon(new ImageIcon(getClass().getResource("/main/resources/pictures/play.png")));


		scaleComboBox.addItem("C major");
		scaleComboBox.addItem("C minor");
		scaleComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				model.setScale(scaleComboBox.getSelectedItem().toString());
			}
		});



		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getPlayer().play();
				model.setIsListening(true);
				keyboardRequestFocus();

				playButton.setEnabled(false);
				pauseButton.setEnabled(true);
				resumeButton.setEnabled(false);
				scaleComboBox.setEnabled(false);
			}
		});

		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getPlayer().pause();
				model.setIsListening(false);
				keyboardRequestFocus();

				playButton.setEnabled(false);
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(true);
			}
		});

		resumeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getPlayer().resume();
				model.setIsListening(true);
				keyboardRequestFocus();

				playButton.setEnabled(false);
				pauseButton.setEnabled(true);
				resumeButton.setEnabled(false);
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getPlayer().stop();
				model.setIsListening(false);

				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(false);
				scaleComboBox.setEnabled(true);
			}
		});



		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyboard.closeConnection();
				model.getPlayer().stop();
				model.setIsListening(false);
				WindowManager.backWelcome();
			}
		});

		
		
		

		// Ajout des composants aux panels intermédiaires
		controlPanel.add(scaleComboBox);
		controlPanel.add(playButton);
		controlPanel.add(resumeButton);
		controlPanel.add(pauseButton);
		controlPanel.add(stopButton);
		controlPanel.add(quitButton);

		labelPanel.add(textLabel);
		labelPanel.add(generalScoreLabel);


		// Ajout des composants au panel principal
        add(labelPanel);
		add(controlPanel);
		add(keyboardPanel);

		// Rendre le panel principal visible
		setVisible(true);

	}

	

    @Override
    public void update() {
		keyboardPanel.discolorAllActivatedNotes();

		if(model.isListening()) {
			for(int i = keyboardPanel.getMinTouch(); i <= keyboardPanel.getMaxTouch(); i++) {
				if(model.getScale().containsNote(i)) {
					keyboardPanel.colorActivatedNote(i, Color.GREEN);
				} else {
					keyboardPanel.colorActivatedNote(i, Color.RED);
				}
			}
		}

		textLabel.setText("Score : " + model.getScore() + " / " + model.getAttempts());
		generalScoreLabel.setText("Score général : " + Statistic.getGeneralScore("WorkScale") + " / " + Statistic.getGeneralAttempts("WorkScale"));
		
    }
}

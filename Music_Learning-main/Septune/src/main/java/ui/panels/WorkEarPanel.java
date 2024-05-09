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

import main.java.models.WorkEar;
import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class WorkEarPanel extends SuperPanel {

    private JButton beginButton = new JButton("Continuer ?");
    private JButton playButton = new JButton("Écouter");
    private JButton submitButton = new JButton("Soumettre");
    private JButton quitButton = new JButton("Menu");
    private JTextField answerField = new JTextField();

    private JLabel textLabel = new JLabel("Appuyez sur \"Continuer ?\".");

    private JPanel controlPanel = new JPanel();
    
    public WorkEarPanel(WorkEar model) {
        super();
        setLayout(new GridLayout(1,2));
        
        //Design les elements
        DesignManager.designMediumLabel(textLabel);
        DesignManager.designMediumButton(beginButton);
        DesignManager.designMediumButton(playButton);
        DesignManager.designMediumButton(submitButton);
        DesignManager.designMediumButton(quitButton);
        DesignManager.designTextField(answerField);

        playButton.setEnabled(false);
        answerField.setEnabled(false);
        submitButton.setEnabled(false);
        
        
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setOpaque(false);
        
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(beginButton);
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(playButton);
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(answerField);
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(submitButton);
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(quitButton);
        controlPanel.add(Box.createHorizontalGlue());
       
        beginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                textLabel.setText("Quelle est cette note ?");
				beginButton.setEnabled(false);
                playButton.setEnabled(true);
                answerField.setEnabled(true);
                submitButton.setEnabled(true);
				quitButton.setEnabled(false);
				Thread modelThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        model.launch();
                    }
                });
                modelThread.start();
			}
		});

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.playNote();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setAnswer(answerField.getText());
                model.check();
                if(model.getCorrectAnswer()) {
                    textLabel.setText("Bonne réponse !");
                } else {
                    textLabel.setText("<html>Mauvaise réponse !<br>La bonne réponse était " + model.getName() + ".</html>");
                }

                answerField.setText("");

                beginButton.setEnabled(true);
                playButton.setEnabled(false);
                answerField.setEnabled(false);
                submitButton.setEnabled(false);
                quitButton.setEnabled(true);
            }
        });

        quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowManager.backWelcome();
			}
		});


        add(textLabel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
       
    }

    
    
    
}
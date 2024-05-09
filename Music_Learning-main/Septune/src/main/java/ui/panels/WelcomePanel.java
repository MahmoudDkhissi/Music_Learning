package main.java.ui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.models.Statistic;
import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class WelcomePanel extends SuperPanel {

    // Création des composants
    JPanel panelNorth = new JPanel();
    JPanel panelSouth = new JPanel();
    JLabel labelTitle = new JLabel("Welcome.");
    JButton chordQuizzButton = new JButton("Chord Quizz");
    JButton chordQuizzReversedButton = new JButton("Chord Quizz Reversed");
    JButton freeModeButton = new JButton("Free Mode");
    JButton helpButton = new JButton("Aide");
    JButton workScaleButton = new JButton("Work Scale");
    JButton workEarButton = new JButton("Work Ear");
    JButton courseButton = new JButton("Course");
    JButton exitButton = new JButton("Quitter");

    public WelcomePanel() {

        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DesignManager.designBigButton(chordQuizzButton);
        DesignManager.designBigButton(chordQuizzReversedButton);
        DesignManager.designBigButton(freeModeButton);
        DesignManager.designBigButton(helpButton);
        DesignManager.designBigButton(workScaleButton);
        DesignManager.designBigButton(workEarButton);
        DesignManager.designBigButton(courseButton);
        DesignManager.designBigButton(exitButton);

        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.X_AXIS));
        panelNorth.setOpaque(false);
        panelSouth.setOpaque(false);

        chordQuizzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("ChordQuizzPanel");
            }
        });

        freeModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("FreeModePanel");
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("HowToPlayPanel");
            }
        });

        chordQuizzReversedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("ChordQuizzReversedPanel");
            }
        });

        workScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("WorkScalePanel");
            }
        });

        workEarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("WorkEarPanel");
            }
        });

        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.setPanel("CoursePanel");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistic.save();
                System.exit(0);
            }
        });
		


        //add(labelTitle);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(chordQuizzButton);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(chordQuizzReversedButton);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(freeModeButton);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(helpButton);
        panelNorth.add(Box.createHorizontalGlue());
        

        panelSouth.add(Box.createHorizontalGlue());
        panelSouth.add(workScaleButton);
        panelSouth.add(Box.createHorizontalGlue());
        panelSouth.add(workEarButton);
        panelSouth.add(Box.createHorizontalGlue());
        panelSouth.add(courseButton);
        panelSouth.add(Box.createHorizontalGlue());
        panelSouth.add(exitButton);
        panelSouth.add(Box.createHorizontalGlue());

        add(Box.createVerticalGlue());
        add(panelNorth);
        add(Box.createVerticalGlue());
        add(panelSouth);
        add(Box.createVerticalGlue());

    }
}

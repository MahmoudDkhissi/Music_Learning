package main.java.ui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import main.java.ui.managers.DesignManager;
import main.java.ui.managers.WindowManager;

public class CoursePanel extends SuperPanel {

        private JLabel imageLabel = new JLabel();
        private JTextArea textArea = new JTextArea();
        private JComboBox<String> comboBox = new JComboBox<String>();
        private JPanel northPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        private JPanel controlPanel = new JPanel();
        private JPanel imagePanel = new JPanel();
        private JPanel textAreaPanel = new JPanel();
        private JButton quitButton = new JButton("Menu");

        private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();


        public CoursePanel() {
            super();
            setOpaque(false);

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            // Paramétrage des composants
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            //imageLabel.setPreferredSize(new Dimension((int)(screenWidth * 0.8), 200));

            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imagePanel.setOpaque(false);

            textAreaPanel.setOpaque(false);

            DesignManager.designBigButton(quitButton);
            DesignManager.designComboBox(comboBox);

            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WindowManager.backWelcome();
                }
            });

            controlPanel = new JPanel();
            controlPanel.setOpaque(false);
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

            southPanel = new JPanel();
            southPanel.setOpaque(false);
            southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
            
            northPanel = new JPanel();
            northPanel.setOpaque(false);
            northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

            comboBox.addItem("Généralités");
            comboBox.addItem("Construire un accord majeur");
            comboBox.addItem("Construire un accord mineur");
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    setCourse(comboBox.getSelectedIndex());
                }
            });


            textArea.setLineWrap(true); // Permet le retour à la ligne automatique
            textArea.setWrapStyleWord(true);
            textArea.setFont(DesignManager.TEXTAREA_FONT);
            textArea.setForeground(Color.WHITE);
            textArea.setOpaque(false);
            textArea.setAlignmentX(CENTER_ALIGNMENT);
            textArea.setPreferredSize(new Dimension((int)(screenWidth * 0.8), 200));

            // Ajout des composants intermédiaires
            imagePanel.add(imageLabel);
            textAreaPanel.add(textArea);

            controlPanel.add(Box.createHorizontalGlue());
            controlPanel.add(comboBox);
            controlPanel.add(Box.createHorizontalGlue());
            controlPanel.add(quitButton);
            controlPanel.add(Box.createHorizontalGlue());

            northPanel.add(imagePanel);


            southPanel.add(textAreaPanel);
            southPanel.add(controlPanel);
            

            // Ajout des composants
            add(northPanel);
            add(southPanel);

            setCourse(0);


        }

        public void setCourse(int course) {
            switch(course) {
                case 0:
                    imageLabel.setIcon(new ImageIcon(getClass().getResource("/main/resources/pictures/courses/generalites.jpg")));
                    textArea.setText("\nUn octave est divisé en 12 demi-tons. Un demi-ton est l'écart qui sépare deux notes qui se suivent. La notation internationale des note prend comme référence le La en lui associant la lettre A. Ainsi le Si est représenté par un B et ainsi de suite jusqu'au Sol représenté par un G.");
                    break;
                case 1:
                    imageLabel.setIcon(new ImageIcon(getClass().getResource("/main/resources/pictures/courses/construire_accord_majeur.png")));
                    textArea.setText("\nUn accord majeur est composé de 3 notes, la fondamentale, une tierce majeure et la quinte. La fondamentale est la note qui donne le nom à l'accord. Pour trouver facilement un accord majeur il suffit de partir de la note fondamentale, sauter les trois demi-tons suivants, on trouve alors la tierce majeure, puis sauter les deux demi-tons suivants pour trouver la quinte. ");
                    break;
                case 2:
                    imageLabel.setIcon(new ImageIcon(getClass().getResource("/main/resources/pictures/courses/construire_accord_mineur.png")));
                    textArea.setText("\nUn accord mineur est composé de 3 notes, la fondamentale, une tierce mineure et la quinte. La fondamentale est la note qui donne le nom à l'accord. Pour trouver facilement un accord mineur il suffit de partir de la note fondamentale, sauter les deux demi-tons suivants, on trouve alors la tierce mineure, puis sauter les trois demi-tons suivants pour trouver la quinte. ");
                    break;
            }
        }
    
}

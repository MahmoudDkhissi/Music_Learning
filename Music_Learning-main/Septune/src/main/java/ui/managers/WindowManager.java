package main.java.ui.managers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import main.java.midi.Keyboard;
import main.java.models.ChordQuizz;
import main.java.models.ChordQuizzReversed;
import main.java.models.Statistic;
import main.java.models.WorkScale;
import main.java.models.WorkEar;
import main.java.ui.panels.ChordQuizzPanel;
import main.java.ui.panels.ChordQuizzReversedPanel;
import main.java.ui.panels.CoursePanel;
import main.java.ui.panels.FreeModePanel;
import main.java.ui.panels.HowToPlayPanel;
import main.java.ui.panels.KeyboardPanel;
import main.java.ui.panels.PausePanel;
import main.java.ui.panels.SuperFrame;
import main.java.ui.panels.SuperPanel;
import main.java.ui.panels.WelcomePanel;
import main.java.ui.panels.WorkScalePanel;
import main.java.ui.panels.WorkEarPanel;

/** Cette classe a pour objectif de gérer les changements de panneau.
 * Elle est composée de méthodes statiques qui permettent de changer
 * le panneau affiché, de mettre en pause le panneau actuel, de le
 * reprendre, etc. 
 */
public class WindowManager {
    
    /** Frame principale */
    private static SuperFrame frame;

    /** Thread pour exécuter le modèle */
    //private static Thread modelThread;

    /** Panel mis en pause */
    private static SuperPanel pausedPanel;

    /** Etat de pause */
    private static boolean pause;

    public static boolean getPause() {
        return pause;
    }

    public static void init() {
        frame = new SuperFrame();
        frame.setContentPane(new WelcomePanel());
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Afficher une boîte de dialogue pour confirmer la fermeture
                int option = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment fermer la fenêtre?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Statistic.save();
                    // Fermer la fenêtre si l'utilisateur a confirmé
                    System.exit(0);
                }
            }
        });

        pause = false;

    }

    /** Permet le changement de panneau graphiquement mais gère également
     * le lancement du modèle associé au panneau s'il existe.
     * On notera que le modèle est joué dans un Thread à part pour ne pas
     * bloquer l'interface graphique. 
     * On notera également la gestion du focus pour que le clavier soit
     * toujours actif.
     */
    public static void setPanel(String name) {

        SuperPanel panel = null;
        
        switch(name) {
            case "ChordQuizzPanel" :
                panel = new ChordQuizzPanel(new ChordQuizz());
                break;

            case "ChordQuizzReversedPanel" :
                panel = new ChordQuizzReversedPanel(new ChordQuizzReversed());
                break;

            case "WorkScalePanel" :
                panel = new WorkScalePanel(new WorkScale());
                break;
                
            case "FreeModePanel" :
                panel = new FreeModePanel();
                break;

            case "KeyboardPanel" :
                panel = new KeyboardPanel(new Keyboard());
                break;
            case "WelcomePanel" :
                panel = new WelcomePanel();
                break;
            case "WorkEarPanel" :
                panel = new WorkEarPanel(new WorkEar());
                break;
            case "CoursePanel" :
                panel = new CoursePanel();
                break;
            case "HowToPlayPanel" :
                panel = new HowToPlayPanel();
                break;
        } 
        

        frame.setContentPane(panel);
        frame.revalidate();
        panel.requestFocusInWindow();

        if(panel.hasKeyboardPanel()) {
            panel.getKeyboardPanel().requestFocusInWindow();
        }

        frame.repaint();

    }

    public static void pause() {
        pausedPanel = (SuperPanel) frame.getContentPane();
        PausePanel pausePanel = new PausePanel();

        frame.setContentPane(pausePanel);
        frame.revalidate();
        pausePanel.requestFocusInWindow();
        frame.repaint();

        pause = true;
        
    }

    public static void resume() {
        frame.setContentPane(pausedPanel);
        frame.revalidate();
        pausedPanel.requestFocusInWindow();
        if(pausedPanel.hasKeyboardPanel()) {
            pausedPanel.getKeyboardPanel().requestFocusInWindow();
        }
        frame.repaint();

        pause = false; 
    }

    public static void backWelcome() {
        setPanel("WelcomePanel");
        pause = false;
    }

    public static void exit() {
        System.exit(0);
    }

    
}

package main.java.models;

import java.util.ArrayList;
import java.util.List;

import main.java.midi.Keyboard;
import main.java.structures.Scale;
import main.java.tool.MP3Player;
import main.java.tool.Observable;
import main.java.tool.Observer;

public class WorkScale extends Observable implements Observer {

    private Keyboard keyboard;

    //private String text;
    private List<Integer> previousActivatedNotes;
    private int actualNote;
    private boolean isInScale;
    private int score;
    private int attempts;

    // Gamme à jouer
    private Scale scale;

    // Permet de jouer la gamme en boucle
    private MP3Player player;

    // Indique si on la session est lancée (et donc si on met à jour les données)
    private boolean isListening;

    // Indique si la note jouée est la même que la précédente
    private boolean duplicateNote;

    /**
     * Construit une instance du jeu Travail des gammes.
    */
    public WorkScale() {
        // Initialisation des paramètres
        isListening = false;
        duplicateNote = false;
        score = 0;
        attempts = 0;

        // Création du clavier
        keyboard = new Keyboard();
        // ajout des observateurs au clavier pour pouvoir récupérer les notes jouées
        keyboard.addObserver(this);
        previousActivatedNotes = new ArrayList<Integer>();
        keyboard.openConnection();

        player = new MP3Player();

        // Gamme par défaut pour tester
        setScale("C major");

        
    }

    /**
     * Avoir le playerMp3 qui joue la gamme en boucle.
     * @return le playerMp3 qui joue la gamme en boucle.
     */
    public MP3Player getPlayer() {
        return player;
    }

    /**
     * méthode update de l'interface Observer.
     * Mettre à jour les statistiques.
     * et notifier les observateurs que les données ont changé.
     * @see Observer
     * @see Statistic
     */
    @Override
    public void update() {
        if(isListening) {
            // On récupère la note jouée
            determineActualNote();

            // On vérifie si la note jouée est dans la gamme
            if(scale != null) {
                isInScale = scale.containsNote(actualNote);
            }

            // Si on joue la même note que la précédente, on ne fait rien
            if(!duplicateNote) {

                // Si la note jouée est dans la gamme, on incrémente le score
                if(scale.containsNote(actualNote)) {
                    score++;

                    // Mise à jour du score dans les statistiques
                    Statistic.incrementGeneralScore("WorkScale");
                }
                attempts++;

                // Mise à jour du nombre de manches jouées dans les statistiques
                Statistic.incrementGeneralAttempts("WorkScale");
            }
            
            notifyObservers();
        }
    }

    /**
     * Détermine la note jouée sur le clavier.
     * Verifie si la note jouée est la même que la précédente.
     * et met à jour les notes jouées.
     */
    private void determineActualNote() {

        //Tool.printList(previousActivatedNotes, "Previous");

        List<Integer> actualActivatedNotes = keyboard.getActivatedNotes();

        //Tool.printList(actualActivatedNotes, "Actual  ");

        int previousNote = actualNote;

        for(Integer note : actualActivatedNotes) {
            if(!previousActivatedNotes.contains(note)) {
                    actualNote = note;
                }
                
        }
        

        if(actualNote != previousNote) {
            duplicateNote = false;
        } else {
            duplicateNote = true;
        }

        previousActivatedNotes.clear();
        previousActivatedNotes.addAll(actualActivatedNotes);
    }

    /**
     * Avoir la note jouée sur le clavier.
     * @return la note jouée sur le clavier.
    */
    public Integer getActualNote() {
        return actualNote;
    }

    /**
     * Savoir si la note jouée est dans la gamme.
     * @return true si la note jouée est dans la gamme, false sinon.
    */
    public boolean isInScale() {
        return isInScale;
    }

    /**
     * Mettre à jour le statut du jeu.
     * @param isListening le statut du jeu.
    */
    public void setIsListening(boolean isListening) {
        this.isListening = isListening;
    }

    /**
     * Savoir si le jeu attend une note.
     * Autrement dit, si le jeu est en cours.
     * @return true si le jeu attend une note, false sinon.
    */
    public boolean isListening() {
        return isListening;
    }

    /**
     * Avoir la gamme à jouer.
     * @return la gamme à jouer.
    */
    public Scale getScale() {
        return scale;
    }

    /**
     * Avoir le clavier.
     * @return le clavier.
    */
    public Keyboard getKeyboard() {
        return keyboard;
    }

    /**
     * Avoir le score.
     * @return le score.
    */
    public int getScore() {
        return score;
    }

    /**
     * Avoir le nombre de manches jouées.
     * @return le nombre de manches jouées.
    */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Mettre à jour la gamme à jouer.
     * @param str le chemin de la gamme à jouer.
     * @see Scale
    */
    public void setScale(String str) {
        // appel de la méthode de la classe Scale pour avoir le chemin de la gamme à jouer
        scale = new Scale(str);
        // On met à jour le playerMp3 avec le chemin de la gamme à jouer
        player.setFilePath(scale.getBackTrack());
    }
    
}

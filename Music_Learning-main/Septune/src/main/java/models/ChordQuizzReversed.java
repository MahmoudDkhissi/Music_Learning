package main.java.models;

import main.java.midi.*;
import main.java.structures.Chord;
import main.java.tool.Tool;

import java.util.List;



public class ChordQuizzReversed {
	
	/** keyboard utilisé pour le jeu. */
    private Keyboard keyboard;
    
	/** Score de l'utilisateur. */
    private int score;

	/** Nombre de manches jouées par l'utilisateur. */
    private int attempts;

	/** Accord à deviner. */
    private Chord correctChord;
    
	/** Accord choisi par l'utilisateur. */
	private Chord chosenChord;

    
    /** 
     * Construit une instance du jeu DevinerAccords.
     */
	public ChordQuizzReversed() {
       keyboard = new Keyboard();
       keyboard.openConnection();
	   score = 0;
	   attempts = 0;
	}
	
	/*
	 * Fait jouer l'utilisateur sur  manches.
	 */
	public void init() {
		
		//Chord.displayNotesSet();

		attempts++;
		Statistic.incrementGeneralAttempts("ChordQuizzReversed");
		correctChord = Chord.randomChord();


        // Afficher sur le clavier l'accord à jouer
    }


	/**
	 * Renvoie l'accord joué par l'utilisateur.
	 * @return l'accord joué par l'utilisateur.
	 */
    public boolean submit(String chordName) {

		// Récupérer l'accord joué par l'utilisateur
        boolean answer = false;

		// Contient l'accord joué par l'utilisateur
        chosenChord = new Chord(chordName);

        if(chosenChord != null) {

			// Vérifier si l'accord joué est correct
            answer = (chosenChord.getName().equals(correctChord.getName()));
        }
		
		// Si l'accord joué est correct, incrémenter le score
		if(answer) {
			score++;
			Tool.playSound("resources/audio/sounds/son_de_la_victoire.wav");
			// Incrémenter le score général dans les statistiques de ChordQuizzReversed
			Statistic.incrementGeneralScore("ChordQuizzReversed");
		} else {
			Tool.playSound("resources/audio/sounds/son_de_la_defaite.wav");
		}

		// Retourner la réponse de l'utilisateur
        return answer;
    }


	/**
	 * Permet de quitter la connexion avec le clavier.
	*/
	public void quit() {
		keyboard.closeConnection();
	}

	/* GETTERS AND SETTERS */

	/**
	 * Renvoie le clavier utilisé pour le jeu.
	 * @return le clavier utilisé pour le jeu.
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}


	/**
	 * Renvoie le score de l'utilisateur.
	 * @return le score de l'utilisateur.
	 */
    public int getScore() {
        return score;
    }

	/**
	 * Renvoie le nombre de manches jouées par l'utilisateur.
	 * @return le nombre de manches jouées par l'utilisateur.
	 */
	public int getAttempts() {
		return attempts;
	}

	/**
	 * Renvoie l'accord choisi par l'utilisateur.
	 * @return l'accord choisi par l'utilisateur.
	 */
    public Chord getCorrectChord() {
		// Créer un nouvel accord qui stockera les notes de l'accord correct
        Chord newChord = null;

		// Si l'accord correct n'est pas null
		if(correctChord != null) {

			// Récupérer les notes de l'accord correct dans une liste
			List<Integer> notes = correctChord.getNotes();

			// Créer un nouvel accord à partir de la liste de notes
			newChord = new Chord(notes); 
		}
		// Retourner l'accord correct
		return newChord;
    }

}


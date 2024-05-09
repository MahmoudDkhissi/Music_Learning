package main.java.models;

import main.java.midi.*;
import main.java.structures.Chord;
import main.java.tool.Observable;
import main.java.tool.Tool;

import java.util.List;

public class ChordQuizz extends Observable {
	
	/** Délai en secondes. */
	public static final short TEMPS_CHOIX = 3;
	
	/** keyboard utilisé pour le jeu. */
    private Keyboard keyboard;
    
	/** Score de l'utilisateur. */
    private int score;

	/** Nombre de manches jouées par l'utilisateur. */
    private int attempts;

	// Elements qui seront observés par la vue
	/* Texte à afficher */
    private String text;

	/** Accord à deviner. */
    private Chord chosenChord;
	/** Accord choisi par l'utilisateur. */
	private Chord correctChord;
	/** Réponse de l'utilisateur. */
	private int answer; // 0 = pas de réponse, 1 = bonne réponse, -1 = mauvaise réponse

	/** Permet de savoir si l'utilisateur peut quitter le jeu. */
	private boolean canQuit = false;
    
    /** 
     * Construit une instance du jeu DevinerAccords.
     */
	public ChordQuizz() {
		// Création du clavier
       	keyboard = new Keyboard();

		// Ouverture du clavier
       	keyboard.openConnection();
		// Initialisation des statistiques
	   	score = 0;
	   	attempts = 0;
	}
	
	/*
	 * Fait jouer l'utilisateur sur  manches.
	 */
	public void launch() {
		
		answer = 0;
		notifyObservers();
		//Chord.displayNotesSet();

		// On incrémente le nombre de manches jouées
		attempts++;

		// On met à jour les statistiques générales concernant le nombre de manches jouées
		Statistic.incrementGeneralAttempts("ChordQuizz");

		// On refuse à l'utilisateur de quitter le jeu pendant qu'il joue
		canQuit = false;

		// On choisit un accord au hasard
		correctChord = Chord.randomChord();

		// On affiche l'accord à jouer sur le clavier
		text = "Accord à deviner : " + correctChord.toString()+"\n";

		// On notifie les observateurs pour qu'ils mettent à jour la vue
		notifyObservers();

		// faire jouer l'utilisateur et récupérer l'accord joué
		chosenChord = new Chord(userChordChoice().getNotes());

		// On vérifie si l'accord joué par l'utilisateur est correct
		if(Chord.areEquivalent(correctChord, chosenChord)) {
			// Si c'est le cas, on incrémente le score de l'utilisateur
			text = "Bravo !";
			answer = 1;	
			score++;
			// On met à jour les statistiques générales concernant le score de chordQuizz
			Statistic.incrementGeneralScore("ChordQuizz");
			// On notifie les observateurs pour qu'ils mettent à jour la vue (Ils afficheront le nouveau score)
			notifyObservers();
			// On joue un son de victoire
			Tool.playSound("resources/audio/sounds/son_de_la_victoire.wav");
		} else {
			// Si l'accord joué par l'utilisateur est incorrect, on affiche le bon accord
			text = "Échec ! Le bon accord était : ";
			// Mauvaise réponse
			answer = -1;
			// On notifie les observateurs pour qu'ils mettent à jour la vue (Ils afficheront le bon accord)
			notifyObservers();
			// On joue un son de défaite
			Tool.playSound("resources/audio/sounds/son_de_la_defaite.wav");
		}
	}
	

	/** 
	 * Permet de faire jouer l'utilisateur une fois, actuellement, 
	 * si l'utilisateur maintient TEMPS_CHOIX secondes un accord sans bouger, 
	 * c'est celui ci qui est sélectionné.
	 */
	private Chord userChordChoice() {
		
		//short indicator;

		/* On initialise le temps local à l'heure actuelle */
		long localStartTime;
		
		/* On initialise la variable qui permettra de savoir si l'utilisateur a choisi un accord */
		boolean end = false;

		/* On initialise la variable qui contiendra l'accord choisi par l'utilisateur */
		Chord chord = null;

		// Tant que l'utilisateur n'a pas choisi d'accord
		while(!end) {
			
			// On réinitialise le clavier
			keyboard.setIsUpdated(false);

			// On initialise le temps local à l'heure actuelle
			localStartTime = System.currentTimeMillis();
			//indicator = 0;
			
			// Tant que l'utilisateur n'a pas choisi d'accord et que le temps de choix n'est pas écoulé
			while(!keyboard.getIsUpdated() && System.currentTimeMillis() < localStartTime + TEMPS_CHOIX * 1000) {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				//System.out.println(TEMPS_CHOIX - indicator +" demi-secondes restantes...");
				//indicator++;
			}
			
			if(!keyboard.getIsUpdated()) {
				chord = new Chord(keyboard.getActivatedNotes());
				end = true;
				//System.out.print("Accord retenu : ");
			}

		}

		return chord;
		
	}

	/**
	 * Permet de quitter le jeu.
	 */
	public void quit() {
		keyboard.closeConnection();
	}

	/* GETTERS AND SETTERS */

	public int getAnswer() {
		return answer;
	}

	/**
	 * @return L'accord choisi par l'utilisateur.
	 */
	public Chord getChosenChord() {
		
		Chord newChord = null;

		if(chosenChord != null) {
			List<Integer> notes = chosenChord.getNotes();
			newChord = new Chord(notes); 
		}
		return newChord;
	}

	/**
	 * @return Le keyboard utilisé pour le jeu.
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}
	
	/**
	 * @return Le texte à afficher.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return true si l'utilisateur peut quitter le jeu, false sinon.
	 */
	public boolean getCanQuit() {
		return canQuit;
	}

	/**
	 * @return Le score de l'utilisateur.
	*/
    public int getScore() {
        return score;
    }

	/**
	 * @return Le nombre de tentatives de l'utilisateur.
	*/
	public int getAttempts() {
		return attempts;
	}

	
	/**
	 * @return L'accord correct.
	 */
    public Chord getCorrectChord() {
        Chord newChord = null;

		if(correctChord != null) {
			List<Integer> notes = correctChord.getNotes();
			newChord = new Chord(notes); 
		}
		return newChord;
    }

}


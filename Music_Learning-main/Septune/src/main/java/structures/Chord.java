package main.java.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Classe représentant un accord.
 * Un accord est un ensemble de notes.
 * Hérite de la classe NotesSet qui représente un ensemble de notes.
*/
public class Chord extends NotesSet {
	
	/** Liste des accords. */
	static {
		initChordList();
	}

	/** Nom de l'accord. */
	protected static List<NotesSet> chordList;

	/** 
	 * Construit un accord.
	 * @param name nom de l'accord.
	 * @param notes liste des notes de l'accord.
	 * @param audioFiles liste des fichiers audio de l'accord qui correspondent aux notes.
	 */
	private static void addChordTochordList(String name, List<Integer> notes, List<String> audioFiles) {
		Chord chordToAdd = new Chord(name, notes, audioFiles);
		chordList.add(chordToAdd);
	}

	/** 
	 * Construit un accord.
	 * @param name nom de l'accord.
	 * @param notes liste des notes de l'accord.
	 */
	private static void addChordTochordList(String name, List<Integer> notes) {
		addChordTochordList(name, notes, null);
	}

	/** 
	 * Initialise la liste des accords possibles.
	 */
	private static void initChordList() {

		// Liste des accords
		chordList = new ArrayList<>();
		chordList.clear();

		addChordTochordList("C", Arrays.asList(C0, E0, G0));
		addChordTochordList("Cm", Arrays.asList(C0, Eb0, G0));
		addChordTochordList("Csus2", Arrays.asList(C0, D0, G0));
		addChordTochordList("Csus4", Arrays.asList(C0, F0, G0));
		addChordTochordList("CM7", Arrays.asList(C0, E0, G0, B0));
		addChordTochordList("C7", Arrays.asList(C0, E0, G0, Bb0));
		addChordTochordList("Cm7", Arrays.asList(C0, Eb0, G0, Bb0));

		addChordTochordList("C#", Arrays.asList(Db0, F0, Ab0));
		addChordTochordList("C#m", Arrays.asList(Db0, E0, Ab0));
		addChordTochordList("C#sus2", Arrays.asList(Db0, Eb0, Ab0));
		addChordTochordList("C#sus4", Arrays.asList(Db0, Gb0, Ab0));
		addChordTochordList("C#M7", Arrays.asList(Db0, F0, Ab0, C1));
		addChordTochordList("C#7", Arrays.asList(Db0, F0, Ab0, B0));
		addChordTochordList("C#m7", Arrays.asList(Db0, E0, Ab0, B0));

		addChordTochordList("D", Arrays.asList(D0, Gb0, A0));
		addChordTochordList("Dm", Arrays.asList(D0, F0, A0));
		addChordTochordList("Dsus2", Arrays.asList(D0, E0, A0));
		addChordTochordList("Dsus4", Arrays.asList(D0, G0, A0));
		addChordTochordList("DM7", Arrays.asList(D0, Gb0, A0, Db1));
		addChordTochordList("D7", Arrays.asList(D0, Gb0, A0, C1));
		addChordTochordList("Dm7", Arrays.asList(D0, F0, A0, C1));

		addChordTochordList("D#", Arrays.asList(Eb0, G0, Bb0));
		addChordTochordList("D#m", Arrays.asList(Eb0, Gb0, Bb0));
		addChordTochordList("D#sus2", Arrays.asList(Eb0, F0, Bb0));
		addChordTochordList("D#sus4", Arrays.asList(Eb0, Ab0, Bb0));
		addChordTochordList("D#M7", Arrays.asList(Eb0, G0, Bb0, D1));
		addChordTochordList("D#7", Arrays.asList(Eb0, G0, Bb0, Db1));
		addChordTochordList("D#m7", Arrays.asList(Eb0, Gb0, Bb0, Db1));

		addChordTochordList("E", Arrays.asList(E0, Ab0, B0));
		addChordTochordList("Em", Arrays.asList(E0, G0, B0));
		addChordTochordList("Esus2", Arrays.asList(E0, Gb0, B0));
		addChordTochordList("Esus4", Arrays.asList(E0, A0, B0));
		addChordTochordList("EM7", Arrays.asList(E0, Ab0, B0, Eb1));
		addChordTochordList("E7", Arrays.asList(E0, Ab0, B0, D1));
		addChordTochordList("Em7", Arrays.asList(E0, G0, B0, D1));

		addChordTochordList("F", Arrays.asList(F0, A0, C1));
		addChordTochordList("Fm", Arrays.asList(F0, Ab0, C1));
		addChordTochordList("Fsus2", Arrays.asList(F0, G0, C1));
		addChordTochordList("Fsus4", Arrays.asList(F0, Bb0, C1));
		addChordTochordList("FM7", Arrays.asList(F0, A0, C1, E1));
		addChordTochordList("F7", Arrays.asList(F0, A0, C1, Eb1));
		addChordTochordList("Fm7", Arrays.asList(F0, Ab0, C1, Eb1));

		addChordTochordList("F#", Arrays.asList(Gb0, Bb0, Db1));
		addChordTochordList("F#m", Arrays.asList(Gb0, A0, Db1));
		addChordTochordList("F#sus2", Arrays.asList(Gb0, Ab0, Db1));
		addChordTochordList("F#sus4", Arrays.asList(Gb0, B0, Db1));
		addChordTochordList("F#M7", Arrays.asList(Gb0, Bb0, Db1, F1));
		addChordTochordList("F#7", Arrays.asList(Gb0, Bb0, Db1, E1));
		addChordTochordList("F#m7", Arrays.asList(Gb0, A0, Db1, E1));

		addChordTochordList("G", Arrays.asList(G0, B0, D1));
		addChordTochordList("Gm", Arrays.asList(G0, Bb0, D1));
		addChordTochordList("Gsus2", Arrays.asList(G0, A0, D1));
		addChordTochordList("Gsus4", Arrays.asList(G0, C1, D1));
		addChordTochordList("GM7", Arrays.asList(G0, B0, D1, Gb1));
		addChordTochordList("G7", Arrays.asList(G0, B0, D1, F1));
		addChordTochordList("Gm7", Arrays.asList(G0, Bb0, D1, F1));

		addChordTochordList("G#", Arrays.asList(Ab0, C1, Eb1));
		addChordTochordList("G#m", Arrays.asList(Ab0, B0, Eb1));
		addChordTochordList("G#sus2", Arrays.asList(Ab0, Bb0, Eb1));
		addChordTochordList("G#sus4", Arrays.asList(Ab0, Db1, Eb1));
		addChordTochordList("G#M7", Arrays.asList(Ab0, C1, Eb1, G1));
		addChordTochordList("G#7", Arrays.asList(Ab0, C1, Eb1, Gb1));
		addChordTochordList("G#m7", Arrays.asList(Ab0, B0, Eb1, Gb1));

		addChordTochordList("A", Arrays.asList(A0, Db1, E1));
		addChordTochordList("Am", Arrays.asList(A0, C1, E1));
		addChordTochordList("Asus2", Arrays.asList(A0, B0, E1));
		addChordTochordList("Asus4", Arrays.asList(A0, D1, E1));
		addChordTochordList("AM7", Arrays.asList(A0, Db1, E1, Ab1));
		addChordTochordList("A7", Arrays.asList(A0, Db1, E1, G1));
		addChordTochordList("Am7", Arrays.asList(A0, C1, E1, G1));

		addChordTochordList("A#", Arrays.asList(Bb0, D1, F1));
		addChordTochordList("A#m", Arrays.asList(Bb0, Db1, F1));
		addChordTochordList("A#sus2", Arrays.asList(Bb0, C1, F1));
		addChordTochordList("A#sus4", Arrays.asList(Bb0, Eb1, F1));
		addChordTochordList("A#M7", Arrays.asList(Bb0, D1, F1, A1));
		addChordTochordList("A#7", Arrays.asList(Bb0, D1, F1, Ab1));
		addChordTochordList("A#m7", Arrays.asList(Bb0, Db1, F1, Ab1));

		addChordTochordList("B", Arrays.asList(B0, Eb1, Gb1));
		addChordTochordList("Bm", Arrays.asList(B0, D1, Gb1));
		addChordTochordList("Bsus2", Arrays.asList(B0, Db1, Gb1));
		addChordTochordList("Bsus4", Arrays.asList(B0, E1, Gb1));
		addChordTochordList("BM7", Arrays.asList(B0, Eb1, Gb1, Bb1));
		addChordTochordList("B7", Arrays.asList(B0, Eb1, Gb1, A1));
		addChordTochordList("Bm7", Arrays.asList(B0, D1, Gb1, A1));

	}

	/**
	 *  Constructeur d'accord
	 * @param name : nom de l'accord
	 * @param notes : liste des notes de l'accord
	 * @param audioFiles : liste des fichiers audio de l'accord
	 * */
	private Chord(String name, List<Integer> notes, List<String> audioFiles) {
		super(name, notes, audioFiles, chordList);
	}
	
	/**
	 *  Constructeur d'accord
	 * @param name : nom de l'accord
	 * */
	public Chord(String name) {
		this(name, notesFromName(name, chordList), audioFilesFromName(name, chordList));
	}
	
	/**
	 *  Constructeur d'accord
	 * @param notes : liste des notes de l'accord
	 * */
	public Chord(List<Integer> notes) {
		this(nameFromNotes(notes, chordList), notes, null);
	}
	
	/**
	 * Permet de recuperer un accord aleatoire.
	 * Accords possibles dans la listes des accords.
	 * @return un accord aleatoire
	 * */
	public static Chord randomChord() {
		return new Chord(randomNotes(chordList));
	}

	/** 
	 * Permet de savoir si deux accords sont equivalents
	 * @param c1 : premier accord
	 * @param c2 : deuxieme accord
	 * @return true si les deux accords sont equivalents, false sinon
	*/	
	public static boolean areEquivalent(Chord c1, Chord c2) {
		// On décale les accords pour les mettre dans la même gamme pour pouvoir les comparer
		Chord shiftedC1 = shiftToLeft(c1);
		Chord shiftedC2 = shiftToLeft(c2);
		
		return shiftedC1.getNotes().equals(shiftedC2.getNotes());
	}

	/**
	 *  Translater l'accord entre deux valeurs, utile pour l'affichage 
	 * @param chord : accord à translater
	 * @param min : valeur minimale
	 * @param max : valeur maximale
	 * @return l'accord translater
	*/
	public static Chord shiftInRange(Chord chord, int min, int max) {
		List<Integer> notes = chord.getNotes();
		// On récupère la note minimale
		int minNote = notes.get(0);

		while(!(min < minNote && minNote < max)) {
			for(int i = 0; i < notes.size(); i++) {
				// On décale toutes les notes d'une octave
				notes.set(i, notes.get(i) + 12);
			}
			// On met à jour la note minimale
			minNote = notes.get(0);
		}
		return new Chord(notes);
	}

	/**
	 * Décaler un accord le plus à gauche possible
	 * @param chord : accord à translater
	 * @return l'accord translater
	 * Appelle la méthode shiftToLeft de Note
	 * @see Note#shiftToLeft(List)
	*/
	public static Chord shiftToLeft(Chord chord) {
		return new Chord(shiftToLeft(chord.getNotes()));
	}

	/* 

	public String affichageUtile() {
		String ajout = "";
		List<Integer> normalized = normalize(notes);
		
		int e;
		for(int i = 0; i < normalized.size(); i++) {
			e = normalized.get(i);
			switch(e) {
				case 0:
					ajout += "C";
					break;
				case 1:
					ajout += "Db";
					break;
				case 2:
					ajout += "D";
					break;
				case 3:
					ajout += "Eb";
					break;
				case 4:
					ajout += "E";
					break;
				case 5:
					ajout += "F";
					break;
				case 6:
					ajout += "Gb";
					break;
				case 7:
					ajout += "G";
					break;
				case 8:
					ajout += "Ab";
					break;
				case 9:
					ajout += "A";
					break;
				case 10:
					ajout += "Bb";
					break;
				case 11:
					ajout += "B";
					break;
			}
			
			if(i != normalized.size() - 1) {
				ajout += ", ";
			}
			
		}
		return "Arrays.asList("+ ajout + "));";
	}

	*/
	
}

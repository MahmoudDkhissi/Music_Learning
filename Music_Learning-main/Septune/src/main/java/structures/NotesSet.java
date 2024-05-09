package main.java.structures;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un ensemble de notes.
 * Classe abstraite qui est héritée par les classes Chord et Scale.
 */
public abstract class NotesSet {
	
	public static final int C0 = 0, Db0 = 1, D0 = 2, Eb0 = 3, E0 = 4, F0 = 5, Gb0 = 6, G0 = 7, Ab0 = 8, A0 = 9, Bb0 = 10, B0 = 11;
	public static final int C1 = 12, Db1 = 13, D1 = 14, Eb1 = 15, E1 = 16, F1 = 17, Gb1 = 18, G1 = 19, Ab1 = 20, A1 = 21, Bb1 = 22, B1 = 23;
	
	protected String name;
	
	protected List<Integer> notes;

	protected List<String> audioFiles;
	
	/**
	 * Construit un ensemble de notes.
	 * @param name nom de l'ensemble de notes.
	 * @param notes liste des notes de l'ensemble.
	 * @param audioFiles liste des fichiers audio de l'ensemble qui correspondent aux notes.
	 * @param notesSetList liste des ensembles de notes.
	 * @throws IllegalArgumentException si les arguments sont nuls.
	 */
	protected NotesSet(String name, List<Integer> notes, List<String> audioFiles, List<NotesSet> notesSetList) {
		this.name = name;
		this.notes = notes;

		// Si les fichiers audio ne sont pas donnés en paramètre, on les récupère à partir du nom de l'ensemble de notes
		if(audioFiles == null) {
			audioFiles = audioFilesFromName(name, notesSetList);
		} else {
			this.audioFiles = audioFiles;
		}

		// Si les notes ne sont pas donnés en paramètre, on throw une exception
		if(name == null && notes == null) {
			throw new IllegalArgumentException("Les arguments ne peuvent être nuls simultanément");
		}
	}

	/**
	 * Construit un ensemble de notes à partir de son nom et de la liste des ensembles de notes.
	 * @param name nom de l'ensemble de notes.
	 * @param notes	liste des notes de l'ensemble.
	 * @param notesSetList liste des ensembles de notes.
	 */
	public NotesSet(String name, List<Integer> notes, List<NotesSet> notesSetList) {
		this(name, notes, audioFilesFromName(name, notesSetList), notesSetList);
	}

	/**
	 * Construit un ensemble de notes à partir de son nom et de la liste des ensembles de notes.
	 * @param name nom de l'ensemble de notes.
	 * @param notesSetList liste des ensembles de notes.
	 */
	public NotesSet(String name, List<NotesSet> notesSetList) {
		this(name, notesFromName(name, notesSetList), audioFilesFromName(name, notesSetList), notesSetList);
	}
	
	/**
	 * Construit un ensemble de notes à partir de la liste des notes et de la liste des ensembles de notes.
	 * @param notes liste des notes de l'ensemble.
	 * @param notesSetList liste des ensembles de notes.
	 */
	public NotesSet(List<Integer> notes, List<NotesSet> notesSetList) {
		this(nameFromNotes(notes, notesSetList), notes, null);
	}

	/**
	 * Recupère la liste des notes à partir du nom de l'ensemble de notes.
	 * @param name nom de l'ensemble de notes.
	 * @param notesSetList liste des ensembles de notes.
	 * @return la liste des notes de l'ensemble de notes.
	 */
	public static List<Integer> notesFromName(String name, List<NotesSet> notesSetList){

		List<Integer> notes = null;

		// On parcourt la liste des ensembles de notes
		for(NotesSet notesSet : notesSetList) {
			// Si le nom de l'ensemble de notes correspond au nom donné en paramètre, on récupère la liste des notes
			if(notesSet.getName().equals(name)) {
				notes = new ArrayList<Integer>(notesSet.getNotes());
			}
		}
		return notes;
	}

	/**
	 * Recupère la liste des fichiers audio à partir du nom de l'ensemble de notes.
	 * @param name nom de l'ensemble de notes.
	 * @param notesSetList liste des ensembles de notes.
	 * @return la liste des fichiers audio de l'ensemble de notes.
	 */
	public static List<String> audioFilesFromName(String name, List<NotesSet> notesSetList){

		List<String> audioFiles = null;

		for(NotesSet notesSet : notesSetList) {
			if(notesSet.getName().equals(name)) {
				if(notesSet.getAudioFiles() != null) {
					audioFiles = new ArrayList<String>(notesSet.getAudioFiles());
				}
			}
		}

		return audioFiles;
	}
	
	/**
	 * Recupère les cles de l'ensemble de notes à partir de la liste des notes.
	 * @param notes liste des notes de l'ensemble de notes.
	 * @param notesSetList liste des ensembles de notes.
	 * @return le nom de l'ensemble de notes.
	 */
	public static String nameFromNotes(List<Integer> notes, List<NotesSet> notesSetList){

		String name = null;
		
		List<Integer> shiftedNotes = shiftToLeft(notes);

		for(NotesSet notesSet : notesSetList) {
			if(notesSet.getNotes().equals(shiftedNotes)) {
				name = notesSet.getName();
			}
		}

		return name;
	}

	/**
	 * Accesseur de manière aléatoire à un ensemble de notes.
	 * @param notesSetList liste des ensembles de notes disponibles.
	 * @return un ensemble de notes.
	 */
	protected static List<Integer> randomNotes(List<NotesSet> notesSetList) {
		int randomIndex = (int)(Math.random() * (notesSetList.size()));
		return notesSetList.get(randomIndex).getNotes();
	}
	
	/**
	 * Décale les notes vers la gauche pour que la note la plus grave soit B0.
	 * @param originalList liste des notes.
	 * @return la liste des notes décalées.
	 */
	public static List<Integer> shiftToLeft(List<Integer> originalList) {
		List<Integer> list = new ArrayList<Integer>(originalList);
		if(!list.isEmpty()) {
			while(list.get(0) > B0) {
				for(int i = 0; i < list.size(); i++) {
					list.set(i, list.get(i) - 12);
				}
			}
		}
		return list;
	}
	
	/**
	 * Equivalence entre deux ensembles de notes de manière stricte.
	 * @param o l'ensemble de notes à comparer.
	 * @return true si les deux ensembles de notes sont équivalents, false sinon.
	 */
	@Override 
	public boolean equals(Object o) {
		return notes.equals(((NotesSet)o).notes);
	}

	/**
	 * Convertit un ensemble de notes en chaine de caractères.
	 * @return la chaine de caractères correspondant à l'ensemble de notes.
	 */
	@Override
    public String toString() {
		String toString = "Accord inconnu";
		if(name != null) {
			toString = name;
		}
        return "" + toString;	
    }

	/**
	 * Accesseur de la liste des notes au sein de l'ensemble de notes.
	 * @return la liste des notes.
	 */
	public List<Integer> getNotes() {
		List<Integer> copy = new ArrayList<Integer>();
		for(Integer note : notes) {
			copy.add(note);
		}
		return copy;
	}

	/**
	 * Mutateur de la liste des notes au sein de l'ensemble de notes.
	 * @param notes la liste des notes.
	 * @param notesSetList liste des ensembles de notes.
	 */
	public void setNotes(List<Integer> notes, List<NotesSet> notesSetList) {
		this.notes = notes;
		this.name = nameFromNotes(notes, notesSetList);
		this.audioFiles = audioFilesFromName(name, notesSetList);
	}

	/**
	 * Accesseur de la liste des fichiers audio au sein de l'ensemble de notes.
	 * @return la liste des fichiers audio.
	 */
	public List<String> getAudioFiles() {
		List<String> copy = null;
		if(audioFiles != null) {
			copy = new ArrayList<String>();
			for(String audioFile : audioFiles) {
				copy.add(audioFile);
			}
		}
		return copy;
	}

	/**
	 * Mutateur du nom de l'ensemble de notes.
	*/
	public String getName() {
		return name;
	}
	
}

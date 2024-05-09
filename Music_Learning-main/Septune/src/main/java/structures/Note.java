package main.java.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Cette classe représente une note, notamment par un nom, une clé ainsi qu'une octave.
 * Elle permet également de récupérer les fichiers audio correspondant à la note.
*/
public class Note 
{
    // Liste des notes
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    private static List<Note> noteList;

    private List<String> audioFiles;
    

    private String name;

    private int key;
    private int octave;
    

    /**
     * Construit une note à partir de sa clé.
     * @param key clé de la note.
    */
    public Note(int key) {
        this.key = key;
        this.octave = (key / 12)-1;
        int note = key % 12;
        this.name = NOTE_NAMES[note];
        audioFiles = audioFilesFromName(name);
    }

    /**
     * Construit une note à partir de son nom.
     * @param name nom de la note.
    */
    public Note(String name) {
        this(name, audioFilesFromName(name));
    }

    /**
     * Recupère les fichiers audios correspondant à la note.
     * Peut-être des fichiers audios de piano ou de guitare.
     * @param name nom de la note.
     * @return liste des fichiers audio correspondant à la note.
    */
    private static List<String> audioFilesFromName(String name) {
        // Liste des fichiers audios
        List<String> audioFiles = new ArrayList<>();
        for (Note note : noteList) {
            if (note.getName().equals(name)) {
                // On récupère les fichiers audio de la note
                audioFiles = note.getAudioFiles();
            }
        }
        return audioFiles;
    }

    /**
     * Construit une note à partir de son nom et de ses fichiers audios.
     * @param name nom de la note.
     * @param audioFiles liste des fichiers audio correspondant à la note.
    */
    public Note(String name, List<String> audioFiles) {
        this.name = name;
        this.key = keyFromName(name);
        this.audioFiles = audioFiles;
    }

    /**
     * Méthode statique.
    */
    static {
        initMapNameNotes();
    }

    /**
     * Ajoute une note à la liste des notes à partir de son nom et de ses fichiers audios.
     * @param name nom de la note.
     * @param audioFiles liste des fichiers audio correspondant à la note.
    */
    private static void addToNoteList(String name, List<String> audioFiles) {
        Note noteToAdd = new Note(name, audioFiles);
        noteList.add(noteToAdd);
    }

    /**
     * Initialise la map des notes avec leurs fichiers audios.
     * La clé de la note est calculée à partir de son nom.
     * La valeur de la note est une liste de fichiers audios.
    */
    private static void initMapNameNotes() {
		
		noteList = new ArrayList<>();
		noteList.clear();

        // On ajoute les notes à la liste des notes avec leurs fichiers audios
		addToNoteList("C", Arrays.asList("C_piano.wav", "C_guitar.wav"));
        addToNoteList("C#", Arrays.asList("C#_piano.wav", "C#_guitar.wav"));
        addToNoteList("D", Arrays.asList("D_piano.wav", "D_guitar.wav"));
        addToNoteList("D#", Arrays.asList("D#_piano.wav", "D#_guitar.wav"));
        addToNoteList("E", Arrays.asList("E_piano.wav", "E_guitar.wav"));
        addToNoteList("F", Arrays.asList("F_piano.wav", "F_guitar.wav"));
        addToNoteList("F#", Arrays.asList("F#_piano.wav", "F#_guitar.wav"));
        addToNoteList("G", Arrays.asList("G_piano.wav", "G_guitar.wav"));
        addToNoteList("G#", Arrays.asList("G#_piano.wav", "G#_guitar.wav"));
        addToNoteList("A", Arrays.asList("A_piano.wav", "A_guitar.wav"));
        addToNoteList("A#", Arrays.asList("A#_piano.wav", "A#_guitar.wav"));
        addToNoteList("B", Arrays.asList("B_piano.wav", "B_guitar.wav"));
        
		
        
		
	}

    /**
     * Permets de faire correspondre un nom de note à une clé.
     * @param name nom de la note.
     * @return la clé de la note.
     */
    public int keyFromName(String name) {
        int key = 0;
        while(key < NOTE_NAMES.length && !NOTE_NAMES[key].equals(name)) {
            key++;
        }
        return key;
    }

    /**
     * Donne une note aléatoire.
     * @return la note aléatoire.
    */
    public static Note randomNote() {
        Random random = new Random();
        int randomNumber = random.nextInt(NOTE_NAMES.length);
        return new Note(randomNumber);
    }

    /**
     * Savoir si deux notes sont équivalentes  par rapport à leur clé.
     * @param note note à comparer.
    */
    public boolean equivalent(Note note) {
        return key % 12 == note.key % 12;
    }

    /**
     * Savoir si deux notes sont égales mais de manière stricte.
     * @param obj note à comparer.
    */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Note && this.key == ((Note) obj).key;
    }

    /**
     * transforme une note en chaine de caractère.
     * @return la clé de la note.
    */
    @Override
    public String toString() {
        return "" + this.name + this.octave;
    }

    /**
     * Getter de la clé de la note.
     * @return le fichier audio de la note.
    */
    public List<String> getAudioFiles() {
        return audioFiles;
    }

    /**
     * Getter du nom de la note.
     * @return le nom de la note.
    */
    public String getName() {
        return name;
    }
    
}
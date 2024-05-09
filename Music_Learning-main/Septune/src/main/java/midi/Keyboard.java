package main.java.midi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import main.java.structures.Note;
import main.java.tool.Observable;

/**
 * Cette classe modélise un clavier en s'intéressant à l'état actif ou inactif de ses notes.
 * La première note correspond à la note C-1 (clé : 0). 
 * La dernière note correspond à la note C9 (clé : 120).
 * Ces valeurs sont calculés expérimentalement à l'aide de deux claviers midi.
 * On a donc un total de 120 notes.
 * L'attribut notes_actives vaut 1 à la case i si la i ème note est active.
 * L'état des notes est modifié par RecepteurMidi qui récupère les notes en direct depuis
 * un clavier midi externe.
 */
public class Keyboard extends Observable {

	private boolean isUpdated;
    
    private MidiConnection connection;
    
    /** Ce tableau contient les notes du clavier. */
    protected List<Integer> activatedNotes; 
    
    /** 
     * Construit un Clavier, et établit les connections.
     */
    public Keyboard()
    {	
        
    	connection = new MidiConnection(this);
    	connection.start();
        
    	activatedNotes = new ArrayList<Integer>();
      

    }
    
    public void openConnection() {
    	connection.openConnection();
    }
    
    public void closeConnection() {
    	connection.closeConnection();
    }
    
    
    
    public void update(int key, boolean active) {
  
    	isUpdated = true;
    	
    	if(active) {
    		activatedNotes.add(key);
    	} else {
    		activatedNotes.remove(Integer.valueOf(key));
    	}
    	
    	Collections.sort(activatedNotes);
    	
    	notifyObservers();
    	//displayKeyboard();

    }
    
    public void addNote(int key) {
        setIsUpdated(true);
    	if(!activatedNotes.contains(key)) {
    		activatedNotes.add(key);
    	}

        Collections.sort(activatedNotes);

    	notifyObservers();
        //displayKeyboard();
    }
    
    public void removeNote(int key) {
        setIsUpdated(true);
    	activatedNotes.remove(Integer.valueOf(key));

        Collections.sort(activatedNotes);

    	notifyObservers();
    	//displayKeyboard();
    }
    
    public void setIsUpdated(boolean isUpdated) {
    	this.isUpdated = isUpdated;
    }
    
    public boolean getIsUpdated() {
    	return isUpdated;
    }
    
    public List<Integer> getActivatedNotes() {
    	return activatedNotes;
    }

    public boolean containsNote(int key) {
        return activatedNotes.contains(key);
    }


    /** Sert au programmeur à observer l'état du clavier */
    
    public void displayKeyboard()
    {
    	System.out.print("[ ");
        for(Integer n : activatedNotes) 
        {
        	//int octave = (n / 12)-1;
            //String name = Note.NOTE_NAMES[n % 12];
            System.out.print(n + " ");
        	//-System.out.print(name + octave + " ");
        }
        System.out.println("]");
    }
    
}

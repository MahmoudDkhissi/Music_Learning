package main.java.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scale extends NotesSet {
	
	static {
		initScaleList();
	}

	private static List<NotesSet> scaleList;

	private static void addScaleToscaleList(String name, List<Integer> notes, List<String> audioFiles) {
		Scale scaleToAdd = new Scale(name, notes, audioFiles);
		scaleList.add(scaleToAdd);
	}

	private static void initScaleList() {
		
		scaleList = new ArrayList<>();
		scaleList.clear();

		addScaleToscaleList("C major",  Arrays.asList(C0, D0, E0, F0, G0, A0, B0), Arrays.asList("/main/resources/audio/backtracks/C_major.mp3"));
		addScaleToscaleList("C minor",  Arrays.asList(C0, D0, Eb0, F0, G0, Ab0, Bb0), Arrays.asList("/main/resources/audio/backtracks/C_minor.mp3"));
		
	}

	private Scale(String name, List<Integer> notes, List<String> audioFiles) {
		super(name, notes, audioFiles, scaleList);
	}
	
	public Scale(String name) {
		this(name, notesFromName(name, scaleList), audioFilesFromName(name, scaleList));
	}
	
	public Scale(List<Integer> notes) {
		this(nameFromNotes(notes, scaleList), notes, null);
	}
	
	public static Scale randomScale() {
		return new Scale(randomNotes(scaleList));
	}
	
	public boolean containsNote(int key) {
		return notes.contains(key % 12);
	}
	
	public String getBackTrack(int index) {
		return audioFiles.get(index);
	}

	public String getBackTrack() {
		return getBackTrack(0);
	}
	
}

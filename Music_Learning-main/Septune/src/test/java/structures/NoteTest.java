package test.java.structures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.structures.Note;

/**
 * Classe permettant de tester la classe Note.
 */
public class NoteTest {

    private Note aNote, C0, C1, C2;

    @Before
    public void setUp() {

        aNote = new Note(66);

        C0 = new Note(0);
        C1 = new Note(12);
        C2 = new Note(24);
    }

    @Test
    public void keyFromNameTest() {
        assertEquals(0, aNote.keyFromName("C"));
        assertEquals(1, aNote.keyFromName("C#"));
        
    }

    @Test 
    public void equivalentTest() {
        assert(C0.equivalent(C1));
        assert(C1.equivalent(C2));
    }
    
}

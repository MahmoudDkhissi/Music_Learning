package test.java.structures;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import main.java.structures.Chord;

/**
 * Classe permettant de tester la classe Chord.
 */
public class ChordTest {

	Chord chord_C_0, chord_C_1, chord_C_1_false, chord_C_2, chord_B_0, chord_Gsus2_0, chord_Gsus2_4;

	@Before
	public void setUp() {
		chord_C_0 = new Chord(Arrays.asList(0, 4, 7));
		chord_C_1 = new Chord(Arrays.asList(12, 16, 19));
		chord_C_1_false = new Chord(Arrays.asList(12, 16, 20));
		chord_C_2 = new Chord(Arrays.asList(24, 28, 31));
		chord_B_0 = new Chord(Arrays.asList(11, 14, 18));
		chord_Gsus2_0 = new Chord(Arrays.asList(7, 9, 14));
		chord_Gsus2_4 = new Chord(Arrays.asList(55, 57, 62));
	}

	@Test 
	public void testShiftToLeft() {

		Chord shiftedChord;

		shiftedChord = Chord.shiftToLeft(chord_C_1);
		assert(shiftedChord.equals(chord_C_0));

		shiftedChord = Chord.shiftToLeft(chord_C_1_false);
		assert(!shiftedChord.equals(chord_C_0));

		shiftedChord = Chord.shiftToLeft(chord_B_0);
		assert(shiftedChord.equals(chord_B_0));
	}

	@Test
	public void testNameFromNotes() {
	
		assertEquals(chord_C_0.getName(), "C");
		assertEquals(chord_C_1.getName(), "C");
		assert(chord_C_1_false.getName() == null);
		assertEquals(chord_C_2.getName(), "C");

		assertEquals(chord_B_0.getName(), "Bm");
	}

	@Test  
	public void testShiftInRange() {

		Chord shiftedChord;

		shiftedChord = Chord.shiftInRange(chord_C_0, 10, 90);
		assertEquals(shiftedChord, chord_C_1);

		shiftedChord = Chord.shiftInRange(chord_C_0, 21, 38);
		assertEquals(shiftedChord, chord_C_2);

		shiftedChord = Chord.shiftInRange(chord_Gsus2_0, 48, 84);
		assertEquals(shiftedChord, chord_Gsus2_4);

	}
	

}

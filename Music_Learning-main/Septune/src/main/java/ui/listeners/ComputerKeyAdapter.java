package main.java.ui.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.java.midi.Keyboard;

/**
 * Classe permettant de gérer les évènements clavier.
 * Permet de jouer des notes sur le clavier.
*/
public class ComputerKeyAdapter extends KeyAdapter {
	
	// Modèle du clavier
	Keyboard model;
	
	/**
	 * Constructeur de ComputerKeyAdapter.
	 * @param model le modèle du clavier.
	 */
	public ComputerKeyAdapter(Keyboard model) {
		this.model = model;
	}
	
	/**
	 * Méthode appelée lorsqu'une touche est relâchée.
	 * Elle permet de suppimer la note de la liste des notes jouées.
	 * @param e l'évènement clavier.
	*/
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
        	model.removeNote(48);
        	break;
        case KeyEvent.VK_2:
        	model.removeNote(49);
        	break;
        case KeyEvent.VK_Z:
        	model.removeNote(50);
        	break;
        case KeyEvent.VK_3:
        	model.removeNote(51);
        	break;
        case KeyEvent.VK_E:
        	model.removeNote(52);
        	break;
        case KeyEvent.VK_R:
        	model.removeNote(53);
        	break;
        case KeyEvent.VK_5:
        	model.removeNote(54);
        	break;
        case KeyEvent.VK_T:
        	model.removeNote(55);
        	break;
        case KeyEvent.VK_6:
        	model.removeNote(56);
        	break;
        case KeyEvent.VK_Y:
        	model.removeNote(57);
        	break;
        case KeyEvent.VK_7:
        	model.removeNote(58);
        	break;
        case KeyEvent.VK_U:
        	model.removeNote(59);
        	break;
        case KeyEvent.VK_I:
        	model.removeNote(60);
        	break;
        case KeyEvent.VK_9:
        	model.removeNote(61);
        	break;
        case KeyEvent.VK_O:
        	model.removeNote(62);	
        	break;
        case KeyEvent.VK_0:
        	model.removeNote(63);
        	break;
        case KeyEvent.VK_P:
        	model.removeNote(64);
        	break;
        case KeyEvent.VK_W:
        	model.removeNote(65);
        	break;
        case KeyEvent.VK_S:
        	model.removeNote(66);
        	break;
        case KeyEvent.VK_X:
        	model.removeNote(67);
        	break;
        case KeyEvent.VK_D:
        	model.removeNote(68);
        	break;
        case KeyEvent.VK_C:
        	model.removeNote(69);
        	break;
        case KeyEvent.VK_F:
        	model.removeNote(70);
        	break;
        case KeyEvent.VK_V:
        	model.removeNote(71);
        	break;
        case KeyEvent.VK_B:
        	model.removeNote(72);
        	break;
        case KeyEvent.VK_H:
        	model.removeNote(73);
        	break;
        case KeyEvent.VK_N:
        	model.removeNote(74);
        	break;
        case KeyEvent.VK_J:
        	model.removeNote(75);
        	break;
        case KeyEvent.VK_COMMA:
        	model.removeNote(76);
        	break;
        case KeyEvent.VK_SEMICOLON:
        	model.removeNote(77);
        	break;
        case KeyEvent.VK_L:
        	model.removeNote(78);
        	break;
        case KeyEvent.VK_COLON:
        	model.removeNote(79);	
        	break;
        case KeyEvent.VK_M:
        	model.removeNote(80);
        	break;
        case KeyEvent.VK_EXCLAMATION_MARK:
        	model.removeNote(81);
        	break;
        default:
		}
	}
	
	/**
	 * Méthode appelée lorsqu'une touche est pressée.
	 * Elle permet d'ajouter jouer une note.
	 * @param e l'évènement clavier.
	*/
	public void keyPressed (KeyEvent e) {
	    switch(e.getKeyCode()) {
	        case KeyEvent.VK_A:
				actionKeyPressed(48);
	        	break;
	        case KeyEvent.VK_2:
				actionKeyPressed(49);
	        	break;
	        case KeyEvent.VK_Z:
	        	actionKeyPressed(50);
	        	break;
	        case KeyEvent.VK_3:
				actionKeyPressed(51);
	        	break;
	        case KeyEvent.VK_E:
	        	actionKeyPressed(52);
	        	break;
	        case KeyEvent.VK_R:
				actionKeyPressed(53);
	        	break;
	        case KeyEvent.VK_5:
				actionKeyPressed(54);
	        	break;
	        case KeyEvent.VK_T:
				actionKeyPressed(55);
	        	break;
	        case KeyEvent.VK_6:
				actionKeyPressed(56);
	        	break;
	        case KeyEvent.VK_Y:
				actionKeyPressed(57);
	        	break;
	        case KeyEvent.VK_7:
				actionKeyPressed(58);
	        	break;
	        case KeyEvent.VK_U:
				actionKeyPressed(59);
	        	break;
	        case KeyEvent.VK_I:
				actionKeyPressed(60);
	        	break;
	        case KeyEvent.VK_9:
				actionKeyPressed(61);
	        	break;
	        case KeyEvent.VK_O:
				actionKeyPressed(62);
	        	break;
	        case KeyEvent.VK_0:
				actionKeyPressed(63);
	        	break;
	        case KeyEvent.VK_P:
				actionKeyPressed(64);
	        	break;
	        case KeyEvent.VK_W:
				actionKeyPressed(65);
	        	break;
	        case KeyEvent.VK_S:
				actionKeyPressed(66);
	        	break;
	        case KeyEvent.VK_X:
				actionKeyPressed(67);
	        	break;
	        case KeyEvent.VK_D:
				actionKeyPressed(68);
	        	break;
	        case KeyEvent.VK_C:
				actionKeyPressed(69);
	        	break;
	        case KeyEvent.VK_F:
				actionKeyPressed(70);
	        	break;
	        case KeyEvent.VK_V:
				actionKeyPressed(71);
	        	break;
	        case KeyEvent.VK_B:
				actionKeyPressed(72);
	        	break;
	        case KeyEvent.VK_H:
				actionKeyPressed(73);
	        	break;
	        case KeyEvent.VK_N:
				actionKeyPressed(74);
	        	break;
	        case KeyEvent.VK_J:
				actionKeyPressed(75);
	        	break;
	        case KeyEvent.VK_COMMA:
				actionKeyPressed(76);
	        	break;
	        case KeyEvent.VK_SEMICOLON:
				actionKeyPressed(77);
	        	break;
	        case KeyEvent.VK_L:
				actionKeyPressed(78);
	        	break;
	        case KeyEvent.VK_COLON:
				actionKeyPressed(79);	
	        	break;
	        case KeyEvent.VK_M:
				actionKeyPressed(80);
	        	break;
	        case KeyEvent.VK_EXCLAMATION_MARK:
	        	actionKeyPressed(81);
	        	break;
	        default:
	    }

	}

	/**
	 * Permets d'ajouter une note au model à partir de sa clé.
	 * Elle permet d'ajouter jouer une note si elle n'est pas déjà jouée.
	 * @param key la clé de la note.
	*/
	private void actionKeyPressed(int key) {
		// Si la note n'est pas déjà jouée, on l'ajoute au model.
		if (!model.containsNote(key)) {
			// On ajoute la note au model.
			model.addNote(key);
		}
	}

}

package main.java.ui.panels;
import main.java.midi.Keyboard;
import main.java.ui.listeners.ComputerKeyAdapter;
import main.java.ui.managers.DesignManager;
import main.java.tool.Observer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class KeyboardPanel extends SuperPanel implements Observer{
	
	/* JPanel pour faire deux couches pour créer le clavier */
	JPanel whiteTouchLayer;
	JPanel blackTouchLayer;
	
	/* Modèle observé */
	private Keyboard model;
	
	/* Touche minimale et maximale affichée */
	private int minTouch;
	
	public int getMinTouch() {
		return minTouch;
	}

	private int maxTouch;
	
	public int getMaxTouch() {
		return maxTouch;
	}

	/* Nombre de touches affichées pour chaque couleur */
	private int nbWhiteTouches;
	//private int nbBlackTouches;
	
	/* Tableau de touches */
	private Touch[] touches;
	
	/* Mensurations de l'écran */
	private int screenHeight;
	private int screenWidth;
	
	/** Différence entre la largeur de l'écran et la largeur du clavier */
	private int gap;
	
	/* Mensurations des touches */
	private int whiteTouchHeight;
	private int whiteTouchWidth;
	private int blackTouchHeight;
	private int blackTouchWidth;
	private int unit;
	
	/* Couleur d'une note active */
	Color activatedColor;
	
	public KeyboardPanel(Keyboard model) {
		
		
		/* Définition du panneau principal */
		super();
		setOpaque(false);
		setLayout(new OverlayLayout(this));	// OverlayLayout permet de superposer les composants
		
		/* Pour pouvoir jouer avec le clavier de l'ordinateur */
		setFocusable(true);
		addKeyListener(new ComputerKeyAdapter(model));
		
		/* Définition des attributs */
		this.model = model;
		minTouch = 48;
		maxTouch = 84;
		touches = new Touch[120];
		activatedColor = new Color(DesignManager.MAIN_COLOR.getRGB());
		hasBackground = false;
		
		/* Initialisation du tableau de touches */
		boolean color;
		for(int i = 0; i < 120; i++) {
			color = (i % 12 == 0) || (i % 12 == 2) || (i % 12 == 4) || (i % 12 == 5) || (i % 12 == 7) || (i % 12 == 9) || (i % 12 == 11) || (i % 12 == 0);	
			touches[i] = new Touch(color);
			touches[i].setOpaque(true);
		}
		
		/* Création des deux panneaux contenant respectivement les touches blanches et noires */
		whiteTouchLayer = new JPanel(null);
		whiteTouchLayer.setOpaque(false);
		
		blackTouchLayer = new JPanel(null);
		blackTouchLayer.setOpaque(false);
		
		/* Appel aux méthodes de mise à jour */
		updateTouchesMeasurements();
		
		/* Affichage du clavier */
		
		// Création du clavier
		for(int i = minTouch; i <= maxTouch; i++) {
			if(touches[i].isNatural()) { // Si la touche est blanche
				whiteTouchLayer.add(touches[i]);
			} else { // Si la touche est noire
				blackTouchLayer.add(touches[i]);
			}
		}
		
		updateKeyboard();
		
		setPreferredSize(new Dimension(screenWidth, screenHeight / 3));
		
		/* Ajout des deux panneaux au panneau principal */
		add(blackTouchLayer);
		add(whiteTouchLayer);
		update();		
	}
	
	private void updateKeyboard() {
		updateTouchesMeasurements();
		// Initialisation
		int xWhite = gap / 2;
		int xBlack = initXBlack();
				
		// Affichage de chaque touche
		for(int i = minTouch; i <= maxTouch; i++) {
			if(touches[i].isNatural()) { // Si la touche est blanche
				touches[i].setBounds(xWhite , 0, whiteTouchWidth, whiteTouchHeight);
				touches[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				xWhite += whiteTouchWidth;
			} else { // Si la touche est noire
				xBlack += incrementValue(i) * unit;
				touches[i].setBounds(xBlack, 0, blackTouchWidth, blackTouchHeight);
				touches[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//updateKeyboard();
	}
	
	private void updateTouchesMeasurements() {
		updateNumberTouchesDisplayed();
	
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		screenHeight = (int)tailleEcran.getHeight(); 
		screenWidth = (int)tailleEcran.getWidth();
		
		whiteTouchHeight = (int) (screenHeight / 3);
		whiteTouchWidth =(int)(screenWidth/nbWhiteTouches);
		
		while(whiteTouchWidth % 3 != 0) {
			whiteTouchWidth--;
		}
		
		
		unit = whiteTouchWidth / 3;
		blackTouchWidth = 2 * unit;
		blackTouchHeight = (int) (whiteTouchHeight * 0.6);
		gap = screenWidth - whiteTouchWidth * nbWhiteTouches;
		
	}
	
	/* En fonction du nombre de touches affichés, met à jour les variables nbWhiteTouches nbBlackTouches */
	private void updateNumberTouchesDisplayed() {
		nbWhiteTouches = 0;
		//nbBlackTouches = 0;
		for(int i = minTouch; i <= maxTouch; i++) {
			if(touches[i].isNatural()) {
				nbWhiteTouches++;
			} else {
				//nbBlackTouches++;
			}
		}
	}
	
	/* Change la couleur des touches activées */
	@Override
	public void update() {
		
		int i;
		
		for(i = minTouch; i <= maxTouch; i++) {
				touches[i].setActivated(false);
		}
		
		//model.displayKeyboard();
		
		
		for(Integer e : model.getActivatedNotes()) {
			touches[e].setActivated(true);
		}
		
		
		i = minTouch;
		
		while(i <= maxTouch) {
			if(touches[i].getPermanentColor() != null) {
				touches[i].setBackground(touches[i].getPermanentColor());
			}	else {
				if(touches[i].getActivated()) {
					if(touches[i].getActivatedColor() != null) {
						touches[i].setBackground(touches[i].getActivatedColor());
					} else {
						touches[i].setBackground(activatedColor);
					}
				} else if (touches[i].isNatural()) {
					touches[i].setBackground(Color.WHITE);
				} else {
					touches[i].setBackground(Color.BLACK);
				}	
			}

			i++;
		}
		// On appelle cette méthode pour forcer à refaire l'interface
		repaint();
			
	}
	
	
	
	private int incrementValue(int i) {
		
		int iMod12 = i % 12;
		int incrementValue = 0;
		switch(iMod12) {
			case 1:
				//On avance de 3 
				incrementValue = 6 ;
				break;
			case 3:
				//On avance de 2 pour passer la touche noire puis de 2 pour l'écart
				incrementValue = 3;
				break;
			case 6:
				//On avance de 2 pour passer la touche noire puis de 2 pour l'écart
				incrementValue= 6;
				break;
			case 8:
				//On avance de 2 pour passer la touche noire puis de 2 pour l'écart
				incrementValue = 3;
				break;
			case 10:
				//On avance de 2 pour passer la touche noire puis de 2 pour l'écart
				incrementValue = 3;
				break;	
			default :
				incrementValue = 0;
		}
		
		return incrementValue;
	}

	private int initXBlack() {
		int minMod12 = minTouch % 12;
		int result = 0;
		
		switch(minMod12) {
			case 0:
			case 5:
				result = (gap / 2) - 4 * unit;
				break;
			case 2:
			case 4:
			case 7:
			case 9:
			case 11:
				result = (gap / 2) - 1 * unit;
				break;
		}
		
		return result;
	}

	public void colorPermanentNote(int index, Color color) {
		touches[index].setPermanentColor(color);
		update();
	}

	public void discolorPermanentNote(int index) {
		touches[index].setPermanentColor(null);
		update();
	}

	public void colorActivatedNote(int index, Color color) {
		touches[index].setActivatedColor(color);
		update();
	}

	public void discolorActivatedNote(int index) {
		touches[index].setActivatedColor(null);
		update();
	}

	public void discolorAllActivatedNotes() {
		for(int i = minTouch; i <= maxTouch; i++) {
			touches[i].setActivatedColor(null);
		}
	}

}

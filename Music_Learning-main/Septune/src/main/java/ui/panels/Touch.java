package main.java.ui.panels;

import java.awt.Color;

import javax.swing.JLabel;

public class Touch extends JLabel {
	
	/* nature de la touche (true => blanche ou false => noire) */
	private boolean isNatural;
	
	/* etat de la touche */
	private boolean activated;

	/* coloration de la touche lorsqu'elle est activée */
	private Color activatedColor;

	/* coloration supplémentaire et permanente de la touche */
	private Color permanentColor;

	public Touch(boolean isNatural) {
		super();
		this.isNatural = isNatural;
		activated = false;
		activatedColor = null;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public boolean getActivated() {
		return activated;
	}
	
	public boolean isNatural() {
		return isNatural;
	}

	public Color getActivatedColor() {
		return activatedColor;
	}

	public void setActivatedColor(Color activatedColor) {
		this.activatedColor = activatedColor;
	}

	public Color getPermanentColor() {
		return permanentColor;
	}

	public void setPermanentColor(Color permanentColor) {
		this.permanentColor = permanentColor;
	}
	
}

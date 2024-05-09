package main.java.ui.panels;

/** Cette classe hérite de SuperPanel et a pour caractéristique
 * d'avoir un KeyboardPanel. Les méthodes liées à ce dernier sont
 * donc rédéfinies. Tout panel héritant de SuperPanelWithKeyboard
 * possédera un KeyboardPanel. (Par exemple ChordQuizzPanel ou FreeModePanel).
*/
public class SuperPanelWithKeyboard extends SuperPanel {

    protected KeyboardPanel keyboardPanel;

    public SuperPanelWithKeyboard() {
        super();
    }

    @Override
	public boolean hasKeyboardPanel() {
		return true;
	}
	
	@Override
	public KeyboardPanel getKeyboardPanel() {
		return keyboardPanel;
	}

	public void keyboardRequestFocus() {
        revalidate();
        keyboardPanel.requestFocusInWindow();
        repaint();
	}
}

package main.java.ui.managers;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Classe permettant de gérer le design de l'application.
 * Permet de définir les couleurs, les polices, les tailles des composants.
 * Permet de définir les boutons.
 * Permet de définir les labels.
 * Permet de définir les champs de texte.
 * Permet de définir les combobox.
 * Permet de définir les textarea.
 * Permet de définir les panels.
*/
public class DesignManager {

    // Fonts

    public static final Font LABEL_MEDIUM_FONT = new Font("Montserrat", Font.PLAIN, 45);

    public static final Font LABEL_SMALL_FONT = new Font("Montserrat", Font.PLAIN, 30);

    public static final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 25);

    public static final Font TEXTAREA_FONT = new Font("Montserrat", Font.BOLD, 20);


    // Colors
    public static final Color MAIN_COLOR = new Color(0xff944d);

    public static final Color VICTORY_COLOR = Color.GREEN;

    public static final Color FAILURE_COLOR = Color.RED;

    public static final Color CORRECTION_COLOR = new Color(0x0080ff);


    /**
     * Classe permettant de définir le design des labels.
     * @param label le label à modifier.
     */
    public static void designMediumLabel(JLabel label) {
        label.setFont(DesignManager.LABEL_MEDIUM_FONT);
        label.setForeground(java.awt.Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Classe permettant de définir le design des labels.
     * @param label le label à modifier.
     */
    public static void designSmallLabel(JLabel label) {
        label.setFont(DesignManager.LABEL_SMALL_FONT);
        label.setForeground(java.awt.Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Classe permettant de définir le design des boutons.
     * @param button le bouton à modifier.
     */
    public static void designBigButton(JButton button) {
        button.setPreferredSize(new Dimension(260, 50));
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(MAIN_COLOR.getRGB()));
        button.setForeground(Color.white);
        // customize the button with your own look
        button.setUI(new OurButton());
    }

    /**
     * Classe permettant de définir le design des boutons.
     * @param button le bouton à modifier.
     */
    public static void designMediumButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(MAIN_COLOR.getRGB()));
        button.setForeground(Color.white);
        // customize the button with your own look
        button.setUI(new OurButton());
    }

    /**
     * Classe permettant de définir le design des boutons.
     * @param button le bouton à modifier.
     */
    public static void designImageButton(JButton button) {
        //button.setPreferredSize(new Dimension(150, 30));
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(MAIN_COLOR.getRGB()));
        button.setForeground(Color.white);
        // customize the button with your own look
        button.setUI(new OurImageButton());
    }

    /**
     * Classe permettant de définir le design des champs de texte.
     * @param textField le champ de texte à modifier.
     */
    public static void designTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(100, 50));
        textField.setMaximumSize(new Dimension(100, 50));
    }

    /**
     * Classe permettant de définir le design des combobox.
     * @param comboBox la combobox à modifier.
     */
    public static <T> void designComboBox(JComboBox<T> comboBox) {
        comboBox.setPreferredSize(new Dimension(260,50));
        comboBox.setMaximumSize(new Dimension(150,50));
    }

    /**
     * Classe permettant de définir le design des Boutons.
     * hérite de BasicButtonUI pour pouvoir modifier le design des boutons.
     * Premier design de bouton.
     * @see BasicButtonUI
     */
    static class OurButton extends BasicButtonUI {

        /**
         * Override de la méthode installUI pour modifier le design des boutons.
         * @param c le composant à modifier.
         */
        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
            button.setOpaque(false);
            
        }
        
        /**
         * Override de la méthode paint pour modifier le design des boutons.
         * @param g le composant à modifier.
         * @param c le composant à modifier.
         */
        @Override
        public void paint (Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }
    
        /**
         * Méthode permettant de modifier le design des boutons.
         * @param g le composant à modifier.
         * @param c le composant à modifier.
         * @param yOffset le décalage du composant.
         */
        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
        }
    
    
    }

    /**
     * Classe permettant de définir le design des Boutons.
     * hérite de BasicButtonUI pour pouvoir modifier le design des boutons.
     * Deuxième version pour les boutons images.
     * @see BasicButtonUI
     */
    static class OurImageButton extends BasicButtonUI {

        /**
         * Override de la méthode installUI pour modifier le design des boutons.
         * @param c le composant à modifier.
         */
        @Override
        public Dimension getPreferredSize(JComponent c) {
            Dimension preferredSize = super.getPreferredSize(c);
            int size = Math.max(preferredSize.width, preferredSize.height);
            return new Dimension(size, size);
        }

        /**
         * Override de la méthode installUI pour modifier le design des boutons.
         * @param c le composant à modifier.
         */
        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setBorder(new EmptyBorder(5, 5, 5, 5));
            button.setOpaque(false);
            
        }
    
        /**
         * Override de la méthode paint pour modifier le design des boutons.
         * @param g le composant à modifier.
         * @param c le composant à modifier.
         */
        @Override
        public void paint (Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, 0);
            super.paint(g, c);
        }
    
        /**
         * Méthode permettant de modifier le design des boutons.
         * @param g le composant à modifier.
         * @param c le composant à modifier.
         * @param yOffset le décalage du composant.
         */
        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, 2 + yOffset, size.width, size.height - yOffset, 10, 10);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset, 10, 10);
        }
    
    
    }


}

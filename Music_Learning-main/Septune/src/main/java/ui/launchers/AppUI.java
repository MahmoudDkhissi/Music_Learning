package main.java.ui.launchers;

import main.java.models.Statistic;
import main.java.ui.managers.WindowManager;

/**
 * Classe principale du programme.
 * Lance l'application.
 */
public class AppUI {

	/**
	 * Lance l'application.
	 * @param args arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		// Lancement de l'initialisation des statistiques (création des fichiers)
		Statistic.init();
		// Lancement de l'application
		WindowManager.init();
	}

}

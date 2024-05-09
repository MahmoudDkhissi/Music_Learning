package main.java.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de gérer les observeurs.
 * Permet d'ajouter, supprimer et notifier les observeurs.
 * Observable est la classe mère de toutes les classes observables.
 */
public class Observable {
	
	List<Observer> observers = new ArrayList<>();

	/* Methodes liées aux observeurs */
	
	/** Ajouter un observeur 
     * @param observer l'observeur à ajouter.
    */
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

    /* Notifier les observateurs d'un changement dans le modèle.
    * Appelle la méthode update() de chaque observeur.
    */
    public void notifyObservers() {
        // On parcourt la liste des observeurs et on appelle la méthode update() de chacun
        for (Observer observer : observers) {
        	observer.update();
        }
    }
}

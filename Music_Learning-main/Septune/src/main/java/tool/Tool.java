package main.java.tool;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Classe permettant de gérer les outils utiles dans le projet.
 * Contient des méthodes statiques utiles.
 */
public class Tool {

	/**
	 * Joue un son.
	 * @param str le nom du fichier audio.
	 */
    public static void playSound(String str) {
	    try {
            InputStream inputStream = Tool.class.getClassLoader().getResourceAsStream("main/"+str);

			if(inputStream == null) {
				System.out.println("Impossible de trouver le fichier audio " + str);
			} else {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));  
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			}
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}


	/**
	 * Renvoie une liste contenant les éléments de la liste originale.
	 * @param originalList la liste originale.
	 * @return une liste contenant les éléments de la liste originale.
	 */
	public static <E> List<E> copyList(List<E> originalList) {
		List<E> copiedList = new ArrayList<>(originalList);
		return copiedList;
	}

	/**
	 * Affiche une liste.
	 * @param list la liste à afficher.
	 * @param <E> le type des éléments de la liste.
	 */
	public static <E> void displayList(List<E> list) {
		System.out.print("[");
		for(E e : list) {
			System.out.print(e + " ");
		}
		System.out.println("]");
	}

	/**
	 * Renvoie un élément aléatoire d'une liste.
	 * @param list la liste.
	 * @param <E> le type des éléments de la liste.
	 * @return un élément aléatoire de la liste.
	 */
	public static <E> E randomElement(List<E> list) {
		E element = null;

        if (!(list == null) && !list.isEmpty()) {
            Random random = new Random();
        	int randomIndex = random.nextInt(list.size());
			element = list.get(randomIndex);
        }

        return element;
    }

	
}

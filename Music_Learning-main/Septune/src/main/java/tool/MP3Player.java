package main.java.tool;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe permettant de gérer la lecture de fichiers audio.
*/
public class MP3Player {

    public static final int INITIAL = 0, PLAYING = 1, PAUSED = 2;

    private Player player;
    
    private InputStream fis;

    private long pauseLocation;
    private long songTotalLength;
    private String filePath;

    private int state = INITIAL;
    /**
     * Renvoie l'état du lecteur.
     * @return l'état du lecteur.
     */
    public int getState() {
        return state;
    }

    /**
     * Renvoie le chemin du fichier audio.
     * @return le chemin du fichier audio.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Lit un fichier audio.
     */
    public void play() {

        try {
            fis = getClass().getResourceAsStream(filePath);

            if(fis == null) {
                System.out.println("Fichier audio non trouvé.");
            } else {

                BufferedInputStream bis = new BufferedInputStream(fis);

                // On stocke la longueur totale du son
                
                songTotalLength = fis.available();
               
                player = new Player(bis);

                new Thread(() -> {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        // Gérer les exceptions de lecture
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Met en pause la lecture d'un fichier audio.
     */
    public void pause() {

        if (player != null && state != PAUSED) {
            try {
                pauseLocation = fis.available();
                player.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            state = PAUSED;
        }
    }

    /**
     * Reprend la lecture d'un fichier audio.
     */
    public void resume() {

        try {
            fis = getClass().getResourceAsStream(filePath);

            if(fis == null) {
                System.out.println("Fichier audio non trouvé.");
            } else {

                BufferedInputStream bis = new BufferedInputStream(fis);

                // On skip la partie déjà lue
                fis.skip(songTotalLength - pauseLocation);
                
                
               
                player = new Player(bis);

                new Thread(() -> {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        // Gérer les exceptions de lecture
                        e.printStackTrace();
                    }
                }).start();

                state = PLAYING;
            }

        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Arrête la lecture d'un fichier audio.
     */
    public void stop() {

        if (player != null) {
            player.close();
            player = null;
            state = INITIAL;
        }
    }
}

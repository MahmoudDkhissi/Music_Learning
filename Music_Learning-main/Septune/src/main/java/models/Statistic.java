package main.java.models;

import java.io.File;

import main.java.tool.FileManager;


/**
 * Modèle des statistiques.
 * Permet de récupérer les statistiques et de les modifier.
 * Accessible depuis tous les modèles.
 */
public class Statistic {

    // Stockage des statistiques des différents jeux
    private static int[] ChordQuizz;
    private static int[] ChordQuizzReversed;
    private static int[] WorkScale;

    // Gestion du fichier
    private static FileManager fileManager;

    /**
     * Initialise les statistiques.
     * Doit être appelé au lancement de l'application.
     * Crée le fichier qui stocke les statistiques si celui-ci n'existe pas.
     * Récupère les statistiques depuis le fichier.
     */
    public static void init(){

        // Récupérer le répertoire courant du JAR et du fichier
        File jarFile = new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String jarDirectory = jarFile.getParent();

        // Chemin du fichier
        String filePath = jarDirectory + "/stats.txt";

        System.out.println("Stockage des statistiques dans : " + filePath);

        // Initialisation des attributs
        ChordQuizz = new int[2];
        ChordQuizzReversed = new int[2];
        WorkScale = new int[2];
        fileManager = new FileManager(filePath);

        // Créer le fichier s'il n'existe pas
        fileManager.createFileIfNotExists("0 0\n0 0\n0 0\n");
        

        // Récupérer les stats
        getStats();
    }

    /**
     * Permet de charger les statistiques depuis le fichier.
     * Met à jour les attributs.
     */
    public static void getStats() {

        // Récupérer les statistiques depuis le fichier dans son ensemble et les séparer pour les stocker dans les attributs
        String[] content = fileManager.readFromFileLigne(0).split(" ");
        ChordQuizz[0] = Integer.parseInt(content[0]);
        ChordQuizz[1] = Integer.parseInt(content[1]);
        
        // ChordQuizzReversed
        content = fileManager.readFromFileLigne(1).split(" ");
        ChordQuizzReversed[0] = Integer.parseInt(content[0]);
        ChordQuizzReversed[1] = Integer.parseInt(content[1]);

        // WorkScale
        content = fileManager.readFromFileLigne(2).split(" ");
        WorkScale[0] = Integer.parseInt(content[0]);
        WorkScale[1] = Integer.parseInt(content[1]);
    }

    /**
     * Permet de récupérer le score d'un jeu.
     * @param mode Le nom du jeu.
     * @return Le score du jeu.
     */
    public static int getGeneralScore(String mode) {

        // Initialisation de la variable
        int generalScore = -1;

        // Récupérer le score du jeu demandé
        switch(mode){
            case "ChordQuizz":
                generalScore = ChordQuizz[0];
                break;
            case "ChordQuizzReversed":
                generalScore = ChordQuizzReversed[0];
                break;
            case "WorkScale":
                generalScore = WorkScale[0];
                break;
        }

        return generalScore;
    }

    /**
     * Permet de récupérer le nombre d'essais d'un jeu.
     * @param mode Le nom du jeu.
     * @return Le nombre d'essais du jeu.
     */
    public static int getGeneralAttempts(String mode) {

        // Initialisation de la variable
        int generalAttempts = -1;

        // Récupérer le nombre d'essais du jeu demandé
        switch(mode){
            case "ChordQuizz":
                generalAttempts = ChordQuizz[1];
                break;
            case "ChordQuizzReversed":
                generalAttempts = ChordQuizzReversed[1];
                break;
            case "WorkScale":
                generalAttempts = WorkScale[1];
                break;
        }

        return generalAttempts;
    }

    /**
     * Permet d'ingrementer le score d'un jeu.
     * @param mode Le nom du jeu.
     */
    public static void incrementGeneralScore(String mode){
        switch(mode){
            case "ChordQuizz":
                ChordQuizz[0]++;
                break;
            case "ChordQuizzReversed":
                ChordQuizzReversed[0]++;
                break;
            case "WorkScale":
                WorkScale[0]++;
                break;
        }
    }

    /**
     * Permet d'ingrementer le nombre d'essais d'un jeu.
     * @param mode Le nom du jeu.
     */
    public static void incrementGeneralAttempts(String mode){
        switch(mode){
            case "ChordQuizz":
                ChordQuizz[1]++;
                break;
            case "ChordQuizzReversed":
                ChordQuizzReversed[1]++;
                break;
            case "WorkScale":
                WorkScale[1]++;
                break;
        }
    }

    /**
     * Permet de sauvegarder les statistiques dans le fichier.
    */
    public static void save(){
        try {
            // On donne les statistiques complétées au gestionnaire de fichier
            fileManager.writeToFile(ChordQuizz[0] + " " + ChordQuizz[1] + "\n" + ChordQuizzReversed[0] + " " + ChordQuizzReversed[1] + "\n" + WorkScale[0] + " " + WorkScale[1]);
            System.out.println("Sauvegarde reussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }
    
}
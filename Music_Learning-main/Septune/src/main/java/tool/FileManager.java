package main.java.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant de gérer les fichiers.
 * Permet de créer un fichier s'il n'existe pas, d'écrire dans un fichier et de lire un fichier.
 */
public class FileManager {
    private String filePath;
    

    /**
     * Constructeur de FileManager.
     * @param filePath le chemin du fichier.
     */
    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Crée un fichier s'il n'existe pas.
     * @param defaultContent le contenu par défaut du fichier.
    */
    public void createFileIfNotExists(String defaultContent) {
        try {
            File file = new File(filePath);
            // Si le fichier n'existe pas on le crée
            if (!file.exists()) {
                File parentDirectory = file.getParentFile();
                if (parentDirectory != null && !parentDirectory.exists()) {
                    parentDirectory.mkdirs();
                }
                file.createNewFile();
                writeToFile(defaultContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecrit dans un fichier.
     * @param content le contenu à écrire dans le fichier.
    */
    public void writeToFile(String content) {
        try {
            // On écrit dans le fichier
            FileWriter fileWriter = new FileWriter(filePath, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lit un fichier.
     * @return le contenu du fichier.
    */
    public String readFromFile() {
        StringBuilder content = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Lit une ligne d'un fichier.
     * Utilise la méthode readFromFile().
     * @param ligne la ligne à lire.
     * @return le contenu de la ligne.
    */
    public String readFromFileLigne(int ligne){
        String content = readFromFile();
        String[] contentArray = content.split("\n");
        return contentArray[ligne];
    }
    
}
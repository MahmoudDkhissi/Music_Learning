package main.java.models;

import main.java.structures.Note;
import main.java.tool.Tool;

public class WorkEar {
    
    // Elements qui seront observés par la vue
    private Note note;
    private String name;

    // Audio file à jouer
    private String audioFile;

    // Réponse de l'utilisateur
    private String answer;

    // Permet de savoir si l'utilisateur à eu la bonne réponse
    private boolean correctAnswer;

    /** 
     * Construit une instance du jeu DevinerAccords.
     */
    public void launch() {
        // Choix de l'accord à jouer de façon aléatoire
        note = Note.randomNote();
        name = note.getName();
        //System.out.println(name);
        // Audio file à jouer
        audioFile = Tool.randomElement(note.getAudioFiles());
    }

    /**
     * Mettre à jour correctAnswer en fonction de la réponse de l'utilisateur.
     */
    public void check() {
        // Vérification de la réponse de l'utilisateur en ignorant les majuscules et les espaces
        correctAnswer = (answer.toUpperCase().replace(" ", "")).equals(name);
        if(correctAnswer) {
            Tool.playSound("resources/audio/sounds/son_de_la_victoire.wav");
        } else {
            Tool.playSound("resources/audio/sounds/son_de_la_defaite.wav");
        }
    }

    /**
     * Joue l'audio file de l'accord à deviner.
     */
    public void playNote() {
        Tool.playSound("resources/audio/notes/"+audioFile);
    }

    /**
     * Met à jour la réponse de l'utilisateur.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Retourne si la réponse de l'utilisateur.
     * @return la réponse de l'utilisateur est juste.
    */
    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Retourne le nom de l'accord à deviner.
     * @return le nom de l'accord à deviner.
    */
    public String getName() {
        return name;
    }

}